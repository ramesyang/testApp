package com.rames.testapp.util;

import android.util.Log;

import java.io.File;

/**
 * 与应用相关的一些实用方法
 * 
 * @author solomon.wen
 * @date 2012-09-16
 */
public class AppUtil {
	private static boolean isRelease = false;


	/**
	 * 用 System.out.println 打印调试信息
	 * ( 若调试开关关闭，则不打印 )
	 */
	public static void print(String x) {
		if (null == x || !isRelease) {
			return;
		}

		System.out.println(x);
	}

	/**
	 * 往屏幕输出普通调试信息
	 * ( 若调试开关关闭，则不打印 )
	 * @param tag
	 * @param msg
	 */
	public static void verbose(String tag, String msg) {
		if (null == msg || !isRelease) {
			return;
		}

		Log.v(null == tag ? "^_^" : tag, msg);
	}

	/**
	 * 往屏幕输出普通调试信息
	 * 
	 * ( 若调试开关关闭，则不打印 )
	 * @param msg
	 */
	public static void verbose(String msg) {
		if (null == msg || !isRelease) {
			return;
		}

		verbose(null, msg);
	}

	/**
	 * 用红字输出出错信息
	 * 
	 * ( 若调试开关关闭，则不打印 )
	 * @param msg
	 */
	public static void error(Object obj, String msg) {
		if (null == msg || !isRelease) {
			return;
		}

		String tag = "^_^";
		if (null != obj && obj instanceof String) {
			tag = (String) obj;
		} else if (null != obj) {
			tag = getClassName(obj);
		}

		Log.e(tag, msg);
	}

	/**
	 * 用红字输出出错信息
	 * 
	 * ( 若调试开关关闭，则不打印 )
	 * @param msg
	 */
	public static void error(String msg) {
		if (null == msg || !isRelease) {
			return;
		}

		error(null, msg);
	}

	/**
	 * 打印出错信息的 StackTrace 信息
	 * 
	 * ( 若调试开关关闭，则不打印 )
	 */
	public static void print(Throwable e) {
		if (null == e || !isRelease) {
			return;
		}

		if (e instanceof Throwable) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取一个对象的类名
	 * @return String
	 */
	public static String getClassName(Object x) {
		if (null != x) {
			try {
				String className;
				if (x instanceof Class<?>) {
					className = ((Class<?>) x).getName();
				} else {
					className = x.getClass().getName();
				}

				String[] classNames = className.split("\\.");
				return classNames[classNames.length - 1];
			} catch (Exception e) {
				AppUtil.print(e);
			}
		}

		return "";
	}

	/**
	 * 获取一个文件的大小
	 * 
	 * @param filePath
	 * @return String
	 */
	public static String getFileSize(String filePath) {
		if (null == filePath || filePath.length() < 1) {
			return "";
		}

		File file = new File(filePath);
		if (!file.exists()) {
			return "";
		}

		return getStringSize(file.length());
	}

	/**
	 * 获取字符串形式的容量
	 * 
	 * @param filesize
	 * @return String
	 */
	public static String getStringSize(long filesize) {
		if (filesize < 1) {
			return "0 Byte";
		}

		float finallysize = 0;
		String unit;

		if (filesize < 1024) {
			finallysize = filesize;
			unit = "Bytes";
		} else if (filesize < 1024 * 1024) {
			finallysize = (float) (Math.ceil(100 * (filesize / 1024.0f)) / 100);
			unit = "KB";
		} else if (filesize < 1024 * 1024 * 1024) {
			finallysize = (float) (Math.ceil(100 * (filesize / 1024.0f / 1024.0f)) / 100);
			unit = "MB";
		} else {
			finallysize = (float) (Math.ceil(100 * (filesize / 1024.0f / 1024.0f / 1024.0f)) / 100);
			unit = "GB";
		}

		return (finallysize + " " + unit);
	}
}
