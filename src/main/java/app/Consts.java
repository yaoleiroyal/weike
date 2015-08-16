package app;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 *    常量类
 * </p>
 *
 * @author Jerry Ou
 * @version 1.0 2014-07-07 17:25
 * @since JDK 1.6
 */
public final class Consts {

    /**
     * 默认分页大小
     */
    public static final int PAGE_SIZE = 15;

    public static final String PARAM_DATA = "data";

     /**
     * 性别
     */
    public static final class GENDER {

        /**
         * 未知、无、不显示
         */
        public static final int NONE = 0;

        /**
         * 男
         */
        public static final int MALE = 1;

        /**
         * 女
         */
        public static final int FEMALE = 2;

        /**
         * 显示所有性别键值
         */
        public static final Map<Integer, String> ALL_MAP = new LinkedHashMap<Integer, String>() {
            {
                put(NONE, "未知");
                put(MALE, "男");
                put(FEMALE, "女");
            }

            private static final long serialVersionUID = 11717795909973597L;
        };
        /**
         * 显示所有男/女性别
         */
        public static final Map<Integer, String> MAP = new LinkedHashMap<Integer, String>() {
            {
                put(MALE, "男");
                put(FEMALE, "女");
            }

            private static final long serialVersionUID = 4288088781431696129L;
        };
    }
}
