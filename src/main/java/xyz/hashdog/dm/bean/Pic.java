package xyz.hashdog.dm.bean;

import xyz.hashdog.dm.bean.base.PicStyle;
import xyz.hashdog.dm.bean.base.Position;

import java.awt.image.BufferedImage;

public class Pic extends PicStyle {

    /**
     * 图片
     */
    private BufferedImage pic;

    /**
     * 在子图的坐标
     */
    private Position position=new Position(0,0);


    /**
     * 所属子图
     */
    private int subPicIndex;

    public BufferedImage getPic() {
        return pic;
    }

    public void setPic(BufferedImage pic) {
        this.pic = pic;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getSubPicIndex() {
        return subPicIndex;
    }

    public void setSubPicIndex(int subPicIndex) {
        this.subPicIndex = subPicIndex;
    }
}
