package org.english.operation.model.po;

import java.util.Date;
import javax.persistence.*;
import org.english.operation.model.base.BaseModel;

public class Token extends BaseModel {
    /**
     * 用户登录token
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * token字段
     */
    private String token;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 过期时间
     */
    @Column(name = "overdue_time")
    private Date overdueTime;

    /**
     * 获取用户登录token
     *
     * @return id - 用户登录token
     */
    public String getId() {
        return id;
    }

    /**
     * 设置用户登录token
     *
     * @param id 用户登录token
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取token字段
     *
     * @return token - token字段
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置token字段
     *
     * @param token token字段
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取过期时间
     *
     * @return overdue_time - 过期时间
     */
    public Date getOverdueTime() {
        return overdueTime;
    }

    /**
     * 设置过期时间
     *
     * @param overdueTime 过期时间
     */
    public void setOverdueTime(Date overdueTime) {
        this.overdueTime = overdueTime;
    }
}