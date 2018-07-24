package org.yanbing.dao;

import java.util.List;

public interface UserRoleDao {
    List<String> findRolesByUserName(String username);
}
