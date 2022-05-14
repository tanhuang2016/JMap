package xyz.hashdog.dm.bean;

/**
 * 子图
 */
public class SubPicLayer {
    /**
     * 底色
     */
    private String faceColor;
    /**
     * 透明度
     */
    private float alpha;
    /**
     * 图层排序
     */
    private int zorder;

    /**
     * 距离主图左边距离多少
     */
    private int left;

    /**
     * 距离主图上边距离多少
     */
    private int top;

    /**
     * 宽
     */
    private int width;

    /**
     * 高
     */
    private int hight;

    public String getFaceColor() {
        return faceColor;
    }

    public void setFaceColor(String faceColor) {
        this.faceColor = faceColor;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public int getZorder() {
        return zorder;
    }

    public void setZorder(int zorder) {
        this.zorder = zorder;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }
}
