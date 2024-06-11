package com.ybvtc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybvtc.domain.entity.Record;
import com.ybvtc.domain.entity.User;
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
    public IPage<Record> serachRecords(Record inputRecord, User user, Integer pageNum, Integer pageSize) {
        IPage<Record> recordPage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Record> queryWrapper = new LambdaQueryWrapper<>();
        // 添加条件
        if (inputRecord.getBorrower() != null) {
            queryWrapper.eq(Record::getBorrower, inputRecord.getBorrower()); // 条件1: 如果 borrower 不为空, 添加 borrower 的等于查询
        }
        if (inputRecord.getBookname() != null) {
            queryWrapper.like(Record::getBookname, inputRecord.getBookname()); // 条件2: 如果 bookname 不为空, 添加 bookname 的模糊查询
        }
        queryWrapper.orderByDesc(Record::getRemandtime); // 排序: 按 remandtime 降序排列
        return page(recordPage, queryWrapper);

    }
}




