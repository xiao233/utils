package xiao.utils.object;

import java.lang.reflect.Field;

import xiao.utils.check.ParamCheckUtils;

/**
 * �������Ը���
 * @author Administrator
 *
 */
public class ObjectUtils {
	/**
	 * ��ԭ����src���Ե�ֵ���Ƶ�Ŀ�Ķ���dis������ͬ�����������ֶ���
	 * @param src ԭ����
	 * @param dis Ŀ�����
	 */
	public static void objectPropertiesCopy(Object src,Object dis) {
		try {
			ParamCheckUtils.paramIsNull(src, "ԭ����Ϊnull");
			ParamCheckUtils.paramIsNull(dis, "Ŀ�Ķ���Ϊnull");
		} catch (Exception e) {
			System.out.println("error : "+e.getMessage());
			return;
		}
		Class<? extends Object> srcClass = src.getClass(); //��ȡ�����class����
	
		Field []srcFiled = srcClass.getDeclaredFields();//��ȡ��������б���
		
		Class<? extends Object> disClass = dis.getClass();
		Field []disFiled = disClass.getDeclaredFields();
		try {
			for (int i = 0; i < disFiled.length; i++) {
				for (int j = 0; j < srcFiled.length; j++) {
					boolean flag = checkIsSameFiled(disFiled[i],srcFiled[j]);
					if(flag) {
						disFiled[i].setAccessible(true);  //���������ķ���Ȩ��
						srcFiled[j].setAccessible(true);
						//��ԭ�������Ӧ�ֶ�ȡֵ��ֵ��Ŀ��������Ӧ�ֶ�
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
	 * �Զ����ֶε�ֵΪnull���ֶν��г�ʼ��
	 * @param objecinit ����ʼ������
	 * @param initStr	String���ͳ�ʼ��ֵ
	 * @param initInt	���ͳ�ʼ��ֵ
	 * @param initCha	�ַ��ͳ�ʼ��ֵ
	 */
	public static void objectInit(Object objecinit,String initStr,Integer initInt,Character initCha) {
		try {
			ParamCheckUtils.paramIsNull(objecinit, "����Ϊnull");
		} catch (Exception e) {
			System.out.println("error : "+e.getMessage());
			return;
		}
		
		Class<? extends Object> classObj = objecinit.getClass();//��ȡ�����Class����
		Field []field = classObj.getDeclaredFields();
		
		//����String�ֶε�Ĭ��ֵΪ""
		if(initStr==null) {
			initStr="";
		}
		// ���������ֶε�Ĭ��ֵΪ0
		if (initInt == null) {
			initInt = 0;
		}
		// �����ַ����ֶε�Ĭ��ֵΪ' '
		if (initCha == null) {
			initCha = ' ';
		}
		try {
			//�����ʼ��
			for (int i = 0; i < field.length; i++) {
				field[i].setAccessible(true);
				Object obj = field[i].get(objecinit);//��ȡ����ָ�˵�ֵ
				if(obj!=null) {//������ֵ���ڸı�
					continue;
				}
				//����String��������
				if(String.class.equals(field[i].getType())) {
					field[i].set(objecinit, initStr);
				}
				//�������ͺͳ���������
				if(Integer.class.equals(field[i].getType())
						||Long.class.equals(field[i].getType())) {
					field[i].set(objecinit, initInt);
				}
				//�����ַ�������
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
	 * �Զ�������Ϊnull��String�������ݽ��г�ʼ��
	 * @param objecinit
	 * @param initStr String���ͳ�ʼ��ֵ
	 */
	public static void objectInitString(Object objecinit,String initStr) {
		objectInit(objecinit, initStr, null, null);
	}
	/**
	 * �Զ�������Ϊnull���������ݽ��г�ʼ��
	 * @param objecinit
	 * @param initInt ���ͳ�ʼ��ֵ
	 */
	public static void objectInitInteger(Object objecinit,Integer initInt) {
		objectInit(objecinit, null, initInt, null);
	}
	/**
	 * �Զ�������Ϊnull��Character�������ݽ��г�ʼ��
	 * @param objecinit
	 * @param initCha Character���ͳ�ʼ��ֵ
	 */
	public static void objectInitCharacter(Object objecinit,Character initCha) {
		objectInit(objecinit, null, null, initCha);
	}
	/**
	 * ��֤�������������ͺ������Ƿ���ͬ
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
