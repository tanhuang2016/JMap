package xyz.hashdog.dm.draw.convert;

import xyz.hashdog.dm.bean.MapPic;
import xyz.hashdog.dm.bean.SubPicLayer;
import xyz.hashdog.dm.bean.base.Position;

/**
 * 经纬度转坐标
 */
public interface CoordinateConvert {

    /**
     * 底图宽
     * @return
     */
    int FloorPicWidth();

    /**
     * 底图高
     * @return
     */
    int FloorPicHieght();

    /**
     * 横坐标,纵坐标
     * @param lon
     * @return
     */
    Position XY(Double lon, Double lat);


    void init(MapPic mapPic, SubPicLayer subPicLayer);
}
