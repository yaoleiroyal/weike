package app.controllers;

import app.models.user.User;
import com.github.sog.controller.BasicController;
import com.github.sog.controller.security.AppUser;
import com.github.sog.controller.security.Securitys;
import com.jfinal.core.ActionKey;
import org.apache.shiro.SecurityUtils;

import java.util.List;

/**
 * <p>
 * Default index Controller.
 * </p>
 */
public class HomeController extends BasicController {

    @ActionKey("/")
    public void index() {
        if (Securitys.isLogin()) {
            AppUser appUser = Securitys.getLoginMember();
            String userName=appUser.getName();
            User person = User.dao.findByName(userName);
            if (person != null) {
                long personId = person.getLong("id");
                setAttr("personId", personId);
            } else {
                person = new User();
            }
            setAttr("person", person);
            render("/index.ftl");
        } else {
            redirect("/login");
        }
    }

    @ActionKey("/logout")
    public void logout() {
        if (Securitys.isLogin()) {
            SecurityUtils.getSubject().logout();
        }
        redirect("/login");
    }
}