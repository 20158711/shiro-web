package org.yanbing.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.yanbing.dao.RolePermissionDao;
import org.yanbing.dao.UserRoleDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RolePermissionDaoImpl implements RolePermissionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRoleDao userRoleDao;

    public static final String SQL="select permission from roles_permissions where role_name=?";

    @Override
    public Set<String> findPermissionsByUsername(String username) {
        List<String> roles=userRoleDao.findRolesByUserName(username);
        Set<String> permissions=new HashSet<>();
        roles.stream().forEach(role->
            jdbcTemplate.query(SQL,new String[]{role},rs->{
                permissions.add(rs.getString("permission"));
            }));
        return permissions;
    }
}
