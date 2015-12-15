package com.zwli.research.image;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.zwli.research.regex.RegexUtils;

/**
 * 图片上传到服务器后，会根据情况将图片缩小成一个图标，我们可以利用java强大的图形处理功能，<br>
 * 对上传的图片进行缩放处理。下面的程序使用jdk1.4中最新的ImageIO对图片进行读写。<br>
 * 使用AffineTransform对图片进行缩放。<br>
 * 创建日期：2007-11-15
 *
 * @author 李赵伟
 */
public class UploadImg {

    /**
     * 图片上传到服务器后，会根据情况将图片缩小成一个图标，我们可以利用java强大的图形处理功能，<br>
     * 对上传的图片进行缩放处理。下面的程序使用jdk1.4中最新的ImageIO对图片进行读写。<br>
     * 使用AffineTransform对图片进行缩放.
     *
     * <pre>
     * 该程序使用了Java 的AWT,在linux下运行可能报错，有两种解决方式：
     * (1) jdk1.4以前版本：需要安装 XFree86和XFree86-Xvfb ,加入 export DISPLAY=hostdomain:0.0
     * (2) jdk1.4以后版本，执行命令java 加入参数-Djava.awt.headless=true，表示这是一个没有键盘 没有显示器的无头服务器，意称机房托管的服务器。
     * </pre>
     *
     * @param fromdir 图片的原始目录
     * @param todir 处理后的图片存放目录
     * @param imgfile 原始图片
     * @param sysimgfile 处理后的图片文件名前缀
     * @return
     * @throws IOException
     */
    public static final boolean createThumbnail(String fromdir, String imgfile, String todir, String sysimgfile)
            throws IOException {
        // ext是图片的格式 gif JPG 或png
        String ext = "png";
        double Ratio = 0.0;
        File file = new File(fromdir, imgfile);
        if (!file.isFile()) {
            throw new IOException(file + " is not image file error in CreateThumbnail!");
        }

        // 首先判断上传的图片是gif还是JPG ImageIO只能将gif转换为png
        if (isJpg(imgfile)) {
            ext = "jpg";
        }

        File f = new File(todir, sysimgfile + "." + ext);
        BufferedImage bi = ImageIO.read(file);
        // 假设图片宽 高 最大为120 120
        Image itemp = bi.getScaledInstance(120, 120, BufferedImage.SCALE_SMOOTH);

        if ((bi.getHeight() > 120) || (bi.getWidth() > 120)) {
            if (bi.getHeight() > bi.getWidth()) {
                Ratio = 120.0 / bi.getHeight();
            } else {
                Ratio = 120.0 / bi.getWidth();
            }
        }

        AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(Ratio, Ratio), null);
        itemp = op.filter(bi, null);

        try {
            ImageIO.write((BufferedImage) itemp, ext, f);
        } catch (IOException ex) {
            throw new IOException(" ImageIo.write error in CreatThum.: " + ex.getMessage());
        }
        return true;
    }

    private static final boolean isJpg(String imgfile) {
        String regex = ".jpg";
        int cou = RegexUtils.matcheRegexp(imgfile, regex, false);
        return (0 != cou ? true : false);
    }
}
