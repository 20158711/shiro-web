package org.yanbing.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yanbing.DaoBaseTest;
import org.yanbing.dao.RolePermissionDao;

import java.util.Set;

import static org.junit.Assert.*;

public class RolePermissionDaoImplTest extends DaoBaseTest {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Test
    public void findPermissionsByUsername() {
        Set<String> permissions = rolePermissionDao.findPermissionsByUsername("yanbing1025");
        Assert.assertNotEquals(0,permissions.size());
    }
}