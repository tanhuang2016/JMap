package xyz.hashdog.dm.bean.base;

public  class TextStyle {

    /**
     * 字体大小
     */
    private int fontSize;
    /**
     * 颜色
     */
    private String fontColor;

    /**
     * 字体透明度
     */
    private float alpha=1;
    /**
     * 字体
     */
    private String fontFamily;
    /**
     *样式 bold
     */
    private String fontWeight;
    /**
     * 文字描边宽度
     */
    private String lineWidth;
    /**
     * 文字描边颜色
     */
    private String foreground;


    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public String getFontWeight() {
        return fontWeight;
    }

    public void setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
    }

    public String getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(String lineWidth) {
        this.lineWidth = lineWidth;
    }

    public String getForeground() {
        return foreground;
    }

    public void setForeground(String foreground) {
        this.foreground = foreground;
    }
}
