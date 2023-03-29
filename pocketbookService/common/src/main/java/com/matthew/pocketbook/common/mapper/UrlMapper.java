package com.matthew.pocketbook.common.mapper;

import com.matthew.pocketbook.common.entity.Url;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * urlDao
 *
 * @author Matthew
 * @date 2021-02-25 17:00
 **/
@Mapper
@Repository
public interface UrlMapper {

    @Select("select url_id urlId, method, url from url")
    List<Url> getPublicUrl();
}
