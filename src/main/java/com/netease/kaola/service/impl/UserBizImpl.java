package com.netease.kaola.service.impl;

import com.netease.kaola.dao.RoleDao;
import com.netease.kaola.dao.UserDao;
import com.netease.kaola.entity.Role;
import com.netease.kaola.entity.User;
import com.netease.kaola.service.UserBiz;
import com.netease.kaola.util.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by funstar on 2018/2/8.
 */
@Service
public class UserBizImpl implements UserBiz {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserBizImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public Set<String> findRoles(String username) {
        List<String> roleList = userDao.findRoles(username);
        Set<String> roleSet = new HashSet<>(roleList);
        LOGGER.info("包含role:{}",roleSet.toString());
        return roleSet;
    }

    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        return user;
    }

    @Override
    public boolean add(String username, String password, String roleStr) {
        User existedUser = userDao.findByUsername(username);
        if (existedUser != null) {
            return false;
        }
        User user = new User(username, password);
        PasswordHelper.encryptPassword(user);
        userDao.add(user);
        Long userId = user.getId();
        LOGGER.info("新创建用户，用户ID为:{}", userId);
        //添加用户-角色关系
        Role role = roleDao.findByRole(roleStr);
        Long roleId = role.getId();
        userDao.correlationRole(userId, roleId);
        return true;
    }
}
