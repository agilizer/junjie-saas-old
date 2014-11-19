package com.agilemaster.parta.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebParamsConvert {
	private static final Logger log = LoggerFactory
			.getLogger(WebParamsConvert.class);
	/**
	 * just convert Long Boolean Calendar
	 * 
	 * @param type
	 * @param map
	 * @param timeFormat
	 * @return
	 */
	public static <T> T convertMap(Class<T> type, Map<String, String[]> map,
			String timeFormat) {
		BeanInfo beanInfo = null;
		T obj = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (null != timeFormat) {
			sdf = new SimpleDateFormat(timeFormat);
		}
		try {
			beanInfo = Introspector.getBeanInfo(type);
			obj = type.newInstance(); // 创建 JavaBean 对象
		} catch (Exception e) {
			log.error("class init error {}",type);
		}
		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (map.containsKey(propertyName)) {
				// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
				String[] value = map.get(propertyName);
				if (value == null) {
					continue;
				}
				Object[] args = new Object[1];
				args[0] = value[0];
				Class<?> parameterType = descriptor.getWriteMethod()
						.getParameterTypes()[0];
				if (parameterType.getSimpleName().equalsIgnoreCase("Long")) {
					args[0] = Long.parseLong(value[0]);
				}
				if (parameterType.getSimpleName().equalsIgnoreCase("List")) {
					continue;
				}
				if (parameterType.getSimpleName().equalsIgnoreCase("Boolean")) {
					args[0] = Boolean.parseBoolean(value[0]);
					if (value.length == 2) {
						args[0] = Boolean.TRUE;
					}
				}
				if (parameterType.getSimpleName().equalsIgnoreCase("Short")) {
					args[0] = Short.parseShort(value[0]);
				}
				try {
					if (parameterType.getSimpleName().equalsIgnoreCase(
							"Calendar")) {
						Date date = sdf.parse(value[0]);
						Calendar can = Calendar.getInstance();
						can.setTimeInMillis(date.getTime());
						args[0] = can;
					}
					descriptor.getWriteMethod().invoke(obj, args);
				} catch (Exception e) {
					log.error("convert error-->parameterType:  "+parameterType.getSimpleName()+" propertyName:"+propertyName+ " args [0]" + args[0]+" args {}",args ,e);
				}
			}
		}
		return obj;
	}

}
