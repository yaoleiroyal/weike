package app.models.user;

import com.github.sog.annotation.TableBind;
import com.github.sog.plugin.sqlinxml.SqlKit;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by 名东 on 2015/8/15.
 */
@TableBind(tableName = "htrole")
public class Role extends Model<Role> {
    public static final Role dao = new Role();

    public Role findByName(String roleName) {
        return dao.findFirst("select * from htrole where RoleName = ?",roleName);
    }
}
