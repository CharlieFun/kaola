package com.netease.kaola.dao;

import com.netease.kaola.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * Created by funstar on 2018/2/8.
 */
@Repository
public interface RoleDao {
    void add(Role role);

    void delete(Long id);

    Role findByRole(String role);
}
