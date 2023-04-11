package com.gaowenzheng.springboot_prj;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gaowenzheng.springboot_prj.entity.User;
import com.gaowenzheng.springboot_prj.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootPrjApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
        for (User user : userMapper.selectList(new QueryWrapper<>())) {
            System.out.println(user);
        }
    }

}
