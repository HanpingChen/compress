package com.chen.main;

import static org.junit.Assert.*;

import java.nio.file.Path;

import org.junit.Test;

public class TestCompress {

	String Path = "";
	@Test
	public void test() {
		CCompressor compressor = new CCompressor(Path);
		compressor.compress();
	}

}
