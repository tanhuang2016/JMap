package xyz.hashdog.dm.util;


import xyz.hashdog.dm.bean.Text;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class ImageUtil {



    public static int labelsMaxWidth(Font font, List<String> labels) {
        int max = 0;
        for (String label : labels) {
            int i = widthByString(font, label);
            if (i > max) max = i;
        }
        return max;
    }


    public static int widthByString(Font font, String text) {
        java.awt.FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(font);
        int w = fm.stringWidth(text);
        return w;
    }

    public static int highByFont(Font font) {
        java.awt.FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(font);
        return fm.getHeight();
    }

    public static void DrawString(BufferedImage image, String text, int x, int y, Font font, String fontColor) {
        // 获取Graphics2D
        Graphics2D g2d = image.createGraphics();
        try {
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            if (font != null)
                g2d.setFont(font);
            if (fontColor != null)
                g2d.setColor(Color.decode(fontColor));
            FontMetrics fm = g2d.getFontMetrics();
            g2d.drawString(text, x, y + fm.getAscent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        g2d.dispose();
    }
    public static void DrawString(BufferedImage image, String text, int x, int y,Font font, String color,String alignment) {
        Graphics2D g2d = image.createGraphics();
        g2d.setFont(font);
        FontMetrics fm2 = g2d.getFontMetrics();
        Rectangle2D rec2 = fm2.getStringBounds(text, g2d);
        int cx = (int) Math.ceil(rec2.getWidth());
        int cy = (int) Math.ceil(rec2.getHeight());
        g2d.dispose();

        int width = cx;
        int height = cy;

        if (Text.CENTER_CENTER.endsWith(alignment)) // 中心点定位
        {
            x = x - (int)(width / 2.0f);
            y = y - (int)(height / 2.0f);
        } else if (Text.LEFT_TOP.endsWith(alignment)) // 左上角定位
        {
            x = x + (int)((width - cx) / 2.0f);
            y = y + (int)((height - cy) / 2.0f);
        } else if (Text.CENTER_TOP.endsWith(alignment)) // 上中点定位
        {
            x = x - (int)(width / 2.0f);
            y = y + (int)((height - cy) / 2.0f);
        } else if (Text.RIGHT_TOP.endsWith(alignment)) // 右上角定位
        {
            x = x - (int)((width + cx) / 2.0f);
            y = y + (int)((height - cy) / 2.0f);
        } else if (Text.LEFT_BOTTOM.endsWith(alignment)) // 左下角定位
        {
            x = x + (int)((width - cx) / 2.0f);
            y = y - (int)((height + cy) / 2.0f);
        } else if (Text.CENTER_BOTTOM.endsWith(alignment)) // 下中点定位
        {
            x = x - (int)(width / 2.0f);
            y = y - (int)((height + cy) / 2.0f);
        } else if (Text.RIGHT_BOTTOM.endsWith(alignment)) // 右下角定位
        {
            x = x - (int)((width + cx) / 2.0f);
            y = y - (int)((height + cy) / 2.0f);
        } else if (Text.LEFT_CENTER.endsWith(alignment)) // 左中点定位
        {
            x = x + (int)((width - cx) / 2.0f);
            y = y - (int)(height / 2.0f);
        } else if (Text.RIGHT_CENTER.endsWith(alignment)) // 右中点定位
        {
            x = x - (int)((width + cx) / 2.0f);
            y = y - (int)(height / 2.0f);
        }
        DrawString(image, text, x, y, font, color);
    }
    public static void drawImage(BufferedImage bakimage, BufferedImage operimage, int x, int y) {
        Graphics2D g2d = bakimage.createGraphics();
        try {
            g2d.drawImage(operimage, x, y, null);
        } catch (Exception e) {
        }
        g2d.dispose();
    }
    public static void drawCenterImage(BufferedImage bakimage, BufferedImage operimage, int x, int y) {
        Graphics2D g2d = bakimage.createGraphics();
        try {
            g2d.drawImage(operimage, x-(operimage.getWidth()/2), y-(operimage.getHeight()/2), null);
        } catch (Exception e) {
        }
        g2d.dispose();
    }
    /**
     * 获取透明背景图片
     *
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage getTransparentImg(int width, int height) {
        BufferedImage res = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = res.createGraphics();
        //设置透明背景
        res = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.dispose();
        return res;
    }
    public static boolean write(BufferedImage subimage, String savePath) {
        FileOutputStream pointOut = null;
        try {
            File f = new File(savePath);
            if (!f.getParentFile().isDirectory()) {
                f.getParentFile().mkdirs();
            }
            pointOut = new FileOutputStream(savePath);
            ImageIO.write(subimage, "png", pointOut);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pointOut != null) {
                try {
                    pointOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
    /**
     * BufferedImage转byte[]
     *
     * @param bImage BufferedImage对象
     * @return byte[]
     * @auth zhy
     */
    public static byte[] imageToBytes(BufferedImage bImage) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, "png", out);
        } catch (IOException e) {
            //log.error(e.getMessage());
        }
        return out.toByteArray();
    }


    /**
     * 仿射变换
     * @param picPath
     * @param shear
     * @param scale
     * @return
     * @throws IOException
     */
    public static BufferedImage affineTransform(String picPath, double shear, double scale) throws IOException {
        BufferedImage read = ImageIO.read(new File(picPath));
        //平移宽度
        int shearW =  new BigDecimal(read.getHeight()).multiply(new BigDecimal(shear)).intValue();
        //缩放后的高度
        int scaleH =  new BigDecimal(read.getHeight()).multiply(new BigDecimal(scale)).intValue();
        BufferedImage transparentImg =getTransparentImg(read.getWidth()+shearW,scaleH );
        Graphics2D g2 = transparentImg.createGraphics();

        //高度缩小为h*0.5
        g2.scale(1,scale);
        g2.shear(-shear,0);
        g2.drawImage(read,shearW,0,null);
        g2.dispose();
        return transparentImg;
    }

    /**
     *
     * @param width
     * @param height
     * @param colors 渐变颜色
     * @param alpha
     * @return
     */
    public static BufferedImage getBackgroundImg(int width, int height,float alpha,String... colors){
        BufferedImage img = getTransparentImg(width, height);
        if(colors==null || colors.length==0){
            return img;
        }
        if( colors.length==1){
            String one = colors[0];
            colors=new String[]{one,one};
        }

        Graphics2D g2 = (Graphics2D) img.getGraphics();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        //按颜色拆分高度份数
        int avgH=height/(colors.length-1);
        for (int i = 0; i < colors.length-1; i++) {
            Color top = Color.decode(colors[i]);
            Color dow = Color.decode(colors[i+1]);
            GradientPaint grad = new GradientPaint(0, avgH*i,top,0 , avgH*(i+1), dow); // 起始点，起始颜色，终点，终点颜色
            g2.setPaint(grad); // 选择好渐变颜色
            g2.fillRect(0, avgH*i, width, avgH*(i+1));
        }
        return img;
    }

    /**
     *  将target 画在 src的正中间
     * @param src
     * @param target
     */
    public static void drawCenterImg(BufferedImage src, BufferedImage target) {
        Graphics2D g2d = src.createGraphics();
        g2d.drawImage(target,(src.getWidth()-target.getWidth())/2,(src.getHeight()-target.getHeight())/2,null);
        g2d.dispose();
    }
    // 填充一个多边形
    public static void FillPolygon(BufferedImage image, int[] xPoints, int[] yPoints, Color FillColor, float Alpha) {
        // 获取Graphics2D
        Graphics2D g2d = image.createGraphics();
        try {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 1.0f为透明度 ，值从0-1.0，依次变得不透明
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Alpha));
            g2d.setColor(FillColor);
            g2d.fillPolygon(xPoints, yPoints, xPoints.length);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        } catch (Exception e) {
        }
        g2d.dispose();
    }
    // 绘制一个多边形
    public static void DrawPolygon(BufferedImage image, int[] xPoints, int[] yPoints, Color lineColor, float lineWidth,
                                   float Alpha) {
        // 获取Graphics2D
        Graphics2D g2d = image.createGraphics();
        try {
            applyQualityRenderingHints(g2d);
            // 1.0f为透明度 ，值从0-1.0，依次变得不透明
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, Alpha));
            g2d.setColor(lineColor);
            // g2d.setStroke(new BasicStroke(lineWidth));
            g2d.setStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.drawPolygon(xPoints, yPoints, xPoints.length);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        } catch (Exception e) {
        }
        g2d.dispose();
    }
    public static void applyQualityRenderingHints(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        // g2d.setRenderingHint(RenderingHints.KEY_DITHERING,
        // RenderingHints.VALUE_DITHER_ENABLE);
        // g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
        // RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        // g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
        // RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    }
}
