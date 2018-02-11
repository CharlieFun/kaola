package com.netease.kaola.dao;

import com.netease.kaola.entity.Role;
import com.netease.kaola.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by funstar on 2018/2/8.
 */
@Repository
public interface UserDao {
    void add(User user);

    void updatePassword(@Param("username") String username, @Param("password") String password);

    void deleteUser(Long userId);

    void deleteUserRole(Long userId);

    User findById(Long userId);

    User findByUsername(String username);

    void correlationRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    void uncorrelationRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    List<String> findRoles(String username);

    List<User> findAll();
}
