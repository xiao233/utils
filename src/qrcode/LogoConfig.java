package qrcode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * ��ά�� ���logoͼ�� ����ķ���
 * @author xjl
 * 2018-05-07 15:52:03
 */
public class LogoConfig {
	/**
	 * ����logo
	 * 2018-05-07 16:11:02
	 * @param matrixImage ԭ��ά��ͼƬ
	 * @param file Ҫ����ͼƬ
	 * @return ����logo�Ķ�ά��ͼƬ
	 * @throws IOException
	 */
	public BufferedImage LogoMatrix(BufferedImage matrixImage, String file) throws IOException {
		
		/**
		 * ��ȡ��ά��ͼƬ����������ͼ����
		 */
		Graphics2D g2 = matrixImage.createGraphics();
		int matriWidth = matrixImage.getWidth();
		int martriHeight = matrixImage.getHeight();
		/**
		 * ��ȡLogoͼƬ
		 */
		BufferedImage logo = ImageIO.read(new File(file));
		//��ʼ����ͼƬ
		g2.drawImage(logo, matriWidth/5*2, martriHeight/5*2, matriWidth/5, martriHeight/5, null);
		
		BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g2.setStroke(stroke);//���ñʻ�����
		RoundRectangle2D.Float round = new RoundRectangle2D.Float( matriWidth/5*2, martriHeight/5*2, matriWidth/5, martriHeight/5, 20, 20);
		g2.setColor(Color.white);
		g2.draw(round);//����Բ������
		
		
		//����logo��һ����ɫ�߿�
		BasicStroke stroke2 = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		g2.setStroke(stroke2);
		RoundRectangle2D.Float round2 = new RoundRectangle2D.Float( matriWidth/5*2+2, martriHeight/5*2+2, matriWidth/5-4, martriHeight/5-4, 20, 20);
		g2.setColor(new Color(128,128,128));
		g2.draw(round2);//����Բ������
		
		g2.dispose();
		matrixImage.flush();
		return matrixImage;
	}
}
