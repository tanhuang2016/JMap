package xyz.hashdog.dm.bean;

import java.util.List;

public class MapBorder {
    /**
     * 地区名
     */
    private String name;
    /**
     * 地区code
     */
    private String adcode;
    /**
     * 边界经度
     */
    private List<List<Double>> lonsList;
    /**
     * 边界维度
     */
    private List<List<Double>> latsList;
    /**
     *边界颜色
     */
    private String edgeColor;
    /**
     * 填充颜色
     */
    private String faceColor;
    /**
     * 线宽
     */
    private float lineWidth;
    /**
     * 填充色透明度
     */
    private float alpha=1;

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<List<Double>> getLonsList() {
        return lonsList;
    }

    public void setLonsList(List<List<Double>> lonsList) {
        this.lonsList = lonsList;
    }

    public List<List<Double>> getLatsList() {
        return latsList;
    }

    public void setLatsList(List<List<Double>> latsList) {
        this.latsList = latsList;
    }

    public String getEdgeColor() {
        return edgeColor;
    }

    public void setEdgeColor(String edgeColor) {
        this.edgeColor = edgeColor;
    }

    public String getFaceColor() {
        return faceColor;
    }

    public void setFaceColor(String faceColor) {
        this.faceColor = faceColor;
    }


    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
}
