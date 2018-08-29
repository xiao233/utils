package table;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * �Ա���һЩ����
 * @author xjl
 * 2018-04-27 14:22:40
 */
public class TableOperate {
	private static Scanner scan = new Scanner(System.in);
	/**
	 * д���ݵ����
	 * 2018-04-27 14:26:26
	 * @param file
	 */
	public static void writeToTable(File file) {
		try {
			//����������
			WritableWorkbook tbl = Workbook.createWorkbook(file);
			//�������
			WritableSheet sheet = tbl.createSheet("����", 0);//�����ƣ����λ��
			WritableSheet sheet1 = tbl.createSheet("С�ܹ�", 1);
			//���ÿ�е�����
			String title[] = {"ѧ��","����","�Ա�","��ϵ��ʽ"};
			//������Ԫ��
			Label label = null;
			for (int i = 0; i < title.length; i++) {
				label = new Label(i, 0, title[i]);//�С��С�ֵ
				//����Ԫ����ӵ����
				sheet.addCell(label);
			}
			int rows = 1;
			while(true) {
				System.out.print("�Ƿ����: ");
				String temp = scan.next();
				if(temp.equals("n")) {
					break;
				}
				System.out.print("ѧ��: ");
				label = new Label(0,rows,scan.next());
				sheet.addCell(label);
				System.out.print("����: ");
				label = new Label(1,rows,scan.next());
				sheet.addCell(label);
				System.out.print("�Ա�: ");
				label = new Label(2,rows,scan.next());
				sheet.addCell(label);
				System.out.print("��ϵ��ʽ: ");
				label = new Label(3,rows,scan.next());
				sheet.addCell(label);
				rows++;
			}
			//д�����ݵ�������
			tbl.write();
			//�رչ�����
			tbl.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �ӱ���ȡ����
	 * 2018-04-27 14:27:03
	 * @param file
	 */
	public static void readerFromTable(File file) {
		try {
			//��ȡ������
			Workbook workbook = Workbook.getWorkbook(file);
			//��ȡ���
			Sheet sheet = workbook.getSheet(0);
			//��ȡ�к�
			int row = sheet.getRows();
			//��ȡ�к�
			int cloum = sheet.getColumns();
			for (int i = 1; i < row; i++) {
				System.out.print("��"+i+"��: ");
				for (int j = 0; j < cloum; j++) {
					//��ȡ��Ԫ��
					Cell cell = sheet.getCell(j, i);//�С���
					//��ʾ��Ԫ������
					System.out.print(" "+cell.getContents());
				}
				System.out.println();
			}
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
