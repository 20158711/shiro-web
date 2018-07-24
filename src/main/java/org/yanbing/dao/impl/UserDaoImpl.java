package org.yanbing.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.yanbing.dao.UserDao;
import org.yanbing.vo.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static final String SQL="select password from users where username=?";

    @Override
    public User getUserByUsername(String userName) {
        List<User> userList=new ArrayList<>();
        jdbcTemplate.query(SQL,new String[]{userName},e->{
            User user=new User();
            user.setUsername(userName);
            user.setPassword(e.getString("password"));
            userList.add(user);
        });
        if (CollectionUtils.isEmpty(userList))
            return null;
        return userList.get(0);
    }
}
