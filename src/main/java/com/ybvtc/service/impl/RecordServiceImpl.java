package com.ybvtc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybvtc.domain.Record;
import com.ybvtc.service.RecordService;
import com.ybvtc.mapper.RecordMapper;
import org.springframework.stereotype.Service;

/**
* @author kaimier
* @description 针对表【record】的数据库操作Service实现
* @createDate 2024-05-30 18:38:26
*/
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record>
    implements RecordService{

    @Override
    public Record serachRecords(Record inputRecord) {

        return null;
    }
}




