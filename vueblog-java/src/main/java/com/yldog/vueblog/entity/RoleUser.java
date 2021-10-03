package com.yldog.vueblog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Role-user mapping
 * </p>
 *
 * @author yldog
 * @since 2021-10-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("m_role_user")
public class RoleUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * User Id
     */
    private Long userId;

    /**
     * Role Id
     */
    private Long roleId;


}
