package javaAssignment2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONArray;

public class DataBaseAPI {

	/*
	 * Disk manipulation API 
	 * For illustrating disk, I have used json data format
	 * in order to save the data in secondary storage if current map exceeds
	 * heap size.
	 * 
	 * Here we should have used a No-SQL database here, which store in key/value pair
	 * manner. So that we just provide a key and it will just return value
	 * associated with it.
	 * 
	 */

	String File_Name = "db.json";  //Secondary Storage (Disk).

	
	// get operation from disk
	public ConcurrentHashMap<Integer, String> getFromDB() throws FileNotFoundException {
		
		/* returns a temporary map with all the data present in disk
		 * and then from that map we can fetch the data in O(1)
		 * time complexity.(For Illustration only i have used this)
		 */
		
		
		ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<Integer, String>();

		Scanner scan = new Scanner(new File(File_Name));

		StringBuilder build = new StringBuilder();

		while (scan.hasNextLine()) {
			build.append(scan.nextLine());
		}

		JSONArray arr = new JSONArray(build.toString());

		for (int i = 0; i < arr.length(); i++) {
			JSONArray obj = arr.getJSONArray(i);
			map.put(obj.getInt(1), obj.getString(0));
		}

		scan.close();
		return map;
	}

	// put operation in disk
	public void putInDB(ConcurrentHashMap<Integer, String> map) throws IOException {
		
		// Takes a map and spill all the data into the disk
		
		JSONArray arr = new JSONArray();

		for (int key : map.keySet()) {
			JSONArray jsonObj = new JSONArray();
			jsonObj.put(0, map.get(key));
			jsonObj.put(1, key);
			arr.put(jsonObj);
		}

		BufferedWriter wr = new BufferedWriter(new FileWriter(new File(File_Name)));
		wr.write(arr.toString());
		wr.close();
	}
}
