package xiao.utils;

import java.io.IOException;

public class RunProgram {
	private static final Runtime runtime = Runtime.getRuntime();
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Process process = null;
		try {
			process = runtime.exec("\"D:\\Sublime Text 3\\sublime_text.exe\"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
