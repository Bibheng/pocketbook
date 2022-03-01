package com.matthew.pocketbook.common.dao;

import com.matthew.pocketbook.common.entity.Url;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * urlDao
 *
 * @author Matthew
 * @date 2021-02-25 17:00
 **/
@Component
public interface UrlDao {

    @Select("select url_id urlId, method, url from url")
    List<Url> getPublicUrl();
}
