package com.netease.kaola.service.impl;

import com.netease.kaola.dao.RoleDao;
import com.netease.kaola.entity.Role;
import com.netease.kaola.service.RoleBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by funstar on 2018/2/10.
 */
@Service
public class RoleBizImpl implements RoleBiz {
    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findByRole(String role) {
        return roleDao.findByRole(role);
    }
}
