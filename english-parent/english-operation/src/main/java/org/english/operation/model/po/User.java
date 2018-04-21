package org.english.operation.model.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import org.english.operation.model.base.BaseModel;

public class User extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    /**
     * 账号或昵称
     */
    private String account;

    /**
     * 微信授权ID
     */
    @Column(name = "public_open_id")
    private String publicOpenId;

    private String email;

    /**
     * 微信账号
     */
    @Column(name = "wx_account")
    private String wxAccount;

    private String password;

    /**
     * 推荐人用户ID
     */
    @Column(name = "recom_id")
    private String recomId;

    /**
     * 性别，1男 2女
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 0 不可用 1未付费 2已付费 3 已使用
     */
    private Integer status;

    private String qq;

    /**
     * 手机
     */
    private String phone;

    /**
     * 最后一次登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 登录次数
     */
    @Column(name = "login_count")
    private Integer loginCount;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 当前积分
     */
    private Integer integral;

    /**
     * 历史总积分
     */
    @Column(name = "total_integral")
    private Integer totalIntegral;

    /**
     * 当前用户余额
     */
    private BigDecimal balance;

    /**
     * 已提现金额
     */
    @Column(name = "withd_price")
    private BigDecimal withdPrice;

    /**
     * 创建类型 1微信授权注册 2邮箱注册 3后端创建
     */
    @Column(name = "create_type")
    private Integer createType;

    /**
     * 班级ID
     */
    @Column(name = "class_id")
    private String classId;

    /**
     * 自己的推荐码
     */
    @Column(name = "self_recom_id")
    private String selfRecomId;

    /**
     * 1.未签到 2.已签到
     */
    @Column(name = "sign_status")
    private Integer signStatus;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取账号或昵称
     *
     * @return account - 账号或昵称
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号或昵称
     *
     * @param account 账号或昵称
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取微信授权ID
     *
     * @return public_open_id - 微信授权ID
     */
    public String getPublicOpenId() {
        return publicOpenId;
    }

    /**
     * 设置微信授权ID
     *
     * @param publicOpenId 微信授权ID
     */
    public void setPublicOpenId(String publicOpenId) {
        this.publicOpenId = publicOpenId;
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
     * 获取微信账号
     *
     * @return wx_account - 微信账号
     */
    public String getWxAccount() {
        return wxAccount;
    }

    /**
     * 设置微信账号
     *
     * @param wxAccount 微信账号
     */
    public void setWxAccount(String wxAccount) {
        this.wxAccount = wxAccount;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取推荐人用户ID
     *
     * @return recom_id - 推荐人用户ID
     */
    public String getRecomId() {
        return recomId;
    }

    /**
     * 设置推荐人用户ID
     *
     * @param recomId 推荐人用户ID
     */
    public void setRecomId(String recomId) {
        this.recomId = recomId;
    }

    /**
     * 获取性别，1男 2女
     *
     * @return sex - 性别，1男 2女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别，1男 2女
     *
     * @param sex 性别，1男 2女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取0 不可用 1未付费 2已付费 3 已使用
     *
     * @return status - 0 不可用 1未付费 2已付费 3 已使用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0 不可用 1未付费 2已付费 3 已使用
     *
     * @param status 0 不可用 1未付费 2已付费 3 已使用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return qq
     */
    public String getQq() {
        return qq;
    }

    /**
     * @param qq
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取手机
     *
     * @return phone - 手机
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机
     *
     * @param phone 手机
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取最后一次登录时间
     *
     * @return last_login_time - 最后一次登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后一次登录时间
     *
     * @param lastLoginTime 最后一次登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取登录次数
     *
     * @return login_count - 登录次数
     */
    public Integer getLoginCount() {
        return loginCount;
    }

    /**
     * 设置登录次数
     *
     * @param loginCount 登录次数
     */
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取当前积分
     *
     * @return integral - 当前积分
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * 设置当前积分
     *
     * @param integral 当前积分
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * 获取历史总积分
     *
     * @return total_integral - 历史总积分
     */
    public Integer getTotalIntegral() {
        return totalIntegral;
    }

    /**
     * 设置历史总积分
     *
     * @param totalIntegral 历史总积分
     */
    public void setTotalIntegral(Integer totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    /**
     * 获取当前用户余额
     *
     * @return balance - 当前用户余额
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 设置当前用户余额
     *
     * @param balance 当前用户余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 获取已提现金额
     *
     * @return withd_price - 已提现金额
     */
    public BigDecimal getWithdPrice() {
        return withdPrice;
    }

    /**
     * 设置已提现金额
     *
     * @param withdPrice 已提现金额
     */
    public void setWithdPrice(BigDecimal withdPrice) {
        this.withdPrice = withdPrice;
    }

    /**
     * 获取创建类型 1微信授权注册 2邮箱注册 3后端创建
     *
     * @return create_type - 创建类型 1微信授权注册 2邮箱注册 3后端创建
     */
    public Integer getCreateType() {
        return createType;
    }

    /**
     * 设置创建类型 1微信授权注册 2邮箱注册 3后端创建
     *
     * @param createType 创建类型 1微信授权注册 2邮箱注册 3后端创建
     */
    public void setCreateType(Integer createType) {
        this.createType = createType;
    }

    /**
     * 获取班级ID
     *
     * @return class_id - 班级ID
     */
    public String getClassId() {
        return classId;
    }

    /**
     * 设置班级ID
     *
     * @param classId 班级ID
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }

    /**
     * 获取自己的推荐码
     *
     * @return self_recom_id - 自己的推荐码
     */
    public String getSelfRecomId() {
        return selfRecomId;
    }

    /**
     * 设置自己的推荐码
     *
     * @param selfRecomId 自己的推荐码
     */
    public void setSelfRecomId(String selfRecomId) {
        this.selfRecomId = selfRecomId;
    }

    /**
     * 获取1.未签到 2.已签到
     *
     * @return sign_status - 1.未签到 2.已签到
     */
    public Integer getSignStatus() {
        return signStatus;
    }

    /**
     * 设置1.未签到 2.已签到
     *
     * @param signStatus 1.未签到 2.已签到
     */
    public void setSignStatus(Integer signStatus) {
        this.signStatus = signStatus;
    }
}