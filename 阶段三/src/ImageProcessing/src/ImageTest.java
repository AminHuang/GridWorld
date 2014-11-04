import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;


public class ImageTest {

	@Before
	public void setUp() throws Exception {
	}

	// java API的读取和myRead的读取是否一样
	@Test
	public void test1() throws IOException {
		FileInputStream in = new FileInputStream("/home/hjm/workspace/ImageProcessing/1.bmp");
		BufferedImage goal = ImageIO.read(in);
		
		ImageIOImplement imageioer = new ImageIOImplement();
		Image image= imageioer.myRead("/home/hjm/workspace/ImageProcessing/1.bmp");
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		BufferedImage test = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        test.getGraphics().drawImage(image, 0, 0, width, height, null);
		

		assertEquals(goal.getWidth(null),image.getWidth(null));
		assertEquals(goal.getHeight(null),image.getHeight(null));
		
		for (int i = 0; i < goal.getWidth(null); i++) {
			for (int j = 0; j < goal.getHeight(null); j++) {
				assertEquals(goal.getRGB(i, j), test.getRGB(i, j));
			}
		}
	}
	
	// 第一张图片的过滤蓝色通道
	@Test
	public void test2() throws IOException {
		FileInputStream in = new FileInputStream("/home/hjm/workspace/ImageProcessing/goal/1_blue_goal.bmp");
		BufferedImage goal = ImageIO.read(in);
		
		ImageIOImplement imageioer = new ImageIOImplement();
		Image image= imageioer.myRead("/home/hjm/workspace/ImageProcessing/1.bmp");
		ImageProcessor processor = new ImageProcessor(); 
		Image blue = processor.showChanelB(image);
		int width = blue.getWidth(null);
		int height = blue.getHeight(null);
		BufferedImage test = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        test.getGraphics().drawImage(blue, 0, 0, width, height, null);
        
        assertEquals(goal.getWidth(null),blue.getWidth(null));
		assertEquals(goal.getHeight(null),blue.getHeight(null));
		
		for (int i = 0; i < goal.getWidth(null); i++) {
			for (int j = 0; j < goal.getHeight(null); j++) {
				assertEquals(goal.getRGB(i, j), test.getRGB(i, j));
			}
		}
	}
	
	// 第一张图片的灰度图片
	@Test
	public void test3() throws IOException {
		FileInputStream in = new FileInputStream("/home/hjm/workspace/ImageProcessing/goal/1_gray_goal.bmp");
		BufferedImage goal = ImageIO.read(in);
		
		ImageIOImplement imageioer = new ImageIOImplement();
		Image image= imageioer.myRead("/home/hjm/workspace/ImageProcessing/1.bmp");
		ImageProcessor processor = new ImageProcessor(); 
		Image gray = processor.showGray(image);
		int width = gray.getWidth(null);
		int height = gray.getHeight(null);
		BufferedImage test = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        test.getGraphics().drawImage(gray, 0, 0, width, height, null);
        
        assertEquals(goal.getWidth(null),gray.getWidth(null));
		assertEquals(goal.getHeight(null),gray.getHeight(null));
		for (int i = 0; i < goal.getWidth(null); i++) {
			for (int j = 0; j < goal.getHeight(null); j++) {
				assertEquals(goal.getRGB(i, j), test.getRGB(i, j));
			}
		}
	}

	// 第一张图片的过滤绿色通道
	@Test
	public void test4() throws IOException {
		FileInputStream in = new FileInputStream("/home/hjm/workspace/ImageProcessing/goal/1_green_goal.bmp");
		BufferedImage goal = ImageIO.read(in);
		
		ImageIOImplement imageioer = new ImageIOImplement();
		Image image= imageioer.myRead("/home/hjm/workspace/ImageProcessing/1.bmp");
		ImageProcessor processor = new ImageProcessor(); 
		Image green = processor.showChanelG(image);
		int width = green.getWidth(null);
		int height = green.getHeight(null);
		BufferedImage test = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        test.getGraphics().drawImage(green, 0, 0, width, height, null);
        
        assertEquals(goal.getWidth(null),green.getWidth(null));
		assertEquals(goal.getHeight(null),green.getHeight(null));
		for (int i = 0; i < goal.getWidth(null); i++) {
			for (int j = 0; j < goal.getHeight(null); j++) {
				assertEquals(goal.getRGB(i, j), test.getRGB(i, j));
			}
		}
	}
	
