package com.chen.util;

/**
 * 获取当前系统的信息
 * @author chen
 *
 */
public class SystemType {

	private static String getSystemType(){
		String systemType = System.getProperty("os.name");
		return systemType;
	}
	
	/**
	 * 获取当前系统所使用的分割符号
	 * @return
	 */
	public static String getSplit() {
		String type = getSystemType();
		String split = "";
		if (type.startsWith("win")||type.startsWith("Win")) {
			return "\\";
		}
		return "/";
	}
}
