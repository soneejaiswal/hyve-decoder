package com.hyve.assignment.test;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.hyve.assignment.Decoder;

/**
 * 
 * @author Sonee
 *
 */
public class DecoderTest {
	public static void main(String[] args) {

		boolean useTrivial = "1".equals(System.getenv("USE_TRIVIAL_IMPLEMENTATION")) ? true : false;

		BufferedInputStream in = new BufferedInputStream(System.in);
		Decoder decoder = new Decoder(in);
		try {
			if (useTrivial)
				decoder.isTrivialImplementation(true);
			decoder.decode();

		} catch (FileNotFoundException e) {
			System.err.println("Not found " + e.getMessage());
			System.exit(1);
		} catch (IOException ex) {
			System.out.println("Fatal IOException " + ex.getMessage());
			System.exit(1);
		}

	}
}
