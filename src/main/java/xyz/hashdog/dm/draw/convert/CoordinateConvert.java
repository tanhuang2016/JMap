package xyz.hashdog.dm.draw.convert;

import xyz.hashdog.dm.bean.MapPic;
import xyz.hashdog.dm.bean.SubPicLayer;
import xyz.hashdog.dm.bean.base.Position;

/**
 * 经纬度转坐标
 */
public interface CoordinateConvert {

    /**
     *
     * @return 底图宽
     */
    int FloorPicWidth();

    /**
     *
     * @return 底图高
     */
    int FloorPicHieght();

    /**
     *
     * @param lon 经度
     * @param lat 维度
     * @return 横坐标,纵坐标
     */
    Position XY(Double lon, Double lat);


    void init(MapPic mapPic, SubPicLayer subPicLayer);
}
