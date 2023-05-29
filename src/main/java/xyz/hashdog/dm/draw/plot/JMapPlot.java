package xyz.hashdog.dm.draw.plot;


import xyz.hashdog.dm.draw.JMap;

/**
 * 地图绘制接口
 */
public interface JMapPlot extends Plot {
    /**
     * 地图绘制
     * @return
     */
    @Override
    JMap plot();

}
