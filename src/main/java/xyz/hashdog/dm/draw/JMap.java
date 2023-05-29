package xyz.hashdog.dm.draw;

import xyz.hashdog.dm.draw.plot.JMapPlot;

import java.awt.image.BufferedImage;

/**
 *地图操作相关接口
 */
public interface JMap extends JMapPlot {

    /**
     * 输出到路径
     * @param path
     */
    void out(String path);

    /**
     * 输出为byte数组
     * @return
     */
    byte[] outToBytes();

    /**
     * 输出为BufferedImage对象
     * @return
     */
    BufferedImage out();


}
