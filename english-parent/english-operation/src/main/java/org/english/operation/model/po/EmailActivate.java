package org.english.operation.model.po;

import java.util.Date;
import javax.persistence.*;
import org.english.operation.model.base.BaseModel;

@Table(name = "email_activate")
public class EmailActivate extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String email;

    /**
     * 状态 1待激活  2已激活
     */
    private Integer status;

    /**
     * 账户名
     */
    private String account;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 推荐人id
     */
    @Column(name = "recom_id")
    private String recomId;

    /**
     * 创建时间
     */
    @Column(name = "creat_time")
    private Date creatTime;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取状态 1待激活  2已激活
     *
     * @return status - 状态 1待激活  2已激活
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 1待激活  2已激活
     *
     * @param status 状态 1待激活  2已激活
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取账户名
     *
     * @return account - 账户名
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账户名
     *
     * @param account 账户名
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取推荐人id
     *
     * @return recom_id - 推荐人id
     */
    public String getRecomId() {
        return recomId;
    }

    /**
     * 设置推荐人id
     *
     * @param recomId 推荐人id
     */
    public void setRecomId(String recomId) {
        this.recomId = recomId;
    }

    /**
     * 获取创建时间
     *
     * @return creat_time - 创建时间
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 设置创建时间
     *
     * @param creatTime 创建时间
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}