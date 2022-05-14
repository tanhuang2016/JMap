package xyz.hashdog.dm.bean;

import java.util.ArrayList;
import java.util.List;

public class MapPic {
    /**
     * 宽高比
     */
    private double zoomWeight=1;

    /**
     * 开始经度,左
     */
    private double startLon=-1;
    /**
     * 结束经度,右
     */
    private double endLon=-1;
    /**
     * 开始纬度,上
     */
    private double startLat=-1;
    /**
     * 结束纬度,下
     */
    private double endLat=-1;

    /**
     * 绘制子图下标
     */
    private int subPicIndex=0;
    /**
     * 地图边界
     */
    private List<MapBorder> mapBorders;
    /**
     * 文本标题
     */
    private List<MapText> mapTexts;
    /**
     * 图标
     */
    private List<MapIcon> mapIcons;
    {
        mapBorders = new ArrayList<>();
        mapTexts = new ArrayList<>();
        mapIcons = new ArrayList<>();
    }


    public List<MapText> getMapTexts() {
        return mapTexts;
    }

    public void setMapTexts(List<MapText> mapTexts) {
        this.mapTexts = mapTexts;
    }

    public List<MapIcon> getMapIcons() {
        return mapIcons;
    }

    public void setMapIcons(List<MapIcon> mapIcons) {
        this.mapIcons = mapIcons;
    }

    public List<MapBorder> getMapBorders() {
        return mapBorders;
    }

    public void setMapBorders(List<MapBorder> mapBorders) {
        this.mapBorders = mapBorders;
    }

    public double getZoomWeight() {
        return zoomWeight;
    }

    public void setZoomWeight(double zoomWeight) {
        this.zoomWeight = zoomWeight;
    }

    public double getStartLon() {
        return startLon;
    }

    public void setStartLon(double startLon) {
        this.startLon = startLon;
    }

    public double getEndLon() {
        return endLon;
    }

    public void setEndLon(double endLon) {
        this.endLon = endLon;
    }

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public double getEndLat() {
        return endLat;
    }

    public void setEndLat(double endLat) {
        this.endLat = endLat;
    }

    public int getSubPicIndex() {
        return subPicIndex;
    }

    public void setSubPicIndex(int subPicIndex) {
        this.subPicIndex = subPicIndex;
    }
}
