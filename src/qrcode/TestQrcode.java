package qrcode;
import org.junit.Test;

import com.google.zxing.Result;
public class TestQrcode {
	@Test
	public void testQrcode() {
		/**
		 * qrcode
		 */
		String path = "C:\\Users\\Administrator\\Desktop\\qrcode.png";
		QrcodeOperation.createQrcode("大爷6啊", "utf-8","png", path, 20);
		String content = QrcodeOperation.decoderCode(path,"utf-8");
		System.out.println(content);
	}
	@Test
	public void qrcode() {
		String path = "C:\\Users\\Administrator\\Desktop\\qrcode1.jpg";
		String logo = "C:\\Users\\Administrator\\Desktop\\logo.jpg";
		ZXingOperation.encode("https://www.sina.com", "utf-8", path, "jpg", 20, logo);
		Result result = ZXingOperation.decode(path, "utf-8");
		try {
			String content = result.getText();
			//if( content!=null) {
				
				System.out.println("图片中内容：  ");  
				System.out.println("author： " + content );  
			//}
			System.out.println("图片中格式：  ");  
			System.out.println("encode： " + result.getBarcodeFormat());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		
	}
}
