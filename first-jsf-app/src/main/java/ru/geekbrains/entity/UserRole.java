package ru.geekbrains.entity;

import javax.persistence.*;

@Entity
@Table(name = "users_roles")
@NamedQueries({
        @NamedQuery(name = "rolesLeftJoin", query = "select r from Role r left join UserRole ur on ur.roleId=r.id where ur.userId =:userId")
})
public class UserRole {

    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "role_id")
    private Long roleId;

    public UserRole() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
