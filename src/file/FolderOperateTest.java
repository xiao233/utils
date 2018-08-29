package file;
import java.io.File;

import org.junit.Test;
public class FolderOperateTest {
	@Test
	public void test() {
		String srcName = "C:\\Users\\Administrator\\Desktop\\aa.html";
		String disName = "C:\\Users\\Administrator\\Desktop\\bb.html";
		String regx ="<a href=";
		FolderOperate.parseFile(new File(srcName), new File(disName), regx);
	}
}
