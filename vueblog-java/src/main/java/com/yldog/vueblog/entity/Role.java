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
 * Role Table
 * </p>
 *
 * @author yldog
 * @since 2021-10-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("m_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Role Id
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * Role key used in authorization
     */
    private String roleKey;

    /**
     * IsDeleted
     */
    private Integer delFlag;

    /**
     * Create Time
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * Update Time
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
