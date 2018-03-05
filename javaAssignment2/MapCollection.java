package javaAssignment2;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class MapCollection {

	ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<Integer, String>();  // ConcurrentHashMap is thread-safe
	int heapSizeOfMap = 10;  // heap size or limit

	// -------PUT Operation---------
	synchronized void putOperation(int key, String value) throws IOException {
		DataBaseAPI db = new DataBaseAPI();
		if (map.size() >= heapSizeOfMap) {   //if map exceeds heap size
			// spill all data from map to disk (db.json)
			db.putInDB(map);
			map.clear();
		} else {
			map.put(key, value);
		}
	}

	// -----------GET Operation-------------
	synchronized String getOperation(int key) throws IOException {

		String value = null;
		
		DataBaseAPI api = new DataBaseAPI();
		ConcurrentHashMap<Integer, String> getMap = api.getFromDB();  //fetching all the data from disk and put in a temporary map
		
		/* Here we should use a No-SQL database here, which store in key/value
		 * pair manner. So that we just provide a key and it will just return value
		 * associated with it.
		 * 
		 * So, the fetching from the disk will be the same complexity O(1) as of HashMap. Since
		 * we are using a temporary map.  
		 */
		
		if(map.contains(key)){
			value = map.get(key);
		}else{
			// looking in disk for the given key
			value = getMap.get(key);
		}
		 return value;
	}

	public static void main(String[] args) throws IOException {
		// MapCollection obj = new MapCollection();
		// obj.map.put(1, "amit");
		// obj.map.put(2, "sumit");
		// obj.map.put(3, "nitin");
		// System.out.println(obj.map.toString());
		// obj.putOperation(1, "amit");
		// obj.putOperation(2, "sumit");
		// obj.putOperation(3, "nitin");
		// System.out.println(obj.map.toString());
		// System.out.println(obj.getOperation(1));

		// for (Integer i : map.keySet()) {
		// System.out.println(i + "\t" + map.get(i));
		// }
	}
}
