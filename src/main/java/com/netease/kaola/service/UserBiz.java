package com.netease.kaola.service;

import com.netease.kaola.entity.User;

import java.util.Set;

/**
 * Created by funstar on 2018/2/8.
 */
public interface UserBiz {
    /**
     * 根据用户名查找角色
     *
     * @param username
     * @return
     */
    Set<String> findRoles(String username);

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 添加用户，以及用户对应的角色关系
     * @param username
     * @param password
     * @param role 角色名
     */
    boolean add(String username, String password, String role);
}
