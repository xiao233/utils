package qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * ���ɺͽ�����ά��(google.zxing)
 * @author xjl
 * 2018-05-07 15:12:04
 */
public class ZXingOperation {
	/**
	 * ���ɶ�ά��(zxing,��logo)
	 * 2018-05-07 16:20:09
	 * @param content ����
	 * @param charset ���뷽ʽ
	 * @param path ����·��
	 * @param type �����ļ���ʽ
	 * @param size ͼƬ��С
	 * @param logo logoλ��
	 */
	public static void encode(String content, String charset, String path, String type, int size, String logo) {
		 Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>(); 
		 //�������ݶԵı��뷽ʽ
		 hints.put(EncodeHintType.CHARACTER_SET, charset);
		 //���þ�����
		 hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		 //���ö�ά��ߵĿնȣ��Ǹ���
		 hints.put(EncodeHintType.MARGIN, 1);
		 
		 int imgSize = 67 + 12 * (size - 1);
	        try {
				BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
				        BarcodeFormat.QR_CODE, 
				        imgSize, //���
				        imgSize, //�߶�
				        hints);// ���ɾ���  
				MatrixToImageWriter.writeToFile(bitMatrix, type, new File(path),logo);// ���ͼ��
			} catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/**
	 * ������ά��
	 * 2018-05-07 17:56:17
	 * @param filePath ��ά��λ��
	 * @param charset ��ά����뷽ʽ
	 * @return ������ά��������Ϣ�Ľ����
	 */
	public static Result decode(String filePath, String charset) {
		Result result  = null;
		BufferedImage bufImg = null ;
		
		try {
			bufImg = ImageIO.read(new File(filePath));
			LuminanceSource source = new BufferedImageLuminanceSource(bufImg);
			Binarizer binarizer = new HybridBinarizer(source);  
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
			Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();  
			hints.put(DecodeHintType.CHARACTER_SET, charset); 
			//�Ż�����
			hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
			//����ģʽ������PURE_BARCODEģʽ
			hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
			result = new MultiFormatReader().decode(binaryBitmap, hints);// ��ͼ�����
			
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
