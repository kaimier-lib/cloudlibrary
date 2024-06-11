package com.ybvtc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ybvtc.domain.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ybvtc.domain.entity.User;

/**
* @author kaimier
* @description 针对表【record】的数据库操作Service
* @createDate 2024-05-30 18:38:26
*/
public interface RecordService extends IService<Record> {

    IPage<Record> serachRecords(Record inputRecord, User user, Integer pageNum, Integer pageSize);
}
