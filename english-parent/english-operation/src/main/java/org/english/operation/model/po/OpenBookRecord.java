package org.english.operation.model.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import org.english.operation.model.base.BaseModel;

@Table(name = "open_book_record")
public class OpenBookRecord extends BaseModel {
    /**
     * 此交易ID 必须为纯数字，兼容微信
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "user_id")
    private String userId;

    /**
     * 支付类型  1充值卡支付 2微信支付
     */
    @Column(name = "pay_type")
    private Integer payType;

    /**
     * 交易ID 充值卡：为充值卡的卡号     微信则是微信的交易号
     */
    @Column(name = "trade_id")
    private String tradeId;

    /**
     * 金额
     */
    private BigDecimal price;

    /**
     * 业务ID，  与bus_type 关联
     */
    @Column(name = "bus_id")
    private String busId;

    /**
     * 业务类型 1开通课程
     */
    @Column(name = "bus_type")
    private Integer busType;

    /**
     * 开通的天数
     */
    @Column(name = "day_count")
    private Integer dayCount;

    /**
     * 状态 1未支付  2已支付
     */
    private Integer status;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取此交易ID 必须为纯数字，兼容微信
     *
     * @return id - 此交易ID 必须为纯数字，兼容微信
     */
    public String getId() {
        return id;
    }

    /**
     * 设置此交易ID 必须为纯数字，兼容微信
     *
     * @param id 此交易ID 必须为纯数字，兼容微信
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取支付类型  1充值卡支付 2微信支付
     *
     * @return pay_type - 支付类型  1充值卡支付 2微信支付
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * 设置支付类型  1充值卡支付 2微信支付
     *
     * @param payType 支付类型  1充值卡支付 2微信支付
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * 获取交易ID 充值卡：为充值卡的卡号     微信则是微信的交易号
     *
     * @return trade_id - 交易ID 充值卡：为充值卡的卡号     微信则是微信的交易号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置交易ID 充值卡：为充值卡的卡号     微信则是微信的交易号
     *
     * @param tradeId 交易ID 充值卡：为充值卡的卡号     微信则是微信的交易号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 获取金额
     *
     * @return price - 金额
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置金额
     *
     * @param price 金额
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取业务ID，  与bus_type 关联
     *
     * @return bus_id - 业务ID，  与bus_type 关联
     */
    public String getBusId() {
        return busId;
    }

    /**
     * 设置业务ID，  与bus_type 关联
     *
     * @param busId 业务ID，  与bus_type 关联
     */
    public void setBusId(String busId) {
        this.busId = busId;
    }

    /**
     * 获取业务类型 1开通课程
     *
     * @return bus_type - 业务类型 1开通课程
     */
    public Integer getBusType() {
        return busType;
    }

    /**
     * 设置业务类型 1开通课程
     *
     * @param busType 业务类型 1开通课程
     */
    public void setBusType(Integer busType) {
        this.busType = busType;
    }

    /**
     * 获取开通的天数
     *
     * @return day_count - 开通的天数
     */
    public Integer getDayCount() {
        return dayCount;
    }

    /**
     * 设置开通的天数
     *
     * @param dayCount 开通的天数
     */
    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    /**
     * 获取状态 1未支付  2已支付
     *
     * @return status - 状态 1未支付  2已支付
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 1未支付  2已支付
     *
     * @param status 状态 1未支付  2已支付
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取支付时间
     *
     * @return pay_time - 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置支付时间
     *
     * @param payTime 支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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
}