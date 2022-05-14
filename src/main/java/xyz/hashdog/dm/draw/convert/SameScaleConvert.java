package xyz.hashdog.dm.draw.convert;

import xyz.hashdog.dm.bean.MapPic;
import xyz.hashdog.dm.bean.SubPicLayer;
import xyz.hashdog.dm.bean.base.Position;

import java.math.BigDecimal;

/**
 * 等经纬比例转换
 */
public class SameScaleConvert implements CoordinateConvert{

    /**
     * 开始经度,左
     */
    private  double startLon;
    /**
     * 结束经度,右
     */
    private  double endLon;
    /**
     * 开始纬度,上
     */
    private  double startLat;
    /**
     * 结束纬度,下
     */
    private  double endLat;

    /**
     * 宽高比
     */
    private double zoomWeight;
    /**
     * 底图宽
     */
    private  int width;
    /**
     * 底图高
     */
    private  int hight;

    /**
     * 经度总长
     */
    private  BigDecimal sumLon;
    /**
     * 维度总长
     */
    private  BigDecimal sumLat;
    /**
     * 像素点经度格距
     */
    private  BigDecimal svgLon;
    /**
     * 像素点维度格距
     */
    private  BigDecimal svgLat;

    /**
     * 契合底图宽
     */
    private int floorPicWidth;
    /**
     * 契合底图高
     */
    private int floorPicHight;

    public SameScaleConvert() {
    }

    private SameScaleConvert(double startLon, double endLon, double startLat, double endLat, double zoomWeight, int width, int hight) {
        this.startLon = startLon;
        this.endLon = endLon;
        this.startLat = startLat;
        this.endLat = endLat;
        this.zoomWeight = zoomWeight;
        this.width = width;
        this.hight = hight;
    }

    public CoordinateConvert load() {
        //宽高比例
        double value = new BigDecimal(String.valueOf(width)).divide(new BigDecimal(String.valueOf(hight)), 20, BigDecimal.ROUND_HALF_UP).doubleValue();



        //经度总长
        sumLon = new BigDecimal(endLon).subtract(new BigDecimal(startLon));
        //维度总长
        sumLat = new BigDecimal(endLat).subtract(new BigDecimal(startLat));

        //经纬比
        double value1 = sumLon.divide(sumLat.abs(), 20, BigDecimal.ROUND_HALF_UP).doubleValue();
        //需要的经纬比
        double value2= value1*zoomWeight;

        //如果经纬比 < 图片的宽高比
        if(value2>value){
            //以宽为最大尺寸适应
            svgLon = sumLon.divide( new BigDecimal(String.valueOf(width)),20,BigDecimal.ROUND_HALF_UP);
            svgLat =svgLon.multiply(new BigDecimal(String.valueOf(-zoomWeight)));


        }else{
            //以高为最大尺寸适应
            svgLat =sumLat.divide(new BigDecimal(String.valueOf(hight)),20,BigDecimal.ROUND_HALF_UP);
            svgLon = svgLat.divide(new BigDecimal(String.valueOf(-zoomWeight)));
        }

        floorPicWidth = (int)sumLon.divide(svgLon,20,BigDecimal.ROUND_HALF_UP).doubleValue();
        floorPicHight = (int)sumLat.divide(svgLat,20,BigDecimal.ROUND_HALF_UP).doubleValue();
        return this;
    }

    @Override
    public int FloorPicWidth() {
        return floorPicWidth;
    }

    @Override
    public Position XY(Double lon,Double lat) {
        int javaX = getJavaX(startLon, svgLon.doubleValue(), lon);
        int javaY = getJavaY(startLat, svgLat.doubleValue(), lat);
        return new Position(javaX,javaY);
    }

    @Override
    public void init(MapPic mapPic, SubPicLayer subPicLayer) {
        this.startLon =  mapPic.getStartLon();
        this.endLon = mapPic.getEndLon();
        this.startLat = mapPic.getStartLat();
        this.endLat = mapPic.getEndLat();
        this.zoomWeight = mapPic.getZoomWeight();
        this.width = subPicLayer.getWidth();
        this.hight = subPicLayer.getHight();
        load();
    }


    @Override
    public int FloorPicHieght() {
        return floorPicHight;
    }

    private  int getJavaX(double startLon,double  svgLon, double lon) {
        //中心点经度 减去 起始经度(经度差)
        BigDecimal subLon = new BigDecimal(String.valueOf(lon)).subtract(new BigDecimal(startLon));
        //地震x坐标
        BigDecimal x = subLon.divide(new BigDecimal(String.valueOf(svgLon)),20,BigDecimal.ROUND_DOWN);
        return x.intValue();
    }

    private  int getJavaY(double startLat,double  svgLat, double lat) {
        //中心点维度 减去 起始维度(维度差)
        BigDecimal subLat = new BigDecimal(String.valueOf(lat)).subtract(new BigDecimal(startLat));
        //地震y坐标
        BigDecimal y = subLat.divide(new BigDecimal(String.valueOf(svgLat)),20,BigDecimal.ROUND_DOWN);
        return y.intValue();
    }
}
