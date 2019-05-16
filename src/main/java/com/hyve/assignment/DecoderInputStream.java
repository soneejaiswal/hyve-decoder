package com.hyve.assignment;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author Sonee
 *
 */
public class DecoderInputStream extends FilterInputStream {

	/**
	 * 
	 * @param in
	 */
	public DecoderInputStream(InputStream in) {
		super(in);
	}

	/**
	 * 
	 * @return
	 * @throws EOFException
	 * @throws IOException
	 */
	public int readBytes() throws EOFException, IOException {
		try {
			return in.read();
		} catch (EOFException ex) {
			throw ex;
		} catch (IOException io) {
			throw io;
		}
	}
}
