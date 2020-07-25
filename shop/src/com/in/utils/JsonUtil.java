package com.in.utils;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.xml.XMLSerializer;

/**
 * 处理json数据格式的工具类
 * 
 * @Date 2013-3-31
 * @version 1.0
 */
public class JsonUtil {
	/**
	 * 
	 * 
	 * @param array
	 * @return
	 */
	public static String array2json(Object[] objects){
		
		JSONArray jsonArray = JSONArray.fromObject(objects);
		return jsonArray.toString();
		
	}
	
	/**
	 * 
	 * 
	 * @param list
	 * @return
	 */
	public static String list2json(List list){
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
		
	}
	
	/**
	 * 
	 * 
	 * @param map
	 * @return
	 */
	public static String map2json(Map map){
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();
		
	}
	
	/**
	 * 
	 * 
	 * @param object
	 * @return
	 */
	public static String object2json(Object object){
		
		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
		
	}
	
	/**
	 * 
	 * 
	 * @param xml
	 * @return
	 */
	public static String xml2json(String xml){
		
		JSONArray jsonArray = (JSONArray) new XMLSerializer().read(xml);
		return jsonArray.toString();
		
	}
	
	/**
	  * Supprimez les champs indésirables (particulièrement adaptés pour supprimer les objets en cascade)
	  *
	  * @param excludes
	  * @return
	*/
	public static JsonConfig configJson(String[] excludes) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		jsonConfig.setIgnoreDefaultExcludes(true);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return jsonConfig;
	}
	
}
