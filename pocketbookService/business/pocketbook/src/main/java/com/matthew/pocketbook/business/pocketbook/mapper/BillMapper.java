package com.matthew.pocketbook.business.pocketbook.mapper;

import com.matthew.pocketbook.business.pocketbook.entity.BillInfo;
import com.matthew.pocketbook.business.pocketbook.entity.BillQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账单mapper
 *
 * @author songzeheng
 * @date 2023/3/27 23:54
 **/
@Mapper
@Repository
public interface BillMapper {

    List<BillInfo> queryBillListByParam(@Param("param") BillQueryParam param);

    int updateBillInfo(@Param("param") BillInfo param);

    int insertBillInfo(@Param("param") BillInfo param);

    void delBillInfo(@Param("param") BillInfo param);

    List<BillInfo> getSumBillInfo(@Param("param") BillQueryParam queryParam);
}
