package com.chen.main;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.Test;

public class TestCompress {

	String Path = "";
	@Test
	public void test() {
		CCompressor compressor = new CCompressor(Path);
		compressor.compress();
	}
	
	@Test
	public void testDsrc(){
		Runtime runtime = Runtime.getRuntime();
		Process process = null;
		try {
			String cmd = "/Users/chen/Downloads/pack-osx-clang-2/bin/dsrc c /Users/chen/Downloads/SRR042437.filt.fastq /Users/chen/Desktop/a.temp";
			String cmdD = "/Users/chen/Downloads/pack-osx-clang-2/bin/dsrc b /Users/chen/Desktop/a.temp /Users/chen/Desktop/b.tmp";
			runtime.exec(cmdD);
			System.out.println("执行成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
