package org.english.operation.model.po;

import java.util.Date;
import javax.persistence.*;
import org.english.operation.model.base.BaseModel;

public class Book extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 课程ID
     */
    @Column(name = "course_id")
    private Integer courseId;

    /**
     * 课本名称
     */
    private String name;

    /**
     * 书本介绍
     */
    private String introduce;

    /**
     * 单元数
     */
    @Column(name = "totalUnitNbr")
    private Integer totalunitnbr;

    /**
     * 出版社
     */
    private String publisher;

    /**
     * 排序权重
     */
    @Column(name = "top_num")
    private Integer topNum;

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
     * 获取课本名称
     *
     * @return name - 课本名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置课本名称
     *
     * @param name 课本名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取书本介绍
     *
     * @return introduce - 书本介绍
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置书本介绍
     *
     * @param introduce 书本介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * 获取单元数
     *
     * @return totalUnitNbr - 单元数
     */
    public Integer getTotalunitnbr() {
        return totalunitnbr;
    }

    /**
     * 设置单元数
     *
     * @param totalunitnbr 单元数
     */
    public void setTotalunitnbr(Integer totalunitnbr) {
        this.totalunitnbr = totalunitnbr;
    }

    /**
     * 获取出版社
     *
     * @return publisher - 出版社
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * 设置出版社
     *
     * @param publisher 出版社
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * 获取排序权重
     *
     * @return top_num - 排序权重
     */
    public Integer getTopNum() {
        return topNum;
    }

    /**
     * 设置排序权重
     *
     * @param topNum 排序权重
     */
    public void setTopNum(Integer topNum) {
        this.topNum = topNum;
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
}