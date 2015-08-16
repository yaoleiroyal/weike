package app.controllers;

import app.models.user.User;
import com.github.sog.config.StringPool;
import com.github.sog.controller.BasicController;
import com.github.sog.controller.security.AppUser;
import com.github.sog.controller.security.Securitys;
import com.github.sog.kit.date.DateProvider;
import com.github.sog.kit.servlet.RequestKit;
import com.github.sog.libs.AppFunc;
import com.google.common.base.Strings;
import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.Restful;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 登录的Controller
 * </p>
 *
 * @author Jerry Ou
 * @version 1.0 2014-07-07 10:20
 * @since JDK 1.6
 */
@Before(Restful.class)
public class LoginController extends BasicController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public void index() {

        if (Securitys.isLogin()) {
            redirect("/");
            return;
        }
        int error = getParaToInt("error", 0);
        String name = getPara("username", StringPool.EMPTY);
        if (error > 0) {
            setAttr("msg", error == 1 ? "用户名或者密码不能为空" : (error == 2 ? "用户名或者密码错误" : "用户名不存在"));
        }
        setAttr("username", name);
        render("/login.ftl");
    }


    public void save() {
        String password = getPara(FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM);
        String username = getPara(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
        if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
            redirect("/login?error=1&username=" + username);
            return;
        }

        boolean rememberMe = StringUtils.equals(getPara(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM, "off"), "on");
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        final Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            logger.error("用户不存在！", e);
            redirect("/login?error=3&username=" + username);
            return;
        } catch (AuthenticationException e) {
            logger.error("用户名或者密码错误！", e);
            redirect("/login?error=2&username=" + username);
            return;
        }

        // login success.
        AppUser appUser = Securitys.getLoginMember();
        User loginSucc = new User();
        loginSucc.set(AppFunc.TABLE_PK_COLUMN, appUser.getId());
        loginSucc.set("last_login_time", DateProvider.DEFAULT.getCurrentDate());
        loginSucc.set("last_login_ip", RequestKit.getRemoteAddr(getRequest()));
        loginSucc.update();

        redirect("/home");

    }

}
