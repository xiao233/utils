package qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.common.BitMatrix;

public class MatrixToImageWriter {
	private static final int BLACK = 0xFF000000;//��������ͼ������ɫ
	private static final int WHITE = 0xFFFFFFFF;//���ڱ���ɫ
	/**
	 * ���ɶ�ά��ͼƬ��ָ����ʽ�ļ�
	 * 2018-05-07 16:15:31
	 * @param matrix ��ά��λ����
	 * @param charset �����ʽ
	 * @param file ����·��
	 * @param logo logo·��
	 * @throws IOException
	 */
	public static void writeToFile(BitMatrix matrix, String charset, File file, String logo) throws IOException {
		BufferedImage bufImg = toBufferedImage(matrix);
		LogoConfig logoConfig = new LogoConfig();
		
		bufImg = logoConfig.LogoMatrix(bufImg, logo);
		if(!ImageIO.write(bufImg, charset, file)) {
			throw new IOException("Count not write an image of format "
					+ charset + " to "+ file);		
			}
	}
	/**
	 * 
	 * 2018-05-08 10:02:46
	* @param matrix ��ά��λ����
	 * @param charset �����ʽ
	 * @param out ͼƬ�������
	 * @param logo logo·��
	 * @throws IOException
	 */
	public static void writeToStream(BitMatrix matrix, String charset, OutputStream out, String logo) throws IOException {
		BufferedImage bufImg = toBufferedImage(matrix);
		LogoConfig logoConfig = new LogoConfig();
		
		bufImg = logoConfig.LogoMatrix(bufImg, logo);
		if(!ImageIO.write(bufImg, charset, out)) {
			throw new IOException("Count not write an image of format "+ charset);		
			}
	}
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image  =  new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				image.setRGB(i, j, (matrix.get(i, j) ? BLACK : WHITE));
			}
		}
		
		return image;
	}
}
