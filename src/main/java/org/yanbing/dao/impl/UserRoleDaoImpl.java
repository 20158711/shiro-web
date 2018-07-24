package org.yanbing.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.yanbing.dao.UserRoleDao;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static final String SQL="select role_name from user_roles where username=?";

    @Override
    public List<String> findRolesByUserName(String username) {
        List<String> roles=new ArrayList<>();
        jdbcTemplate.query(SQL,new String[]{username},rs->{
            roles.add(rs.getString("role_name"));
        });
        return roles;
    }
}
