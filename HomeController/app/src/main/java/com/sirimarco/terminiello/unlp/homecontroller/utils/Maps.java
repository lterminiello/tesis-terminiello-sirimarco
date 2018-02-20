package com.sirimarco.terminiello.unlp.homecontroller.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Maps {

    /**
	 * Creates a <i>mutable</i>, empty {@code HashMap} instance.
	 * 
	 * @return a new, empty {@code HashMap}
	 */
	public static <K, V> HashMap<K, V> newHashMap() {
		return new HashMap<>();
	}
	
	/**
	 * Creates a <i>mutable</i>, empty, insertion-ordered {@code LinkedHashMap} instance.
	 * 
	 * @return a new, empty {@code LinkedHashMap}
	 */
	public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
		return new LinkedHashMap<>();
	}

	public static <K, V> boolean isNullOrEmpty(Map<K, V> map) {
		return map == null || map.isEmpty();
	}
}