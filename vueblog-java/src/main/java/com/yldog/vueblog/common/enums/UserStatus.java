package com.yldog.vueblog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户的状态，对应字段：m_user:status
 */
@Getter
@AllArgsConstructor
public enum UserStatus {

    OK(0, "正常"),
    BANNED(-1, "封禁");

    private final int code;
    private final String info;
}
