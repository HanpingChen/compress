package com.chen.util;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class TestSystem {

	@Test
	public void test() {
		System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径 
		File file = new File("test");
		file.delete();
	}

}
