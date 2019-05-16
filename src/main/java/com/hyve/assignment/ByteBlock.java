package com.hyve.assignment;

import java.util.ArrayList;

/**
 * stores byte blocks
 * 
 * @author Sonee
 *
 */
public class ByteBlock {

	private int asciiCharacter;
	private int asciiValue;
	private int value;
	private boolean isNullCharacter;
	private String encodingString;

	public ByteBlock(int asciiCharacter) {
		this.asciiCharacter = asciiCharacter;
		this.asciiValue = Character.getNumericValue((char) asciiCharacter);

		if (this.asciiValue == 0)
			this.isNullCharacter = true;

	}

	public int getASCIIValue() {
		return asciiValue;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	/**
	 * read ByteBlock as normal byte[]
	 * 
	 * @return
	 */
	public byte[] getByteArray() {
		return new byte[] { (byte) (asciiCharacter), (byte) value };
	}

	public void setEncoding(String encodingString) {
		this.encodingString = encodingString;
	}

	// In case this ByteBlock encodes for an offset,
	// we should be able to retrieve the string it encodes for as a byte array.
	// This is used for the trivial re-encoding.
	/**
	 * 
	 * @return
	 */
	public ArrayList<Byte> getEncodesForString() {
		ArrayList<Byte> byteList = new ArrayList<>();

		byte[] asBytes = encodingString.getBytes();

		for (byte b : asBytes) {
			// Always add the '0' indicator byte
			byteList.add((byte) 0x30);
			// Java int's are always signed, convert to unsigned and add.
			int uByteVal = b & 0xFF;
			byteList.add((byte) uByteVal);
		}

		return byteList;
	}

	public String valueAsString() {
		if (isNullCharacter) {
			boolean isAscii = this.value < 128;
			if (isAscii) {
				return Character.toString((char) this.value);
			} else {
				// Set value to value for '?' ascii = 63
				this.setValue(63);
				return "?";
			}
		} else {
			// Parse the value to get offset length value. Returns -1 if invalid.
			return Integer.toString(Character.getNumericValue((char) this.value));
		}
	}

	public boolean isNullCharacter() {
		return isNullCharacter;
	}
}
