package com.my.webapp.dao.mapper;

import com.my.webapp.dao.entity.SignRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author struggling_rong
 * @since 2018-01-31
 */
public interface SignRecordMapper extends BaseMapper<SignRecord> {

    public List<SignRecord> query();
}
