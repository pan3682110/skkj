package org.english.operation.model.po;

import javax.persistence.*;
import org.english.operation.model.base.BaseModel;

@Table(name = "recharg_card_book")
public class RechargCardBook extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 课程ID
     */
    @Column(name = "course_id")
    private Integer courseId;

    /**
     * 充值卡ID
     */
    @Column(name = "recharg_card_id")
    private Integer rechargCardId;

    /**
     * (1.未使用 2.已使用)
     */
    @Column(name = "course_type")
    private Integer courseType;

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
     * 获取课程ID
     *
     * @return course_id - 课程ID
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * 设置课程ID
     *
     * @param courseId 课程ID
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * 获取充值卡ID
     *
     * @return recharg_card_id - 充值卡ID
     */
    public Integer getRechargCardId() {
        return rechargCardId;
    }

    /**
     * 设置充值卡ID
     *
     * @param rechargCardId 充值卡ID
     */
    public void setRechargCardId(Integer rechargCardId) {
        this.rechargCardId = rechargCardId;
    }

    /**
     * 获取(1.未使用 2.已使用)
     *
     * @return course_type - (1.未使用 2.已使用)
     */
    public Integer getCourseType() {
        return courseType;
    }

    /**
     * 设置(1.未使用 2.已使用)
     *
     * @param courseType (1.未使用 2.已使用)
     */
    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }
}