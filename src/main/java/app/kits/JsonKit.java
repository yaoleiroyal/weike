package app.kits;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.jfinal.plugin.activerecord.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 * .
 * </p>
 *
 * @author Jerry Ou
 * @version 1.0 2014-08-04 15:34
 * @since JDK 1.5
 */
public class JsonKit {
    private final static Logger logger = LoggerFactory.getLogger(JsonKit.class);

    public static Model<? extends Model> jsonToModel(String data, Class<? extends Model> clz) {
        JSONObject json = JSON.parseObject(data);
        Set<Map.Entry<String, Object>> entries = json.entrySet();
        try {
            Model t = (Model) clz.newInstance();
            for (Map.Entry<String, Object> entry : entries) {
                String k = entry.getKey();
                Object v = entry.getValue();

                t.set(k ,v);
            }
            return t;
        } catch (InstantiationException e) {
            logger.error(clz.getName() + " new instance error!", e);
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            logger.error(clz.getName() + " new instance error!", e);
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Model<? extends Model>> jsonToModelMap(String data, Map<String, Class<? extends Model>> clzMap) {
        Map<String, Model<? extends Model>> result = Maps.newHashMap();

        JSONObject json = JSON.parseObject(data);
        Set<Map.Entry<String, Object>> kvEntries = json.entrySet();
        for(Map.Entry<String, Object> kv: kvEntries) {
            String k = kv.getKey();
            String v = String.valueOf(kv.getValue());
            Class<? extends Model> clz = clzMap.get(k);
            Model<? extends Model> model = jsonToModel(v, clz);

            result.put(k, model);
        }
        return result;
    }

    public static <T> T parse(String data, Class<T> clz) {
        return JSON.parseObject(data, clz);
    }
}
