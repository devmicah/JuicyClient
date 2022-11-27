package dev.micah.juicyclient.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class FileManager {

	private static File home = new File(System.getProperty("user.home") + "/AstroClient");
	private static Gson gson = new Gson();
	
	public static File getHomeDirectory() {
		if (!home.exists()) {
			home.mkdirs();
		}
		return home;
	}
	
	public static boolean writeJsonToFile(File file, Object obj) {
		try {
			if (!file.exists()) {
			file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file);
			out.write(gson.toJson(obj).getBytes());
			out.flush();
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static <T extends Object> T readFromJson(File file, Class<T> c) {
		try {	
			FileInputStream in = new FileInputStream(file);	
			InputStreamReader reader = new InputStreamReader(in);
			BufferedReader bf = new BufferedReader(reader);
			StringBuilder builder = new StringBuilder();
			String line;
			while ((line = bf.readLine()) != null) {
				builder.append(line);
			}
			bf.close();
			reader.close();
			in.close();
			return gson.fromJson(builder.toString(), c);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
		}
	}
	
}
