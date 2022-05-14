package xyz.hashdog.dm.draw.legend;

import xyz.hashdog.dm.exception.JMapException;
import xyz.hashdog.dm.util.ImageUtil;
import xyz.hashdog.dm.util.TDataUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class PieceLegend implements Legend {
    protected List<String> levelColor;
    protected List<String> levelHatches;
    protected List<String> levelLabel;

    /**
     * 字体
     */
    protected String font;
    /**
     * 字号
     */
    protected int fontSize;
    /**
     * 文字与色块的间隔
     */
    protected int space;
    /**
     * 横坐标偏移像素点
     */
    protected int offsetX;
    /**
     * 纵坐标偏移像素点
     */
    protected int offsetY;
    /**
     * 色块宽
     */
    protected int  colorPieceWidth;
    /**
     * 色块高
     */
    protected int  colorPieceHight;
    /**
     * 标题
     */
    protected String title;
    /**
     * 标题字体
     */
    protected String titleFont;
    /**
     * 标题
     */
    protected int titleFontSize;
    protected int titleSpace;

    /**
     * 初始化默认值
     */
    {
        font = "微软雅黑";
        fontSize = 18;
        space = 6;
        offsetX=0;
        offsetY=0;
        colorPieceWidth=18;
        colorPieceHight=33;
        titleFont="微软雅黑";
        titleSpace=10;
        titleFontSize=18;
    }


    protected PieceLegend( List<String> levelColor, List<String> levelHatches, List<String> levelLabel) {
        this.levelColor = levelColor;
        this.levelHatches = levelHatches;
        this.levelLabel = levelLabel;
    }
    protected PieceLegend(final List<String> levelColor, List<String> levelLabel) {
        this.levelColor = levelColor;
        this.levelHatches = new ArrayList<String>(){{
            for (int i = 0; i < levelColor.size(); i++) {
                add("");
            }
        }};
        this.levelLabel = levelLabel;
    }



    protected BufferedImage drawTitle(BufferedImage lenImg) {
        if(title!=null){
            Font titleFont = new Font(this.titleFont, Font.PLAIN, this.titleFontSize);
            int titleWidth = ImageUtil.widthByString(titleFont, title);
            int titleHight = ImageUtil.highByFont(titleFont);
            BufferedImage titleImg = ImageUtil.getTransparentImg(titleWidth, titleHight);
            Graphics2D gt = titleImg.createGraphics();
            gt.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            gt.setColor(Color.black);
            gt.setFont(titleFont);
            gt.drawString(this.title,0,titleHight/4*3);
            gt.dispose();
            return joinTitle(lenImg,titleImg);
        }
        return lenImg;
    }

    /**
     * 因为python的配置顺序问题,有的动态图例需要翻转
     * 如果使用此方法,会导致引用参数跟着变化,最好构造实例的时候,构造参数为副本
     * @return
     */
    public Legend reverse(){
        TDataUtil.reverse(levelColor,levelLabel,levelHatches);
        return this;
    }

    public abstract BufferedImage create();

    /**
     *
     * @param lenImg 图例
     * @param titleImg 标题
     * @return
     */
    protected abstract BufferedImage joinTitle(BufferedImage lenImg,BufferedImage titleImg);
    public void save(String path){
        if(levelColor==null||levelColor.isEmpty()){
            throw new JMapException("levelColor is empty");
        }
        BufferedImage bufferedImage = create();
        ImageUtil.write(bufferedImage,path);
    }



    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getColorPieceWidth() {
        return colorPieceWidth;
    }

    public void setColorPieceWidth(int colorPieceWidth) {
        this.colorPieceWidth = colorPieceWidth;
    }

    public int getColorPieceHight() {
        return colorPieceHight;
    }

    public void setColorPieceHight(int colorPieceHight) {
        this.colorPieceHight = colorPieceHight;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleFont() {
        return titleFont;
    }

    public void setTitleFont(String titleFont) {
        this.titleFont = titleFont;
    }

    public int getTitleFontSize() {
        return titleFontSize;
    }

    public void setTitleFontSize(int titleFontSize) {
        this.titleFontSize = titleFontSize;
    }

    public int getTitleSpace() {
        return titleSpace;
    }

    public void setTitleSpace(int titleSpace) {
        this.titleSpace = titleSpace;
    }
}
