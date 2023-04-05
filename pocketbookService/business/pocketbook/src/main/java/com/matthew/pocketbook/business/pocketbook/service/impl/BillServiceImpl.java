package com.matthew.pocketbook.business.pocketbook.service.impl;

import com.matthew.pocketbook.business.pocketbook.entity.BillInfo;
import com.matthew.pocketbook.business.pocketbook.entity.BillQueryParam;
import com.matthew.pocketbook.business.pocketbook.mapper.BillMapper;
import com.matthew.pocketbook.business.pocketbook.service.BillService;
import com.matthew.pocketbook.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账单服务实现类
 *
 * @author songzeheng
 * @date 2023/3/27 23:47
 **/
@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillMapper billMapper;

    @Override
    public List<BillInfo> getBillList(BillQueryParam param) {
        return billMapper.queryBillListByParam(param);
    }

    @Override
    public int updateBillInfo(BillInfo param) {
        if (StringUtil.isNotEmpty(param.getBillId())) {
            return billMapper.updateBillInfo(param);
        } else {
            return billMapper.insertBillInfo(param);
        }
    }
}