	// 第一张图片的过滤红色通道
	@Test
	public void test5() throws IOException {
		FileInputStream in = new FileInputStream("/home/hjm/workspace/ImageProcessing/goal/1_red_goal.bmp");
		BufferedImage goal = ImageIO.read(in);
		
		ImageIOImplement imageioer = new ImageIOImplement();
		Image image= imageioer.myRead("/home/hjm/workspace/ImageProcessing/1.bmp");
		ImageProcessor processor = new ImageProcessor(); 
		Image red = processor.showChanelR(image);
		int width = red.getWidth(null);
		int height = red.getHeight(null);
		BufferedImage test = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        test.getGraphics().drawImage(red, 0, 0, width, height, null);
        
        assertEquals(goal.getWidth(null),red.getWidth(null));
		assertEquals(goal.getHeight(null),red.getHeight(null));
		for (int i = 0; i < goal.getWidth(null); i++) {
			for (int j = 0; j < goal.getHeight(null); j++) {
				assertEquals(goal.getRGB(i, j), test.getRGB(i, j));
			}
		}
	}
	
	// 第二张图片的过滤蓝色通道
	@Test
	public void test6() throws IOException {
		FileInputStream in = new FileInputStream("/home/hjm/workspace/ImageProcessing/goal/2_blue_goal.bmp");
		BufferedImage goal = ImageIO.read(in);
		
		ImageIOImplement imageioer = new ImageIOImplement();
		Image image= imageioer.myRead("/home/hjm/workspace/ImageProcessing/2.bmp");
		ImageProcessor processor = new ImageProcessor(); 
		Image blue = processor.showChanelB(image);
		int width = blue.getWidth(null);
		int height = blue.getHeight(null);
		BufferedImage test = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        test.getGraphics().drawImage(blue, 0, 0, width, height, null);
        
        assertEquals(goal.getWidth(null),blue.getWidth(null));
		assertEquals(goal.getHeight(null),blue.getHeight(null));
		
		for (int i = 0; i < goal.getWidth(null); i++) {
			for (int j = 0; j < goal.getHeight(null); j++) {
				assertEquals(goal.getRGB(i, j), test.getRGB(i, j));
			}
		}
	}
	
	// 第二张图片的灰度图片
	@Test
	public void test7() throws IOException {
		FileInputStream in = new FileInputStream("/home/hjm/workspace/ImageProcessing/goal/2_gray_goal.bmp");
		BufferedImage goal = ImageIO.read(in);
		
		ImageIOImplement imageioer = new ImageIOImplement();
		Image image= imageioer.myRead("/home/hjm/workspace/ImageProcessing/2.bmp");
		ImageProcessor processor = new ImageProcessor(); 
		Image gray = processor.showGray(image);
		int width = gray.getWidth(null);
		int height = gray.getHeight(null);
		BufferedImage test = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        test.getGraphics().drawImage(gray, 0, 0, width, height, null);
        
        assertEquals(goal.getWidth(null),gray.getWidth(null));
		assertEquals(goal.getHeight(null),gray.getHeight(null));
		for (int i = 0; i < goal.getWidth(null); i++) {
			for (int j = 0; j < goal.getHeight(null); j++) {
				assertEquals(goal.getRGB(i, j), test.getRGB(i, j));
			}
		}
	}
	
	// 第二张图片的过滤绿色通道
	@Test
	public void test8() throws IOException {
		FileInputStream in = new FileInputStream("/home/hjm/workspace/ImageProcessing/goal/2_green_goal.bmp");
		BufferedImage goal = ImageIO.read(in);
		
		ImageIOImplement imageioer = new ImageIOImplement();
		Image image= imageioer.myRead("/home/hjm/workspace/ImageProcessing/2.bmp");
		ImageProcessor processor = new ImageProcessor(); 
		Image green = processor.showChanelG(image);
		int width = green.getWidth(null);
		int height = green.getHeight(null);
		BufferedImage test = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        test.getGraphics().drawImage(green, 0, 0, width, height, null);
        
        assertEquals(goal.getWidth(null),green.getWidth(null));
		assertEquals(goal.getHeight(null),green.getHeight(null));
		for (int i = 0; i < goal.getWidth(null); i++) {
			for (int j = 0; j < goal.getHeight(null); j++) {
				assertEquals(goal.getRGB(i, j), test.getRGB(i, j));
			}
		}
	}

	// 第一张图片的过滤红色通道
	@Test
	public void test9() throws IOException {
		FileInputStream in = new FileInputStream("/home/hjm/workspace/ImageProcessing/goal/2_red_goal.bmp");
		BufferedImage goal = ImageIO.read(in);
		
		ImageIOImplement imageioer = new ImageIOImplement();
		Image image= imageioer.myRead("/home/hjm/workspace/ImageProcessing/2.bmp");
		ImageProcessor processor = new ImageProcessor(); 
		Image red = processor.showChanelR(image);
		int width = red.getWidth(null);
		int height = red.getHeight(null);
		BufferedImage test = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        test.getGraphics().drawImage(red, 0, 0, width, height, null);
        
        assertEquals(goal.getWidth(null),red.getWidth(null));
		assertEquals(goal.getHeight(null),red.getHeight(null));
		for (int i = 0; i < goal.getWidth(null); i++) {
			for (int j = 0; j < goal.getHeight(null); j++) {
				assertEquals(goal.getRGB(i, j), test.getRGB(i, j));
			}
		}
	}
}
