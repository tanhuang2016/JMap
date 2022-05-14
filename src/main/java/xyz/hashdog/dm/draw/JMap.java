package xyz.hashdog.dm.draw;

import xyz.hashdog.dm.draw.plot.JMapPlot;

import java.awt.image.BufferedImage;


public interface JMap extends JMapPlot {

    void out(String path);

    byte[] outToBytes();

    BufferedImage out();
}
