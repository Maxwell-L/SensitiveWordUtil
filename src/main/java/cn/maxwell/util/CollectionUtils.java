package cn.maxwell.util;

import java.util.Collection;

/**
 * @author linliangqi
 * @date 2021/09/03
 */
public class CollectionUtils {

    public static <T> boolean isEmpty(Collection<T> t) {
        return t == null || t.size() == 0;
    }

    public static <T> boolean isNotEmpty(Collection<T> t) {
        return t != null && t.size() != 0;
    }

}
