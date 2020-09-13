package me.titan.titanlib.util;

import java.util.HashMap;
import java.util.Map;

public class MapsMap<MK, K, V> extends HashMap<MK, Map<K,V>> {

	public void put(MK mainKey, K key, V value){
		Map<K, V> map = null;
		if(containsKey(mainKey)){
			map = new HashMap<>(get(mainKey));
		}else{
			map = new HashMap<>();
		}
		map.put(key,value);
		put(mainKey,map);
	}
	public V getValue(MK mainKey, K key){
		return containsKey(mainKey) ? get(mainKey).get(key) : null;
	}


}
