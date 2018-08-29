package qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

/**
 * 生成和解析二维码(qrCode)
 * @author xjl
 * 2018-05-07 13:56:54
 */
public class QrcodeOperation {
	/**
	 * 解析二维码图片
	 * 2018-05-07 13:59:38
	 * @param filePath 二维码图片路劲
	 * @param charset 设置内容编码方式
	 * @return
	 */
	public static String decoderCode(String filePath, String charset) {
		String content = null;
		File file = new File(filePath);
		
		if(charset==null) {
			charset = "utf-8";
		}
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(file);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new QRCodeImageBean(image)), charset);
		} catch (DecodingFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return content;
	}
	/**
	 * 生成二维码图片
	 * 2018-05-07 14:28:22
	 * @param content 内容
	 * @param charset 设置转码方式
	 * @param type 生成图片类型
	 * @param path 生成图片位置
	 * @param size 生成图片大小
	 */
	public static void createQrcode(String content, String charset, String type, String path, int size) {
		BufferedImage bufImg = null;
		if(charset == null) {
			charset = "utf-8";
		}
		try {
			Qrcode qrcode = new Qrcode();
			/**
			 *  设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，
			 *  排错率越高可存储的信息越少，但对二维码清晰度的要求越小 
			 */
			qrcode.setQrcodeErrorCorrect('M');
			qrcode.setQrcodeEncodeMode('B');
			// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
			qrcode.setQrcodeVersion(size);
			// 获得内容的字节数组，设置编码格式
			byte []contentBytes = content.getBytes(charset);
			//图片尺寸
			int imgSize = 67 + 12 * (size - 1);
			bufImg = new BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_RGB);
			 Graphics2D gs = bufImg.createGraphics();    
			 // 设置背景颜色    
			 gs.setBackground(Color.WHITE);    
			 gs.clearRect(0, 0, imgSize, imgSize);    
 
			 // 设定图像颜色> BLACK    
			 gs.setColor(Color.BLACK);    
			 // 设置偏移量，不设置可能导致解析出错    
			 int pixoff = 2;    
			 // 输出内容> 二维码    
			 if (contentBytes.length > 0 && contentBytes.length < 800) {    
			     boolean[][] codeOut = qrcode.calQrcode(contentBytes);    
			     for (int i = 0; i < codeOut.length; i++) {    
			         for (int j = 0; j < codeOut.length; j++) {    
			             if (codeOut[j][i]) {    
			                 gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);    
			             }    
			         }    
			     }    
			 } else {    
			     throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");    
			 }    
			 gs.dispose();    
			 bufImg.flush();
			 ImageIO.write(bufImg, type, new File(path));  
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
