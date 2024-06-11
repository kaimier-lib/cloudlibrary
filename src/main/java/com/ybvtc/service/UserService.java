package com.ybvtc.service;

import com.ybvtc.common.Result;
import com.ybvtc.domain.dto.LoginDTO;
import com.ybvtc.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author kaimier
* @description 针对表【user】的数据库操作Service
* @createDate 2024-05-14 18:09:51
*/
public interface UserService extends IService<User> {

    Result<User> validateUser(LoginDTO loginDTO);
}
