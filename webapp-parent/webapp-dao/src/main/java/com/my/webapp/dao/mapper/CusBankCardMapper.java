package com.my.webapp.dao.mapper;

import com.my.webapp.dao.entity.CusBankCard;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 客户绑定的银行卡，包括借记卡和信用卡（贷款信息表关联当前绑定的信用卡和借记卡） Mapper 接口
 * </p>
 *
 * @author struggling_rong
 * @since 2018-04-12
 */
public interface CusBankCardMapper extends BaseMapper<CusBankCard> {

}
