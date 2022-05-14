package xyz.hashdog.dm.bean;

import xyz.hashdog.dm.bean.base.MapPointMark;
import xyz.hashdog.dm.bean.base.TextStyle;

import java.util.List;

public class MapText extends MapPointMark {
    private List<String> text;
    private TextStyle textStyle;

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public TextStyle getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
    }
}
