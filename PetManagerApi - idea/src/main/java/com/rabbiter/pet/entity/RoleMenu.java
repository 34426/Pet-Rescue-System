package com.rabbiter.pet.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sys_role_menu")
public class RoleMenu {

    private Integer roleId;
    private Integer menuId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "RoleMenu{" +
                "roleId=" + roleId +
                ", menuId=" + menuId +
                '}';
    }
}
