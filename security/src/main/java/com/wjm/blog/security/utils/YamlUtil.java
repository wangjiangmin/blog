/**
 * @Title: YamlUtil.java
 * @Package com.ahead.ebook.utils
 * @Description: TODO
 * @author 王江民		@date 2022-06-27 16:18:09	@version V1.0 
 */

package com.wjm.blog.security.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName: YamlUtil
 * @Description: TODO
 * @author 王江民 @date 2022-06-27 16:18:09 @version V1.0
 */

public class YamlUtil {
	
	static String yamlName;
	
	YamlUtil(String yamlName){
		this.yamlName = yamlName;
		getYamlMap();
	}
	
	private static LinkedHashMap<String, Map<String, String>> properties;

	public YamlUtil getYamlMap() {
		InputStream in = null;
		try {
			Yaml yaml = new Yaml();
			in = YamlUtil.class.getClassLoader().getResourceAsStream(Objects.requireNonNull(yamlName));
			properties = yaml.loadAs(in, LinkedHashMap.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this;
	}

	public String getValueByKey(String root) {
		String value = null;
		
		String[] split = root.split("\\.");
		if(split.length == 1) {
			Iterator it = properties.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				if (split[0].equals(entry.getKey())) {
					value = (String) entry.getValue();
					break;
				}
			}
		}else {
			LinkedHashMap<String, String> rootProperty = (LinkedHashMap<String, String>) properties.get(split[0]);
			value = iter(rootProperty, root.substring(root.indexOf(".") +1,root.length()));
		}
		return value;
	}

	public static String iter(LinkedHashMap<String, String> rootProperty, String key) {
		Iterator it = rootProperty.entrySet().iterator();
		String value = null;
		String[] split = key.split("\\.");
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			if (key.equals(entry.getKey())) {

				return (String) entry.getValue();
			}
			if (!(entry.getValue() instanceof LinkedHashMap)) {
				continue;
			}
			if(split[0].equals(entry.getKey())) {
				value = iter((LinkedHashMap<String, String>) entry.getValue(), key.substring(key.indexOf(".") +1,key.length()));
			}
			if (value != null) {
				break;
			}
		}
		return value;
	}
}
