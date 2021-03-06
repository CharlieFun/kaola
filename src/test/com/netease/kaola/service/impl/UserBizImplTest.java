package com.netease.kaola.service.impl;

import com.netease.kaola.dao.UserDao;
import com.netease.kaola.service.UserBiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by funstar on 2018/2/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml"})
public class UserBizImplTest {
    @Autowired
    UserBiz userBiz;

    @Test
    public void testSet() {
        Set<String> sets = new HashSet<>();
        List<String> list = new ArrayList<>();
        list.add("sdf");
        sets.add("123");
        System.out.print(sets.toString());
    }

    @Test
    public void roleTest(){
        Set<String> set = userBiz.findRoles("seller");
        System.out.print(set);
    }

}