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

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import imagereader.IImageIO;

/**
 * 读取与保存位图
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ImageIOImplement implements IImageIO{
    private static final int TWENTYFOUR = 24;
    private static final int SIXTEEN = 16;
    private static final int EIGHT = 8;
    private static final int EIGHTEEN = 18;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FF = 0xff;
    private static final int TRANSPARENT = 255;

    /**
     * 利用二进制流读取Bitmap位图文件
     * 注意，这里要求不能使用Java提供的API直接读取图像
     * 根据二进制数据创建Image时可以使用API
     * The implementation of this class is testable on the AP CS A and AB exams.
     */
    @Override
    public Image myRead(String arg0) throws IOException {
        Image image;
        try {
            FileInputStream fi = new FileInputStream(arg0);
            
            // 位图头和位图信息总共 54 byte
            // 跳过一些无用的字节信息
            byte info[] = new byte[FOUR];
            byte temp[] = new byte[TWO];
            fi.skip(EIGHTEEN);
            fi.read(info,0,FOUR);
            
            // 字节 #18-21    保存位图宽度（以像素个数表示）
            // 位运算操作，保存位图宽度
            // 与运算保证强制性转换为int型的时候符号为是0而不是1
            int a = ((int)info[THREE] & FF) << TWENTYFOUR;
            int b = ((int)info[TWO] & FF) << SIXTEEN;
            int c = ((int)info[1] & FF) << EIGHT;
            int d = (int)info[0] & FF;
            int width = a | b | c | d;
            
            fi.read(info,0,FOUR);
            
            // 字节 #22-25    保存位图高度（以像素个数表示）。
            int height = (((int)info[THREE] & FF) << TWENTYFOUR)
                    | (((int)info[TWO] & FF) << SIXTEEN)
                    | (((int)info[1] & FF) << EIGHT)
                    | (((int)info[0] & FF) ); 
            
            fi.skip(TWO);
            fi.read(temp,0,TWO);
            
            //  字节 #28-29    保存每个像素的位数，它是图像的颜色深度。常用值是1、4、8（灰阶）和24（彩色）。
            int bitCount = (((int)temp[1] & FF) << EIGHT) | ((int)temp[0] & FF);
            
            fi.skip(FOUR);
            fi.read(info,0,FOUR);
            
            // 字节 #34-37    保存图像大小。这是原始位图数据的大小，不要与文件大小混淆
            int imageSize = (((int)info[THREE] & FF) << TWENTYFOUR)
                    | (((int)info[TWO] & FF) << SIXTEEN)
                    | (((int)info[1] & FF) << EIGHT)
                    | (((int)info[0] & FF) );
            
            fi.skip(SIXTEEN);
            
            // 像素对齐
            // 若像素使用的字节不是4的倍数，会有空白自动填充
            // 需要一开始计算出空白的大小
            // 空白的大小变量为npad
            // 因为实训给的图片是彩色的，自动默认bitCount为24位
            if (bitCount == TWENTYFOUR) {
                // 在24位图像里面，一个像素占3个字节
                int npad = ((imageSize / height) - width * THREE);
                
                // 填充4个空白相当于不需要填充
                if (npad == FOUR) {
                    npad = 0;
                }
                
                int data[] = new int[height * width];
                // 存放像素数据的数组
                byte rgb[] = new byte[imageSize];
                
                // 读入
                fi.read(rgb, 0, imageSize);
                
                int index = 0;
                for (int j = 0; j < height; j++) {
                    for (int i = 0; i < width; i++) {
                        int t = width * (height - j - 1) + i;
                        // TRANSPARENT 透明度
                        data[t] = (TRANSPARENT & FF) << TWENTYFOUR
                                | (((int)rgb[index + TWO] & FF) << SIXTEEN)
                                | (((int)rgb[index + 1] & FF) << EIGHT)
                                | (int)rgb[index] & FF;
                        index += THREE;
                    }
                    index += npad;
                }
                // 构造一个使用默认 RGB ColorModel 中的整数数组为 Image 对象生成数据的 ImageProducer 对象：
                // w - 像素矩形的宽度
                // h - 像素矩形的高度
                // pix - 一个像素数组
                // off - 数组中存储首个像素的偏移量
                // scan - 数组中一行像素到下一行像素之间的距离
                image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(  
                           width, height, data, 0, width)); 
            } else {
                image = (Image)null;
            }
            
            fi.close();
            return image;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
    /**
     * 把处理完的图像保存为jpg格式图像
     * 注意，这里可以使用Java提供的API完成
     * The implementation of this class is testable on the AP CS A and AB exams.
     */
    @Override
    public Image myWrite(Image arg0, String arg1) throws IOException {
        try {          
            int width = arg0.getWidth(null);
            int height = arg0.getHeight(null);
            
            File imageFile = new File(arg1);
            
            // TYPE_INT_RGB 表示一个图像，它具有合成整数像素的 8 位 RGB 颜色分量
            BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            // Graphics2D，可以将图像绘制到此 BufferedImage 中
            bufImage.getGraphics().drawImage(arg0, 0, 0, width, height, null);
            // 使用支持给定格式的任意 ImageWriter 将一个图像写入 File
            ImageIO.write(bufImage, "jpg", imageFile);
            
            return arg0;
            
            
        } catch (Exception e) {
            
        }
        
        return null;
    }

}
