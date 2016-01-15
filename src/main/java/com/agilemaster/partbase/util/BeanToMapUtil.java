package com.agilemaster.partbase.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.junjie.commons.utils.JunjieStaticMethod;

public class BeanToMapUtil {
	private static Logger log = LoggerFactory.getLogger(BeanToMapUtil.class);
	/**
	 * 将一个 Map 对象转化为一个 JavaBean
	 * map为数据库查询,字段分隔为下划线
	 * @param <T>
	 * @param type
	 *            要转化的类型
	 * @param map
	 *            包含属性值的 map
	 * @return 转化出来的 JavaBean 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InstantiationException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 */
	public static <T> T convertMap(Class<T> type, Map map) {
		if(map==null){
			return null;
		}
		BeanInfo beanInfo = null;
		T obj = null;
			try {
				beanInfo = Introspector.getBeanInfo(type);
				obj = type.newInstance(); // 创建 JavaBean 对象
			} catch (Exception e) {
				log.error("init type error",e);
			}
			// 给 JavaBean 对象的属性赋值
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = propertyToField(descriptor.getName());
				if (map.containsKey(propertyName)) {
					// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
					Object value = map.get(propertyName);
                    if(value==null){
                    	continue;
                    }
					Object[] args = new Object[1];
					args[0] = value;
					try {
						if(value instanceof  Timestamp){
							Calendar can = Calendar.getInstance();
							can.setTimeInMillis(((Timestamp)value).getTime());
							args[0] = can;
							descriptor.getWriteMethod().invoke(obj, args);
						}else {
							Class<? extends Object> clazzListObject =descriptor.getPropertyType();
							if (!JunjieStaticMethod.isBaseObjectStr(clazzListObject.getSimpleName())) {
								Method setIdMethod = clazzListObject.getMethod("setId",Long.class);
								Object valueNew = clazzListObject.newInstance();
								setIdMethod.invoke(valueNew, value);
								descriptor.getWriteMethod().invoke(obj, valueNew);
							}else{
								descriptor.getWriteMethod().invoke(obj, args);
							}
						}
					} catch (Exception e) {
						log.error("propertyName :" +propertyName +" convert failed  {}",type,e);
					} 
				}
			}
		return obj;
	}

	/**
	 * 对象属性转换为字段 例如：userName to user_name
	 * 
	 * @param property
	 *            字段名
	 * @return
	 */
	public static String propertyToField(String property) {
		if (null == property) {
			return "";
		}
		char[] chars = property.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (char c : chars) {
			if (Character.isUpperCase(c)) {
				sb.append("_" + Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 字段转换成对象属性 例如：user_name to userName
	 * 
	 * @param field
	 * @return
	 */
	public static String fieldToProperty(String field) {
		if (null == field) {
			return "";
		}
		char[] chars = field.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (c == '_') {
				int j = i + 1;
				if (j < chars.length) {
					sb.append(Character.toUpperCase(chars[j]));
					i++;
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 * 
	 * @param bean
	 *            要转化的JavaBean 对象
	 * @return 转化出来的 Map 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 */
	public static Map<String, Object> convertBean(Object bean) {
		Class<? extends Object> type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					if (result != null) {
						returnMap.put(propertyToField(propertyName), result);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnMap;
	}

	public static void main(String args[]) {
		Map propertiesMap = new HashMap();
		propertiesMap.put("my_code", "aaaaa");
		propertiesMap.put("code", "code");
		TestBean testBean = BeanToMapUtil.convertMap(TestBean.class,
				propertiesMap);
		System.out.println("code--->" + testBean.code);
		System.out.println("myCode--->" + testBean.myCode);
		propertiesMap = BeanToMapUtil.convertBean(testBean);
		System.out.println(propertiesMap);
	}
}

class TestBean {
	String myCode;
	String code;

	public TestBean() {
	}

	public String getMyCode() {
		return myCode;
	}

	public void setMyCode(String myCode) {
		this.myCode = myCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
