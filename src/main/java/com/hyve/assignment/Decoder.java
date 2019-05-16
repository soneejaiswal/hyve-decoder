package com.hyve.assignment;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author Sonee
 *
 */
public class Decoder {
	protected DecoderInputStream decoderInputStream = null;
	protected final InputStream inputStream = null;
	private String decodedMessage = "";
	private boolean trivialImplementation = false;

	/**
	 * 
	 * @param is
	 */
	public Decoder(InputStream is) {
		if (is == null) {
			System.err.println("InputStream is null.");
			System.exit(1);
		}
		decoderInputStream = new DecoderInputStream(is);
	}

	/**
	 * 
	 * @throws IOException
	 * @throws EOFException
	 */
	public void decode() throws IOException, EOFException {
		while (decoderInputStream.available() > 1) {
			// Instantiate new byteblock holding the pairs.

			ByteBlock byteBlock = new ByteBlock(decoderInputStream.readBytes());
			byteBlock.setValue(decoderInputStream.readBytes());

			// Indicates a character
			if (byteBlock.isNullCharacter()) {
				decodedMessage += byteBlock.valueAsString();
				// Output string value to System.out (stdout)
				System.out.print(byteBlock.valueAsString());
				System.err.write(byteBlock.getByteArray());
			} else {
				// Indicates an offset byteblock
				int startIdx = (decodedMessage.length() - byteBlock.getASCIIValue());
				int startValue = byteBlock.getASCIIValue();
				int endValue = Integer.parseInt(byteBlock.valueAsString());
				int endIdx = startIdx + Integer.parseInt(byteBlock.valueAsString());

				// Some validity checks.
				if (startValue > decodedMessage.length() || startValue == -1) {
					System.out.println("\nInvalid offset value (" + startValue + "). Exiting...");
					System.exit(1);
				}
				if (endValue < 1) {
					System.out.println("\nInvalid offset length value (" + endValue + "). Exiting...");
					System.exit(1);
				}

				if (endIdx > decodedMessage.length()) {
					System.out
							.println("\nRepeat offset (" + endIdx + ") is greater than current decoded message length ("
									+ decodedMessage.length() + "). Exiting...");
					System.exit(1);
				}

				String append = decodedMessage.substring(startIdx, endIdx);
				byteBlock.setEncoding(append);
				decodedMessage += append;

				System.out.print(append);

				if (trivialImplementation) {
					for (byte b : byteBlock.getEncodesForString()) {
						System.err.write(new byte[] { (byte) b });
					}
				} else {
					System.err.write(byteBlock.getByteArray());
				}
			}
		}
	}

	public void isTrivialImplementation(boolean trivialImplementation) {
		this.trivialImplementation = trivialImplementation;
	}
}
