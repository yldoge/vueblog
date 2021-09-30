package com.yldog.vueblog.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * user login records
 * </p>
 *
 * @author yldog
 * @since 2021-09-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("m_login_info")
public class LoginInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * login user name
     */
    private String loginName;

    /**
     * ip address
     */
    private String ipAddress;

    private String loginLocation;

    /**
     * browser type
     */
    private String browser;

    /**
     * operation system
     */
    private String os;

    private Integer status;

    /**
     * login result message
     */
    private String msg;

    /**
     * user login time
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime loginTime;


}
