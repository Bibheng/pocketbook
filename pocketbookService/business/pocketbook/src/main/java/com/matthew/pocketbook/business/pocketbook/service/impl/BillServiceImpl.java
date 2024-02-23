package com.matthew.pocketbook.business.pocketbook.service.impl;

import com.matthew.pocketbook.business.pocketbook.entity.BillInfo;
import com.matthew.pocketbook.business.pocketbook.entity.BillQueryParam;
import com.matthew.pocketbook.business.pocketbook.entity.CardInfo;
import com.matthew.pocketbook.business.pocketbook.mapper.BillMapper;
import com.matthew.pocketbook.business.pocketbook.service.BillService;
import com.matthew.pocketbook.common.constant.BillConstant;
import com.matthew.pocketbook.common.util.DateUtil;
import com.matthew.pocketbook.common.util.StringUtil;
import com.matthew.pocketbook.common.util.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    @Transactional
    public int updateBillInfo(BillInfo param) {
        if (StringUtil.isEmpty(param.getCreatedBy())) {
            param.setCreatedBy("system");// todo: 补充用户名获取
        }
        if (StringUtil.isNotEmpty(param.getBillId())) {
            return billMapper.updateBillInfo(param);
        } else {
            return billMapper.insertBillInfo(param);
        }
    }

    @Override
    @Transactional
    public void delBillInfo(BillInfo param) {
        billMapper.delBillInfo(param);
    }

    @Override
    public CardInfo getCardData(BillQueryParam param) {
        // 1、参数处理
        // 当日
        String currentDay = param.getDate();
        // 昨日
        String yesterday = DateUtil.getCalDay(currentDay, -1);
        // 当月周期
        String startDate = DateUtil.getFirstDayOnMonth(currentDay);
        String endDate = DateUtil.getLastDayOnMonth(currentDay);
        // 上月周期
        String lastMonthEndDate = DateUtil.getFirstDayOnLastMonth(currentDay);
        String lastMonthStartDate = DateUtil.getLastDayOnLastMonth(currentDay);

        // 2、计算指标
        // 计算当日支出和较昨日支出
        BillQueryParam queryParam = new BillQueryParam();
        queryParam.setPayDateBegin(currentDay);
        queryParam.setPayDateEnd(currentDay);
        queryParam.setRelatedBy(param.getRelatedBy());
        queryParam.setBillType(BillConstant.BILL_TYPE_PAY);
        List<BillInfo> sumCurrentBillInfo = billMapper.getSumBillInfo(queryParam);
        BigDecimal currentDayPay = sumCurrentBillInfo.stream().findAny().orElseGet(BillInfo::new).getMoney();
        // 昨日支出
        queryParam.setPayDateBegin(yesterday);
        queryParam.setPayDateEnd(yesterday);
        List<BillInfo> sumYesterdayBillInfo = billMapper.getSumBillInfo(queryParam);
        BigDecimal yesterdayPay = sumYesterdayBillInfo.stream().findAny().orElseGet(BillInfo::new).getMoney();

        // 计算当月和上月的支出和收入
        queryParam.setBillType(null);
        queryParam.setPayDateBegin(startDate);
        queryParam.setPayDateEnd(endDate);
        List<BillInfo> sumMonthBillInfo = billMapper.getSumBillInfo(queryParam);
        BigDecimal currentMonthPay = pickupSumMoney(sumMonthBillInfo, BillConstant.BILL_TYPE_PAY);
        BigDecimal currentMonthIncome = pickupSumMoney(sumMonthBillInfo, BillConstant.BILL_TYPE_INCOME);

        queryParam.setPayDateBegin(lastMonthStartDate);
        queryParam.setPayDateEnd(lastMonthEndDate);
        List<BillInfo> sumLastMonthBillInfo = billMapper.getSumBillInfo(queryParam);
        BigDecimal lastMonthPay = pickupSumMoney(sumLastMonthBillInfo, BillConstant.BILL_TYPE_PAY);
        BigDecimal lastMonthIncome = pickupSumMoney(sumLastMonthBillInfo, BillConstant.BILL_TYPE_INCOME);

        return CardInfo.builder()
            .currentDayPay(currentDayPay)
            .compareDayPay(currentDayPay == null || yesterdayPay == null || yesterdayPay.equals(BigDecimal.ZERO)
                ? BigDecimal.ZERO
                : currentDayPay.subtract(yesterdayPay).divide(yesterdayPay, 4, BigDecimal.ROUND_HALF_UP))
            .currentMonthPay(currentMonthPay)
            .compareMonthPay(currentMonthPay == null || lastMonthPay == null || lastMonthPay.equals(BigDecimal.ZERO)
                ? BigDecimal.ZERO
                : currentMonthPay.subtract(lastMonthPay).divide(lastMonthPay, 4, BigDecimal.ROUND_HALF_UP))
            .currentMonthIncome(currentMonthIncome)
            .compareMonthIncome(currentMonthIncome == null || lastMonthIncome == null || lastMonthIncome.equals(BigDecimal.ZERO)
                ? BigDecimal.ZERO
                : currentMonthIncome.subtract(lastMonthIncome).divide(lastMonthIncome, 4, BigDecimal.ROUND_HALF_UP))
            .build();
    }

    /**
     * 根据账单类型获取账单汇总金额
     * @param billList
     * @param billType
     * @return
     */
    private BigDecimal pickupSumMoney(List<BillInfo> billList, String billType) {
        return billList.stream()
            .filter(b -> b.getBillType().equals(billType))
            .findAny()
            .orElseGet(BillInfo::new)
            .getMoney();
    }
}
