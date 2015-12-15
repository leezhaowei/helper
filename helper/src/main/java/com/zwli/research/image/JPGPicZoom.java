package com.zwli.research.image;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 缩略图实现，将图片(jpg格式)真实的变成想要的大小<br>
 * 缩略图类，本java类能将jpg图片文件，进行等比或非等比的大小转换。<br>
 * 创建日期：2007-11-15
 *
 * @author 李赵伟
 */
@SuppressWarnings("restriction")
public class JPGPicZoom {

    /** * 输入图路径 */
    private String inputDir;

    /** * 输出图路径 */
    private String outputDir;

    /** * 输入图文件名 */
    private String inputFileName;

    /** * 输出图文件名 */
    private String outputFileName;

    /** * 默认输出图片宽 */
    private int outputWidth = 128;

    /** * 默认输出图片高 */
    private int outputHeight = 128;

    /** * 是否等比缩放标记(默认为等比缩放) */
    private boolean proportion = true;

    public void setInputDir(String inputDir) {
        this.inputDir = inputDir;
    }

    public void setinputFileName(String outputDir) {
        this.outputDir = outputDir;
    }

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public void setOutputWidth(int OutputWidth) {
        this.outputWidth = OutputWidth;
    }

    public void setOutputHeight(int OutputHeight) {
        this.outputHeight = OutputHeight;
    }

    public void setWidthAndHeight(int width, int height) {
        this.outputWidth = width;
        this.outputHeight = height;
    }

    /**
     * @return 图片缩放
     */
    private boolean reduceJPGPic() {
        Image img = null;
        Toolkit tk = Toolkit.getDefaultToolkit();
        MediaTracker mt = new MediaTracker(new Applet());
        img = tk.createImage(inputDir + inputFileName);
        mt.addImage(img, 0);
        try {
            mt.waitForID(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        if (-1 == img.getWidth(null)) {
            System.out.println("Can't read, retry!");
            return false;
        } else {
            int new_w;
            int new_h;

            // 判断是否是等比缩放
            if (proportion) {
                double width = img.getWidth(null);
                double height = img.getHeight(null);
                // 为等比缩放计算输出的图片宽度及高度
                double rate1 = width / outputWidth + 0.1;
                double rate2 = height / outputHeight + 0.1;
                double rate = rate1 > rate2 ? rate1 : rate2;
                new_w = (int) (width / rate);
                new_h = (int) (height / rate);
            } else {
                new_w = outputWidth; // 输出的图片宽度
                new_h = outputHeight; // 输出的图片高度
            }
            BufferedImage buffImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);

            Graphics g = buffImg.createGraphics();

            g.setColor(Color.white);
            g.fillRect(0, 0, new_w, new_h);

            g.drawImage(img, 0, 0, new_w, new_h, null);
            g.dispose();

            FileOutputStream tempout = null;
            try {
                try {
                    File file = new File(outputDir + outputFileName);
                    tempout = new FileOutputStream(file);
                    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(tempout);
                    encoder.encode(buffImg);
                    tempout.close();
                } finally {
                    if (null != tempout) {
                        tempout.close();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        System.out.println("图片缩放完成！");
        return false;
    }

    /**
     * JPG格式图片缩放
     *
     * @param inputDir 要缩放的图片源地址
     * @param outputDir 缩放后图片输出地址
     * @param inputFileName 原图片名称，JPG格式
     * @param outputFileName 缩放后图片名称
     * @return <code> true </code>，缩放成功；否则返回<code> false </code>
     */
    public boolean reduceJPGPic(String inputDir, String outputDir, String inputFileName, String outputFileName) {
        // 输入图路径
        this.inputDir = inputDir;
        // 输出图路径
        this.outputDir = outputDir;
        // 输入图文件名
        this.inputFileName = inputFileName;
        // 输出图文件名
        this.outputFileName = outputFileName;
        return reduceJPGPic();
    }

    /**
     * JPG格式图片缩放
     *
     * @param inputDir 要缩放的图片源地址
     * @param outputDir 缩放后图片输出地址
     * @param inputFileName 原图片名称，JPG格式
     * @param outputFileName 缩放后图片名称
     * @param width 图片缩放后的宽度
     * @param height 图片缩放后的高度
     * @param proportion true，图片按照比例缩放；false，图片使用给定的宽度和高度进行缩放
     * @return <code> true </code>，缩放成功；否则返回<code> false </code>
     */
    public boolean reduceJPGPic(String inputDir, String outputDir, String inputFileName, String outputFileName, int width,
            int height, boolean proportion) {
        this.inputDir = inputDir;
        this.outputDir = outputDir;
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        setWidthAndHeight(width, height);
        this.proportion = proportion;
        return reduceJPGPic();
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        new JPGPicZoom().reduceJPGPic("D:/temp/", "D:/temp/", "car.jpg", "car1.jpg", 400, 400, true);
    }
}
