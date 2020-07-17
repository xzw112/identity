package com.tiptimes.identity.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验证码生成
 * 
 * @author Administrator
 *
 */
public class ValidateCodeUtil {
    // 图片的宽度。
    private int width = 160;
    // 图片的高度。
    private int height = 40;
    // 验证码字符个数
    private int codeCount = 4;
    // 验证码干扰线数
    private int lineCount = 100;
    // 验证码
    private String code = null;
    // 验证码图片Buffer
    private BufferedImage buffImg = null;

    // 验证码范围,去掉0(数字)和O(拼音)容易混淆的(小写的1和L也可以去掉,大写不用了)
    private char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9','a','b','c','d','e','f','g',
            'h','m','n','p','q','r','s','t','u','v','w','x','y','z'};

    /**
     * 默认构造函数,设置默认参数
     */
    public ValidateCodeUtil() {
        this.createCode();
    }

    /**
     * @param width  图片宽
     * @param height 图片高
     */
    public ValidateCodeUtil(int width, int height) {
        this.width = width;
        this.height = height;
        this.createCode();
    }

    /**
     * @param width     图片宽
     * @param height    图片高
     * @param codeCount 字符个数
     * @param lineCount 干扰线条数
     */
    public ValidateCodeUtil(int width, int height, int codeCount, int lineCount) {
        this.width = width;  
        this.height = height;  
        this.codeCount = codeCount;  
        this.lineCount = lineCount;  
        this.createCode();  
    }  

    public void createCode() {  
        int x = 0, fontHeight = 0, codeY = 0;  
        x = width / (codeCount + 2);//每个字符的宽度(左右各空出一个字符)  
        fontHeight = height - 4;//字体的高度  
        codeY = height - 4;  

        // 图像buffer  
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        Graphics2D g = buffImg.createGraphics();  
        
        // 将图像填充为白色  
        g.setColor(new Color(210,221,227)); 
        g.fillRect(0, 0, width, height);  
        
        // 创建字体,可以修改为其它的  
        Font font = new Font("AlibabaPuHuiTiM", Font.PLAIN, fontHeight);
        g.setFont(font);  

        // 生成随机数  
        Random random = new Random();  
        for (int i = 0; i < lineCount; i++) {  
            // 设置随机开始和结束坐标  
            int xs = random.nextInt(width);//x坐标开始  
            int ys = random.nextInt(height);//y坐标开始  
            int xe = xs + random.nextInt(width / 8);//x坐标结束  
            int ye = ys + random.nextInt(height / 8);//y坐标结束  

            // 产生随机的颜色值，让输出的每个干扰线的颜色值都将不同。  
            g.drawLine(xs, ys, xe, ye);  
        }  

        // randomCode记录随机产生的验证码  
        StringBuffer randomCode = new StringBuffer();  
        // 随机产生codeCount个字符的验证码。  
        for (int i = 0; i < codeCount; i++) {  
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);  
            // 产生随机的颜色值，让输出的每个字符的颜色值都将不同。  
            g.setColor(new Color(42, 93, 124));  
            g.drawString(strRand, (i + 1) * x, codeY);  
            // 将产生的四个随机数组合在一起。  
            randomCode.append(strRand);  
        }  
        // 将四位数字的验证码保存到Session中。  
        code = randomCode.toString();  
    }  

    public void write(String path) throws IOException {  
        OutputStream sos = new FileOutputStream(path);  
        this.write(sos);  
    }  

    public void write(OutputStream sos) throws IOException {  
        ImageIO.write(buffImg, "png", sos);  
        sos.close();  
    }  

    
    public BufferedImage getBuffImg() {  
        return buffImg;  
    }  

    public String getCode() {  
        return code;  
    }  

}
