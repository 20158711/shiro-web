package org.yanbing.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yanbing.DaoBaseTest;
import org.yanbing.dao.UserRoleDao;

import java.util.List;

public class UserRoleDaoImplTest extends DaoBaseTest {

    @Autowired
    private UserRoleDao userRoleDao;

    @Test
    public void findRolesByUserName() {
        List<String> userRoles = userRoleDao.findRolesByUserName("yanbing1025");
        Assert.assertNotEquals(0,userRoles.size());
    }
}