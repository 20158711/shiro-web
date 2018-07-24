package org.yanbing.vo;

public class UserRole {
    private String username;
    private String roleName;

    @Override
    public String toString() {
        return "UserRole{" +
                "username='" + username + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
