package com.ybvtc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybvtc.domain.User;
import com.ybvtc.service.UserService;
import com.ybvtc.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author kaimier
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-05-14 18:09:51
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public User getUser(User inputUser) {
        // 获取查询条件
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, inputUser.getEmail());

        return getOne(queryWrapper);
    }
}




