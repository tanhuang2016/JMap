package xyz.hashdog.dm.draw;

import xyz.hashdog.dm.bean.MainPic;



public class JMaps {
    public static JMap newSimpleMapHelper(MainPic mainPic) {
        return SimpleMapHelper.compile(mainPic);
    }
}
