package xyz.hashdog.dm.bean.base;

import java.util.List;

/**
 * 地图上的标记,任何在地图上存在的包括图标,文字都应继承的属性
 */
public  class MapPointMark {
    /**
     * 经度
     */
    private List<Double> lons;
    /**
     * 纬度
     */
    private  List<Double> lats;
    /**
     * 横坐标偏移像素点
     */
    private int offsetX;
    /**
     * 纵坐标偏移像素点
     */
    private int offsetY;

    public List<Double> getLons() {
        return lons;
    }

    public void setLons(List<Double> lons) {
        this.lons = lons;
    }

    public List<Double> getLats() {
        return lats;
    }

    public void setLats(List<Double> lats) {
        this.lats = lats;
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
}
