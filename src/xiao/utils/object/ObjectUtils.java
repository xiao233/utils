package xiao.utils.object;

import java.lang.reflect.Field;

import xiao.utils.check.ParamCheckUtils;

/**
 * 对象属性复制
 * @author Administrator
 *
 */
public class ObjectUtils {
	/**
	 * 将原对象src属性的值复制到目的对象dis具有相同的属性名的字段中
	 * @param src 原对象
	 * @param dis 目标对象
	 */
	public static void objectPropertiesCopy(Object src,Object dis) {
		try {
			ParamCheckUtils.paramIsNull(src, "原对象为null");
			ParamCheckUtils.paramIsNull(dis, "目的对象为null");
		} catch (Exception e) {
			System.out.println("error : "+e.getMessage());
			return;
		}
		Class<? extends Object> srcClass = src.getClass(); //获取对象的class对象
	
		Field []srcFiled = srcClass.getDeclaredFields();//获取对象的所有变量
		
		Class<? extends Object> disClass = dis.getClass();
		Field []disFiled = disClass.getDeclaredFields();
		try {
			for (int i = 0; i < disFiled.length; i++) {
				for (int j = 0; j < srcFiled.length; j++) {
					boolean flag = checkIsSameFiled(disFiled[i],srcFiled[j]);
					if(flag) {
						disFiled[i].setAccessible(true);  //开启变量的访问权限
						srcFiled[j].setAccessible(true);
						//从原对象的相应字段取值赋值给目标对象的相应字段
						disFiled[i].set(dis, srcFiled[j].get(src));
					}
				}
				
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 对对象字段的值为null的字段进行初始化
	 * @param objecinit 待初始化对象
	 * @param initStr	String类型初始化值
	 * @param initInt	整型初始化值
	 * @param initCha	字符型初始化值
	 */
	public static void objectInit(Object objecinit,String initStr,Integer initInt,Character initCha) {
		try {
			ParamCheckUtils.paramIsNull(objecinit, "对象为null");
		} catch (Exception e) {
			System.out.println("error : "+e.getMessage());
			return;
		}
		
		Class<? extends Object> classObj = objecinit.getClass();//获取对象的Class对象
		Field []field = classObj.getDeclaredFields();
		
		//设置String字段的默认值为""
		if(initStr==null) {
			initStr="";
		}
		// 设置整型字段的默认值为0
		if (initInt == null) {
			initInt = 0;
		}
		// 设置字符型字段的默认值为' '
		if (initCha == null) {
			initCha = ' ';
		}
		try {
			//对象初始化
			for (int i = 0; i < field.length; i++) {
				field[i].setAccessible(true);
				Object obj = field[i].get(objecinit);//获取对象指端的值
				if(obj!=null) {//对象有值不在改变
					continue;
				}
				//所有String类型数据
				if(String.class.equals(field[i].getType())) {
					field[i].set(objecinit, initStr);
				}
				//所有整型和长整型数据
				if(Integer.class.equals(field[i].getType())
						||Long.class.equals(field[i].getType())) {
					field[i].set(objecinit, initInt);
				}
				//所有字符型数据
				if(Character.class.equals(field[i].getType())) {
					field[i].set(objecinit, initCha);
				}
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 对对象所有为null的String类型数据进行初始化
	 * @param objecinit
	 * @param initStr String类型初始化值
	 */
	public static void objectInitString(Object objecinit,String initStr) {
		objectInit(objecinit, initStr, null, null);
	}
	/**
	 * 对对象所有为null的整型数据进行初始化
	 * @param objecinit
	 * @param initInt 整型初始化值
	 */
	public static void objectInitInteger(Object objecinit,Integer initInt) {
		objectInit(objecinit, null, initInt, null);
	}
	/**
	 * 对对象所有为null的Character类型数据进行初始化
	 * @param objecinit
	 * @param initCha Character类型初始化值
	 */
	public static void objectInitCharacter(Object objecinit,Character initCha) {
		objectInit(objecinit, null, null, initCha);
	}
	/**
	 * 验证两个变量的类型和名称是否相同
	 * @param dis
	 * @param src
	 * @return
	 */
	private static boolean checkIsSameFiled(Field dis, Field src) {
		if(dis.getType().equals(src.getType())
				&&dis.getName().equals(src.getName())) {
			return true;
		}
		return false;
	}
}
