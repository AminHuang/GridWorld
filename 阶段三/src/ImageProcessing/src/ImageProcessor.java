import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

import imagereader.IImageProcessor;

/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Amin
 */

/**
 * 简单的图像处理
 * JAVA中存在用于过滤图像的专用类RGBImageFilter，我们只需要对其进行派生即可
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ImageProcessor implements IImageProcessor{
    private static final int SIXTEEN = 16;
    private static final int EIGHT = 8;
    
    private static final double GR = 0.299;
    private static final double GG = 0.587;
    private static final double GB = 0.114;
    
    private static final int FILTERRED = 0xffff0000;
    private static final int FILTERGREEN = 0xff00ff00;
    private static final int FILTERBLUE = 0xff0000ff;
    
    private static final int RED = 0x00ff0000;
    private static final int GREEN = 0x0000ff00;
    private static final int BLUE = 0x000000ff;
    
    private Toolkit kit = Toolkit.getDefaultToolkit();
    
    /**
     *  红色通道
     *  使用参考RGBImageFilter的API文档
     */
    class RedSwapFilter extends RGBImageFilter {
        /**
         *  constructor
         */
        public RedSwapFilter() {
            canFilterIndexColorModel = true;
        }

        /**
         *  对于每一个像素单元，由三个字节存储RGB三色，还有一个字节用于存储透明度
         *  四个字节的排序为 透明度-红-绿-蓝，由于是16进制并且透明度不改变
         *  因此可以用形如0xffffffff过滤器与色素相与得到指定通道
         *  红色：0xffff0000 
         *  绿色：0xff00ff00 
         *  蓝色：0xff0000ff
         */
        @Override
        public int filterRGB(int arg0, int arg1, int arg2) {
            return (arg2 & FILTERRED);
        }
    }
    
    /**
     *  提取并且显示彩色图像红色通道
     * The implementation of this class is testable on the AP CS A and AB exams.
     */
    @Override
    public Image showChanelR(Image arg0) {
        RedSwapFilter red = new RedSwapFilter();
        return kit.createImage(new FilteredImageSource(arg0.getSource(),red));
    }
    
    /**
     *  绿色通道
     */
    class GreenSwapFilter extends RGBImageFilter {
        /**
         *  constructor
         */
        public GreenSwapFilter() {
            canFilterIndexColorModel = true;
        }

        /**
         *  因此可以用形如0xffffffff过滤器与色素相与得到指定通道
         *  绿色：0xff00ff00 
         */
        @Override
        public int filterRGB(int arg0, int arg1, int arg2) {
            
            return (arg2 & FILTERGREEN);
        }
    }
    
    /**
     *  提取并且显示彩色图像绿色通道
     * The implementation of this class is testable on the AP CS A and AB exams.
     */
    @Override
    public Image showChanelG(Image arg0) {
        GreenSwapFilter green = new GreenSwapFilter();
        return kit.createImage(new FilteredImageSource(arg0.getSource(),green));
    }
    
    /**
     *  蓝色通道
     */
    class BlueSwapFilter extends RGBImageFilter {
        /**
         *  constructor
         */
        public BlueSwapFilter() {
            canFilterIndexColorModel = true;
        }

        /**
         *  因此可以用形如0xffffffff过滤器与色素相与得到指定通道
         *  蓝色：0xff0000ff 
         */
        @Override
        public int filterRGB(int arg0, int arg1, int arg2) {
            return (arg2 & FILTERBLUE);
        }
    }
    
    /**
     *  提取并且显示彩色图像蓝色通道
     * The implementation of this class is testable on the AP CS A and AB exams.
     */
    @Override
    public Image showChanelB(Image arg0) {
        BlueSwapFilter blue = new BlueSwapFilter();
        return kit.createImage(new FilteredImageSource(arg0.getSource(),blue));
    }

    

    /**
     *  灰色
     */
    class GraySwapFilter extends RGBImageFilter {
        /**
         *  constructor
         */
        public GraySwapFilter() {
            canFilterIndexColorModel = true;
        }

        /**
         *  建议采用NTSC推荐的彩色图到灰度图的转换公式：
         *  I = 0.299 * R + 0.587 * G + 0.114 * B 
         */
        @Override
        public int filterRGB(int arg0, int arg1, int arg2) {
            
            int red = (arg2 & RED) >> SIXTEEN;
            int green = (arg2 & GREEN) >> EIGHT;        
            int blue = (arg2 & BLUE);
            int gray = (int)((double)(GR * red) + (double)(GG * green) + (double)(GB * blue));
            return (arg2 & 0xff000000)+(gray<<SIXTEEN)+(gray<<EIGHT)+gray;
        }
    }

    /**
     *  把读取彩色图像转换成灰度图像
     * The implementation of this class is testable on the AP CS A and AB exams.
     */
    @Override
    public Image showGray(Image arg0) {
        GraySwapFilter gray = new GraySwapFilter();
        return kit.createImage(new FilteredImageSource(arg0.getSource(),gray));
    }

}
