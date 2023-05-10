package com.volta.project.common;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class HistogramMethod {

    //    public static int[] getData(BufferedImage img) {
    public static String getData(BufferedImage img) {
        BufferedImage slt = new BufferedImage(100, 100,
                BufferedImage.TYPE_INT_RGB);
        slt.getGraphics().drawImage(img, 0, 0, 100, 100, null);
        int[] data = new int[256];
        for (int x = 0; x < slt.getWidth(); x++) {
            for (int y = 0; y < slt.getHeight(); y++) {
                int rgb = slt.getRGB(x, y);
                Color myColor = new Color(rgb);
                int r = myColor.getRed();
                int g = myColor.getGreen();
                int b = myColor.getBlue();
                data[(r + g + b) / 3]++;
            }
        }
        //将图片处理为字符串存入数据库作为图像特征
        StringBuilder dataStr = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            dataStr.append(data[i]);
            dataStr.append(',');
        }
        return dataStr.toString();//直方图
    }

//    public static float compare(int[] s, int[] t) {
//        try {
//            float result = 0F;
//            for (int i = 0; i < 256; i++) {
//                int abs = Math.abs(s[i] - t[i]);
//                int max = Math.max(s[i], t[i]);
//                result += (1 - ((float) abs / (max == 0 ? 1 : max)));
//            }
//            return (result / 256) * 100;
//        } catch (Exception exception) {
//            return 0;
//        }
//    }

    public static float compare(String str1, String str2) {
        String[] s = str1.split(",");
        String[] t = str2.split(",");
        try {
            float result = 0F;
            for (int i = 0; i < 256; i++) {
                int abs = Math.abs(Integer.parseInt(s[i]) - Integer.parseInt(t[i]));
                int max = Math.max(Integer.parseInt(s[i]), Integer.parseInt(t[i]));
                result += (1 - ((float) abs / (max == 0 ? 1 : max)));
            }
            return (result / 256) * 100;
        } catch (Exception exception) {
            return 0;
        }
    }

}
