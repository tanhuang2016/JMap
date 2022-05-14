package xyz.hashdog.dm.util;

import java.util.Collections;
import java.util.List;

public class TDataUtil {

    public static <T>void reverse(List<T>... colorss) {
        for (List<T> ts : colorss) {
            Collections.reverse(ts);

        }
    }
}
