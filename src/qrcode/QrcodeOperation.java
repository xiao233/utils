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
 * ���ɺͽ�����ά��(qrCode)
 * @author xjl
 * 2018-05-07 13:56:54
 */
public class QrcodeOperation {
	/**
	 * ������ά��ͼƬ
	 * 2018-05-07 13:59:38
	 * @param filePath ��ά��ͼƬ·��
	 * @param charset �������ݱ��뷽ʽ
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
	 * ���ɶ�ά��ͼƬ
	 * 2018-05-07 14:28:22
	 * @param content ����
	 * @param charset ����ת�뷽ʽ
	 * @param type ����ͼƬ����
	 * @param path ����ͼƬλ��
	 * @param size ����ͼƬ��С
	 */
	public static void createQrcode(String content, String charset, String type, String path, int size) {
		BufferedImage bufImg = null;
		if(charset == null) {
			charset = "utf-8";
		}
		try {
			Qrcode qrcode = new Qrcode();
			/**
			 *  ���ö�ά���Ŵ��ʣ���ѡL(7%)��M(15%)��Q(25%)��H(30%)��
			 *  �Ŵ���Խ�߿ɴ洢����ϢԽ�٣����Զ�ά�������ȵ�Ҫ��ԽС 
			 */
			qrcode.setQrcodeErrorCorrect('M');
			qrcode.setQrcodeEncodeMode('B');
			// �������ö�ά��ߴ磬ȡֵ��Χ1-40��ֵԽ��ߴ�Խ�󣬿ɴ洢����ϢԽ��
			qrcode.setQrcodeVersion(size);
			// ������ݵ��ֽ����飬���ñ����ʽ
			byte []contentBytes = content.getBytes(charset);
			//ͼƬ�ߴ�
			int imgSize = 67 + 12 * (size - 1);
			bufImg = new BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_RGB);
			 Graphics2D gs = bufImg.createGraphics();    
			 // ���ñ�����ɫ    
			 gs.setBackground(Color.WHITE);    
			 gs.clearRect(0, 0, imgSize, imgSize);    
 
			 // �趨ͼ����ɫ> BLACK    
			 gs.setColor(Color.BLACK);    
			 // ����ƫ�����������ÿ��ܵ��½�������    
			 int pixoff = 2;    
			 // �������> ��ά��    
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
