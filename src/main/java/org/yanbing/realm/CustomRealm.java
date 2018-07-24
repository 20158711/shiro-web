package org.yanbing.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.yanbing.dao.RolePermissionDao;
import org.yanbing.dao.UserDao;
import org.yanbing.dao.UserRoleDao;
import org.yanbing.vo.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 自定义realm
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    /**
     * 授权
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName= (String) principals.getPrimaryPrincipal();
        Set<String> roles=getRolesByUsername(userName);
        Set<String> permissions=getPermissionByUserName(userName);
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permissions);
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    public static void main(String[] args) {
        Md5Hash md5Hash=new Md5Hash("123");
        System.out.println(md5Hash.toString());
    }

    @Override
    /**
     * 认证
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1,从主体传过来的认证信息中获取用户名
        String username= (String) token.getPrincipal();
        //2,到数据库中获取凭证
        String password=getPasswordByUserName(username);
        if (password == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo("yanbing1025",password,"customRealm");
        return authenticationInfo;
    }

    /**
     * 模拟数据库获取用户认证数据
     * @param userName
     * @return
     */
    private String getPasswordByUserName(String userName){
        User user=userDao.getUserByUsername(userName);
        return user.getPassword();
    }

    /**
     * 模拟数据库获取角色数据
     * @param username
     * @return
     */
    private Set<String> getRolesByUsername(String username){
        List<String> roles=userRoleDao.findRolesByUserName(username);
        return roles.stream().collect(Collectors.toSet());
    }

    /**
     * 模拟数据库获取权限数据
     * @param username
     * @return
     */
    private Set<String> getPermissionByUserName(String username){
        Set<String> permissions=rolePermissionDao.findPermissionsByUsername(username);
        return permissions;
    }
}
