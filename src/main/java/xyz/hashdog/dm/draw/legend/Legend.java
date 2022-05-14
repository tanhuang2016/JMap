package xyz.hashdog.dm.draw.legend;

import java.awt.image.BufferedImage;

public interface Legend {
    void save(String savePath);
    BufferedImage create();

}
