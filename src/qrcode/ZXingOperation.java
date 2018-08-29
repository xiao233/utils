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
 * 生成和解析二维码(google.zxing)
 * @author xjl
 * 2018-05-07 15:12:04
 */
public class ZXingOperation {
	/**
	 * 生成二维码(zxing,带logo)
	 * 2018-05-07 16:20:09
	 * @param content 内容
	 * @param charset 编码方式
	 * @param path 保存路劲
	 * @param type 保存文件格式
	 * @param size 图片大小
	 * @param logo logo位置
	 */
	public static void encode(String content, String charset, String path, String type, int size, String logo) {
		 Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>(); 
		 //设置内容对的编码方式
		 hints.put(EncodeHintType.CHARACTER_SET, charset);
		 //设置纠错级别
		 hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		 //设置二维码边的空度，非负数
		 hints.put(EncodeHintType.MARGIN, 1);
		 
		 int imgSize = 67 + 12 * (size - 1);
	        try {
				BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
				        BarcodeFormat.QR_CODE, 
				        imgSize, //宽度
				        imgSize, //高度
				        hints);// 生成矩阵  
				MatrixToImageWriter.writeToFile(bitMatrix, type, new File(path),logo);// 输出图像
			} catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/**
	 * 解析二维码
	 * 2018-05-07 17:56:17
	 * @param filePath 二维码位置
	 * @param charset 二维码编码方式
	 * @return 包含二维码所有信息的结果集
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
			//优化精度
			hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
			//复杂模式，开启PURE_BARCODE模式
			hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
			result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行
			
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
