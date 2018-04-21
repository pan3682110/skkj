package org.english.operation.model.po;

import java.util.Date;
import javax.persistence.*;
import org.english.operation.model.base.BaseModel;

@Table(name = "integral_record")
public class IntegralRecord extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    /**
     * 积分数量
     */
    private Integer integral;

    /**
     * 类型 1获取 2 扣减
     */
    private Integer type;

    /**
     * 业务类型 1签到增加 2测试增加 3未登录扣减 4兑换扣减
     */
    @Column(name = "bus_type")
    private Integer busType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取积分数量
     *
     * @return integral - 积分数量
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * 设置积分数量
     *
     * @param integral 积分数量
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * 获取类型 1获取 2 扣减
     *
     * @return type - 类型 1获取 2 扣减
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型 1获取 2 扣减
     *
     * @param type 类型 1获取 2 扣减
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取业务类型 1签到增加 2测试增加 3未登录扣减 4兑换扣减
     *
     * @return bus_type - 业务类型 1签到增加 2测试增加 3未登录扣减 4兑换扣减
     */
    public Integer getBusType() {
        return busType;
    }

    /**
     * 设置业务类型 1签到增加 2测试增加 3未登录扣减 4兑换扣减
     *
     * @param busType 业务类型 1签到增加 2测试增加 3未登录扣减 4兑换扣减
     */
    public void setBusType(Integer busType) {
        this.busType = busType;
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