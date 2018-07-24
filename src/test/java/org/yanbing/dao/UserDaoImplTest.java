package org.yanbing.dao;

import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yanbing.DaoBaseTest;
import org.yanbing.vo.User;

public class UserDaoImplTest extends DaoBaseTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void getUserByUsername() {
        User user=userDao.getUserByUsername("yanbing1025");
        Assert.notNull(user.getPassword());
    }
}