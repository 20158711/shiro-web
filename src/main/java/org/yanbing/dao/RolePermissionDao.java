package org.yanbing.dao;


import java.util.Set;

public interface RolePermissionDao {
    Set<String> findPermissionsByUsername(String username);
}
