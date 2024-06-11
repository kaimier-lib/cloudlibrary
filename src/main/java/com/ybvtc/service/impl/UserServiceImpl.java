package com.ybvtc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybvtc.common.Result;
import com.ybvtc.domain.dto.LoginDTO;
import com.ybvtc.domain.entity.User;
import com.ybvtc.service.UserService;
import com.ybvtc.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
* @author kaimier
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-05-14 18:09:51
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public Result<User> validateUser(LoginDTO loginDTO) {
        // 获取查询条件
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, loginDTO.getEmail());
        User user = getOne(queryWrapper);
        if (user == null) {
            return new Result<>(false, "用户不存在");
        }
        // 输入的密码进行md加密后与数据库中的密码进行比对
        String hashedPassword = DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes());
        System.out.println(hashedPassword);
        String salt = user.getPassword().substring(32);
        String saltedHashedPassword = DigestUtils.md5DigestAsHex((hashedPassword + salt).getBytes()) + salt;
        System.out.println(saltedHashedPassword);
        if (!user.getPassword().equals(saltedHashedPassword)) {
            return new Result<>(false, "密码错误");
        }

        return new Result<>(true, "登录成功", user);
    }
}




