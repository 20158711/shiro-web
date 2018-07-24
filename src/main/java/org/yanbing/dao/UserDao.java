package org.yanbing.dao;

import org.yanbing.vo.User;

public interface UserDao {
    User getUserByUsername(String userName);
}
