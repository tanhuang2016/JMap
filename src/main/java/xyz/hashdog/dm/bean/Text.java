package xyz.hashdog.dm.bean;

import xyz.hashdog.dm.bean.base.Position;
import xyz.hashdog.dm.bean.base.TextStyle;

public class Text extends TextStyle {
    /**
     * 居中定位
     */
    public static final String CENTER_CENTER="center,center";
    /**
     * 居左居中
     */
    public static final String LEFT_CENTER="left,center";
    /**
     * 居右居中
     */
    public static final String RIGHT_CENTER="right,center";
    /**
     * 居中居上
     */
    public static final String CENTER_TOP="center,top";
    /**
     * 居中居下
     */
    public static final String CENTER_BOTTOM="center,bottom";
    /**
     * 居左居上
     */
    public static final String LEFT_TOP="left,top";
    /**
     * 居左居下
     */
    public static final String LEFT_BOTTOM="left,bottom";
    /**
     * 居右居上
     */
    public static final String RIGHT_TOP="right,top";
    /**
     * 居右居下
     */
    public static final String RIGHT_BOTTOM="right,bottom";
    /**
     * 标题
     */
    private String title;

    /**
     * 在子图的坐标
     */
    private Position position=new Position(0,0);

    /**
     * 对齐方式
     */
    private String alignment;

    /**
     * 所属子图
     */
    private int subPicIndex;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public int getSubPicIndex() {
        return subPicIndex;
    }

    public void setSubPicIndex(int subPicIndex) {
        this.subPicIndex = subPicIndex;
    }
}
