package org.english.operation.model.po;

import java.util.Date;
import javax.persistence.*;
import org.english.operation.model.base.BaseModel;

@Table(name = "recharg_card")
public class RechargCard extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 卡号
     */
    @Column(name = "card_no")
    private String cardNo;

    /**
     * 卡密码
     */
    private String password;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 1未使用 2已使用
     */
    private Integer status;

    /**
     * 使用的用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 充值卡后台创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 用户第一次使用充值卡时间
     */
    @Column(name = "user_time")
    private Date userTime;

    /**
     * (1.普通单门卡选A即A 2.普通多门卡 选ABC即ABC 3.vip卡可以从一定范围内选几门） )
     */
    @Column(name = "card_type")
    private Integer cardType;

    /**
     * vip卡可开通的门数 普通卡为空
     */
    @Column(name = "course_no")
    private Integer courseNo;

    /**
     * 开通天数
     */
    private Integer day;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取卡号
     *
     * @return card_no - 卡号
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 设置卡号
     *
     * @param cardNo 卡号
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * 获取卡密码
     *
     * @return password - 卡密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置卡密码
     *
     * @param password 卡密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取渠道
     *
     * @return channel - 渠道
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 设置渠道
     *
     * @param channel 渠道
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取状态 1未使用 2已使用
     *
     * @return status - 状态 1未使用 2已使用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 1未使用 2已使用
     *
     * @param status 状态 1未使用 2已使用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取使用的用户ID
     *
     * @return user_id - 使用的用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置使用的用户ID
     *
     * @param userId 使用的用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取充值卡后台创建时间
     *
     * @return create_time - 充值卡后台创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置充值卡后台创建时间
     *
     * @param createTime 充值卡后台创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取用户第一次使用充值卡时间
     *
     * @return user_time - 用户第一次使用充值卡时间
     */
    public Date getUserTime() {
        return userTime;
    }

    /**
     * 设置用户第一次使用充值卡时间
     *
     * @param userTime 用户第一次使用充值卡时间
     */
    public void setUserTime(Date userTime) {
        this.userTime = userTime;
    }

    /**
     * 获取(1.普通单门卡选A即A 2.普通多门卡 选ABC即ABC 3.vip卡可以从一定范围内选几门） )
     *
     * @return card_type - (1.普通单门卡选A即A 2.普通多门卡 选ABC即ABC 3.vip卡可以从一定范围内选几门） )
     */
    public Integer getCardType() {
        return cardType;
    }

    /**
     * 设置(1.普通单门卡选A即A 2.普通多门卡 选ABC即ABC 3.vip卡可以从一定范围内选几门） )
     *
     * @param cardType (1.普通单门卡选A即A 2.普通多门卡 选ABC即ABC 3.vip卡可以从一定范围内选几门） )
     */
    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    /**
     * 获取vip卡可开通的门数 普通卡为空
     *
     * @return course_no - vip卡可开通的门数 普通卡为空
     */
    public Integer getCourseNo() {
        return courseNo;
    }

    /**
     * 设置vip卡可开通的门数 普通卡为空
     *
     * @param courseNo vip卡可开通的门数 普通卡为空
     */
    public void setCourseNo(Integer courseNo) {
        this.courseNo = courseNo;
    }

    /**
     * 获取开通天数
     *
     * @return day - 开通天数
     */
    public Integer getDay() {
        return day;
    }

    /**
     * 设置开通天数
     *
     * @param day 开通天数
     */
    public void setDay(Integer day) {
        this.day = day;
    }
}