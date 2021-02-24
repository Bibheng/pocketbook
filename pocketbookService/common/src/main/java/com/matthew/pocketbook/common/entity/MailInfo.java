package com.matthew.pocketbook.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用邮件类
 *
 * @author Matthew
 * @date 2021-02-22 17:00
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailInfo {
    /**
     * 收件人
     */
    private String receiver;
    /**
     * 主题
     */
    private String subject;
    /**
     * 内容
     */
    private String content;
}
