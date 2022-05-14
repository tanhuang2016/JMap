package xyz.hashdog.dm.bean;

import java.util.List;

/**
 * 主图
 */
public class MainPic {

    /**
     * 图高
     */
    private int hight;
    /**
     * 图宽
     */
    private int width;
    /**
     * 图背景色
     */
    private String faceColor;

    /**
     * 透明度
     */
    private float alpha;
    /**
     * 子图-图层
     */
    private List<SubPicLayer> subPicLayers;

    /**
     * 地图边界配置
     */
    private List<MapPic> mapPics;

    /**
     * 标题文本配置
     */
    private List<Text> texts;
    /**
     * 图例配置
     */
    private List<Pic> pics;

    public List<Pic> getPics() {
        return pics;
    }

    public void setPics(List<Pic> pics) {
        this.pics = pics;
    }

    public List<MapPic> getMapPics() {
        return mapPics;
    }

    public void setMapPics(List<MapPic> mapPics) {
        this.mapPics = mapPics;
    }

    public List<Text> getTexts() {
        return texts;
    }

    public void setTexts(List<Text> texts) {
        this.texts = texts;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public List<SubPicLayer> getSubPicLayers() {
        return subPicLayers;
    }

    public void setSubPicLayers(List<SubPicLayer> subPicLayers) {
        this.subPicLayers = subPicLayers;
    }

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getFaceColor() {
        return faceColor;
    }

    public void setFaceColor(String faceColor) {
        this.faceColor = faceColor;
    }
}
