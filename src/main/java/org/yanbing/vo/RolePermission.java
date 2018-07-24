package org.yanbing.vo;

public class RolePermission {
    private String roleName;
    private String permission;

    @Override
    public String toString() {
        return "RolePermissionDao{" +
                "roleName='" + roleName + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
