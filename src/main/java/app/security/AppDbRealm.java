/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */

package app.security;

import app.models.user.User;
import com.github.sog.controller.security.AppUser;
import com.github.sog.kit.encry.EncodeKit;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * <p>
 * .
 * </p>
 *
 * @author sagyf yang
 * @version 1.0 2014-06-03 20:55
 * @since JDK 1.6
 */
public class AppDbRealm extends AuthorizingRealm {

    public AppDbRealm() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(EncodeKit.HASH_ALGORITHM);
        matcher.setHashIterations(EncodeKit.HASH_INTERATIONS);

        setCredentialsMatcher(matcher);
    }

    //授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        final AppUser user = (AppUser) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        User db_user = User.dao.findById(user.getId());
        if (db_user.getBoolean("super")) {
            info.addRole("admin");
        } else {
            info.addRole("user");
        }

        return info;
    }


    /*认证回调函数, 登录时调用.*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 调用操作数据库的方法查询user信息
        User user = User.dao.findByName(token.getUsername());
        if (user != null) {
            byte[] salt = EncodeKit.decodeHex(user.getStr("salt"));
            AppUser shiroEmployee = new AppUser(
                    user.getInt("id"),
                    user.getStr("name"), null,
                    user.getStr("name"), null,
                    user.getBoolean("super"));
            return new SimpleAuthenticationInfo(shiroEmployee, user.getStr("password"), ByteSource.Util.bytes(salt), getName());
        } else {
            throw new UnknownAccountException("无法找到对应的用户");
        }
    }
}
