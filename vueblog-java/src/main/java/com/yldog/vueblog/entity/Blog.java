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
 * 
 * </p>
 *
 * @author yldog
 * @since 2021-09-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("m_blog")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String title;

    private String description;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime created;

    private Integer status;

    private Blog(Long userId, String title, String description, String content, Integer status) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.content = content;
        this.status = status;
    }

    public static Blog create(Long userId, String title, String description, String content, Integer status) {
        return new Blog(userId, title, description, content, status);
    }
}
