package xyz.hashdog.dm.draw.plot;


/**
 * 绘制接口
 */
public interface Plot {
    /**
     * 绘制
     * @param <T>
     * @return
     */
    <T extends Plot> T  plot();

    /**
     * 展示接口,仅调试样式的时候用
     */
    void show();
}
