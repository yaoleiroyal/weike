/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 jfinal app. jfapp Group.
 */

package app.models.user;

import com.github.sog.annotation.TableBind;
import com.github.sog.plugin.sqlinxml.SqlKit;
import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * <p>
 * .
 * </p>
 *
 * @author sagyf yang
 * @version 1.0 2014-06-03 22:00
 * @since JDK 1.6
 */
@TableBind(tableName = "htuser")
public class User extends Model<User> {
    /**
     * The public dao.
     */
    public static final User dao = new User();

    private static final long serialVersionUID = -8819350365727558809L;

    public User findByName(String username) {
        return findFirst(SqlKit.sql("user.findByUserName"), username);
    }
}