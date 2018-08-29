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
 * 对表格的一些操作
 * @author xjl
 * 2018-04-27 14:22:40
 */
public class TableOperate {
	private static Scanner scan = new Scanner(System.in);
	/**
	 * 写数据到表格
	 * 2018-04-27 14:26:26
	 * @param file
	 */
	public static void writeToTable(File file) {
		try {
			//创建工作簿
			WritableWorkbook tbl = Workbook.createWorkbook(file);
			//创建表格
			WritableSheet sheet = tbl.createSheet("大哥好", 0);//表名称，表的位置
			WritableSheet sheet1 = tbl.createSheet("小弟乖", 1);
			//表格每列的名称
			String title[] = {"学号","姓名","性别","联系方式"};
			//创建单元格
			Label label = null;
			for (int i = 0; i < title.length; i++) {
				label = new Label(i, 0, title[i]);//列、行、值
				//将单元格添加到表格
				sheet.addCell(label);
			}
			int rows = 1;
			while(true) {
				System.out.print("是否继续: ");
				String temp = scan.next();
				if(temp.equals("n")) {
					break;
				}
				System.out.print("学号: ");
				label = new Label(0,rows,scan.next());
				sheet.addCell(label);
				System.out.print("姓名: ");
				label = new Label(1,rows,scan.next());
				sheet.addCell(label);
				System.out.print("性别: ");
				label = new Label(2,rows,scan.next());
				sheet.addCell(label);
				System.out.print("联系方式: ");
				label = new Label(3,rows,scan.next());
				sheet.addCell(label);
				rows++;
			}
			//写入数据到工作簿
			tbl.write();
			//关闭工作簿
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
	 * 从表格读取数据
	 * 2018-04-27 14:27:03
	 * @param file
	 */
	public static void readerFromTable(File file) {
		try {
			//获取工作簿
			Workbook workbook = Workbook.getWorkbook(file);
			//获取表格
			Sheet sheet = workbook.getSheet(0);
			//获取行号
			int row = sheet.getRows();
			//获取列号
			int cloum = sheet.getColumns();
			for (int i = 1; i < row; i++) {
				System.out.print("第"+i+"行: ");
				for (int j = 0; j < cloum; j++) {
					//获取单元格
					Cell cell = sheet.getCell(j, i);//列、行
					//显示单元格内容
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
