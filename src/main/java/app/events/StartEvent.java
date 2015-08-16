package app.events;

import com.github.sog.initalizer.AppLoadEvent;
import com.github.sog.render.ftl.shiro.ShiroTags;
import com.jfinal.render.FreeMarkerRender;
import freemarker.template.Configuration;

/**
 * <p>
 * 系统启动运行事件.
 * </p>
 *
 * @author Jerry Ou
 * @version 1.0 2014-07-28 09:55
 * @since JDK 1.6
 */
public class StartEvent implements AppLoadEvent {

    @Override
    public void load() {

        final Configuration config = FreeMarkerRender.getConfiguration();
        config.setSharedVariable("shiro", new ShiroTags());
    }
}
