package qrcode;

import java.awt.image.BufferedImage;

import com.google.zxing.LuminanceSource;

public class BufferedImageLuminanceSource extends LuminanceSource {
	private final BufferedImage image;
	private final int  left;
	private final int top;
	

	public BufferedImageLuminanceSource(BufferedImage image) {
		this( 0, 0, image.getWidth(), image.getHeight(), image);
	}
	
	public BufferedImageLuminanceSource(int left, int top, int width, int height, BufferedImage image) {
		super(width, height);
		int sourceWidth = image.getWidth();
		int sourceHeight = image.getHeight();
		if(left + width > sourceWidth || top + height > sourceHeight){  
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");  
        }  
  
        for(int y = top; y < top + height; y++){  
            for(int x = left; x < left + width; x++){  
                if((image.getRGB(x, y) & 0xFF000000) == 0){  
                    image.setRGB(x, y, 0xFFFFFFFF); // = white  
                }  
            }  
        }  
  
        this.image = new BufferedImage(sourceWidth, sourceHeight, BufferedImage.TYPE_BYTE_GRAY);  
        this.image.getGraphics().drawImage(image, 0, 0, null);  
        this.left = left;  
        this.top = top; 
	}

	@Override
	public byte[] getRow(int y, byte[] row) {
		if(y < 0 || y >= getHeight()){  
            throw new IllegalArgumentException("Requested row is outside the image: " + y);  
        }  
        int width = getWidth();  
        if(row == null || row.length < width){  
            row = new byte[width];  
        }  
        image.getRaster().getDataElements(left, top + y, width, 1, row); 
        return row;
	}

	@Override
	public byte[] getMatrix() {
		  int width = getWidth();  
	        int height = getHeight();  
	        int area = width * height;  
	        byte[] matrix = new byte[area];  
	        image.getRaster().getDataElements(left, top, width, height, matrix);  
	        return matrix; 
	}

}
