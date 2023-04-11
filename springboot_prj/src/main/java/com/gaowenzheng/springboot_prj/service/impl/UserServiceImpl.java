package com.gaowenzheng.springboot_prj.service.impl;

import com.gaowenzheng.springboot_prj.entity.User;
import com.gaowenzheng.springboot_prj.mapper.UserMapper;
import com.gaowenzheng.springboot_prj.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gaowenzheng
 * @since 2023-04-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
