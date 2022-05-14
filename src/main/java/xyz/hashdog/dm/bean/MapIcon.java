package xyz.hashdog.dm.bean;

import xyz.hashdog.dm.bean.base.MapPointMark;
import xyz.hashdog.dm.bean.base.PicStyle;

import java.awt.image.BufferedImage;
import java.util.List;

public class MapIcon extends MapPointMark {
    private PicStyle picStyle;
    private List<BufferedImage> icons;

    public PicStyle getPicStyle() {
        return picStyle;
    }

    public void setPicStyle(PicStyle picStyle) {
        this.picStyle = picStyle;
    }

    public List<BufferedImage> getIcons() {
        return icons;
    }

    public void setIcons(List<BufferedImage> icons) {
        this.icons = icons;
    }
}
