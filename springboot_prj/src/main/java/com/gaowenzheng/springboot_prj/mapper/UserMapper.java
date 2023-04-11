package com.gaowenzheng.springboot_prj.mapper;

import com.gaowenzheng.springboot_prj.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
