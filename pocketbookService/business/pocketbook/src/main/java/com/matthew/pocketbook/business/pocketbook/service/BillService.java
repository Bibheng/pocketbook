package com.matthew.pocketbook.business.pocketbook.service;

import com.matthew.pocketbook.business.pocketbook.entity.BillInfo;
import com.matthew.pocketbook.business.pocketbook.entity.BillQueryParam;
import com.matthew.pocketbook.common.entity.Result;

import java.util.List;

/**
 * 账单服务类
 *
 * @author songzeheng
 * @date 2023/3/27 23:47
 **/
public interface BillService {
    List<BillInfo> getBillList(BillQueryParam param);

    int updateBillInfo(BillInfo param);
}
