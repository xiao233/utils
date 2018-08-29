package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 对文件夹的一些操作
 * @author xjl
 * 2018-04-26 15:06:36
 */
public class FolderOperate {
	/**
	 * 把目标文件里面的内容复制到指定文件里面
	 * 2018-04-26 15:07:33
	 * @param srcFile 源文件
	 * @param disFile 目标文件
	 */
	public static void copyFolder(File srcFile,File disFile) {
		File []fileList = srcFile.listFiles();
		for (File file : fileList) {
			if(file.isFile()) {
				FileReader fr = null;
				BufferedReader br = null;
				
				FileWriter fw = null;
				BufferedWriter bw = null;
				
				try {
					fr = new FileReader(file);
					br = new BufferedReader(fr);
					
					String temp = disFile.getAbsolutePath()+File.separator+file.getName();
					System.out.println("\n创建文件： "+ temp);
					fw = new FileWriter(disFile.getAbsolutePath()+File.separator+file.getName());
					bw = new BufferedWriter(fw);
					
					String line = "";
					System.out.println("开始从文件-->"+file.getAbsolutePath()+file.getName());
					System.out.println("复制内容到文件-->"+temp);
					int rows = 1;
					while((line = br.readLine())!=null) {
						System.out.println("\t"+rows+" 复制:  "+line);
						rows++;
						bw.write(line);
						bw.newLine();
						bw.flush();
					}
					System.out.println("======复制文件成功======");
					br.close();
					bw.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				File temp = new File(disFile.getAbsolutePath()+File.separator+file.getName());
				System.out.println("\n创建文件夹： "+temp.getAbsolutePath());
				temp.mkdirs();
				copyFolder(file, temp);
			}
		}
	}
	/**
	 * 显示文件夹结构
	 * 2018-04-26 16:51:22
	 * @param file 文件位置
	 * @param str  占位符
	 */
	public static void showFileName(File file,String str) {
		File []list = file.listFiles();
		str+="\t";
		for (File file2 : list) {
			if(file2.isDirectory()) {
				System.out.println("\n"+str+file2.getAbsolutePath());
				showFileName(file2,str);
			}else {
				System.out.println(str+file2.getName());
			}
		}
	}
	/**
	 * 从指定文件查找包含str的文件或文件夹
	 * 2018-04-26 17:15:19
	 * @param file 文件
	 * @param str 查找的字符
	 */
	public static void findFile(File file,String str) {
		File []list = file.listFiles();
		for (File file2 : list) {
			if(file2.getName().indexOf(str)>=0) {
				System.out.println(file2.getAbsolutePath());
			}
			if(file2.isDirectory()) {
				findFile(file2, str);
			}
		}
	}
	
	public static void parseFile(File src,File dis,String regex) {
		
		FileReader fr = null;
		BufferedReader br= null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fr = new FileReader(src);
			br = new BufferedReader(fr);
			fw = new FileWriter(dis);
			bw = new BufferedWriter(fw);
			bw.write("<meta charset='utf-8'/>");
			bw.newLine();
			String line ="";
			String title="";
			String root = "//www.runoob.com";
			while((line=br.readLine())!=null) {
				if(line.indexOf(regex)>=0) {
					line = line.substring(line.indexOf("<a"), line.indexOf("</a>")+4);
					System.out.println(line.trim());
					line = line.substring(line.indexOf("<a href=\"")+9,line.length()-1);
					System.out.println("-----"+line);
					title=line.substring(line.indexOf(">")+1,line.indexOf("<"));
					line = line.substring(0,line.indexOf("\""));
					System.out.println(title+"....."+line);
					if(line.indexOf(root)<0) {
						line="http:"+root+line;
					}else {
						if(line.indexOf("http")<0) {
							line="http:"+line;
						}
					}
					bw.write(title+"="+line);
					bw.newLine();
					bw.write("<br/>");
					bw.newLine();
					bw.flush();
				}
			}
			br.close();
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
