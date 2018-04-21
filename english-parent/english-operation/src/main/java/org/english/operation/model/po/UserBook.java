package org.english.operation.model.po;

import java.util.Date;
import javax.persistence.*;
import org.english.operation.model.base.BaseModel;

@Table(name = "user_book")
public class UserBook extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 课程ID
     */
    @Column(name = "course_id")
    private Integer courseId;

    /**
     * 书本ID
     */
    @Column(name = "book_id")
    private Integer bookId;

    /**
     * 书本状态 1已开通 2已过期
     */
    private Integer status;

    /**
     * 开通类型 1免费开通（此类型只返回2个单元可用）2付费开通 
     */
    @Column(name = "open_type")
    private Integer openType;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 有效时间
     */
    @Column(name = "overdue_time")
    private Date overdueTime;

    /**
     * 开通天数
     */
    @Column(name = "active_time")
    private Integer activeTime;

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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * 获取书本ID
     *
     * @return book_id - 书本ID
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * 设置书本ID
     *
     * @param bookId 书本ID
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取书本状态 1已开通 2已过期
     *
     * @return status - 书本状态 1已开通 2已过期
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置书本状态 1已开通 2已过期
     *
     * @param status 书本状态 1已开通 2已过期
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取开通类型 1免费开通（此类型只返回2个单元可用）2付费开通 
     *
     * @return open_type - 开通类型 1免费开通（此类型只返回2个单元可用）2付费开通 
     */
    public Integer getOpenType() {
        return openType;
    }

    /**
     * 设置开通类型 1免费开通（此类型只返回2个单元可用）2付费开通 
     *
     * @param openType 开通类型 1免费开通（此类型只返回2个单元可用）2付费开通 
     */
    public void setOpenType(Integer openType) {
        this.openType = openType;
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
     * 获取有效时间
     *
     * @return overdue_time - 有效时间
     */
    public Date getOverdueTime() {
        return overdueTime;
    }

    /**
     * 设置有效时间
     *
     * @param overdueTime 有效时间
     */
    public void setOverdueTime(Date overdueTime) {
        this.overdueTime = overdueTime;
    }

    /**
     * 获取开通天数
     *
     * @return active_time - 开通天数
     */
    public Integer getActiveTime() {
        return activeTime;
    }

    /**
     * 设置开通天数
     *
     * @param activeTime 开通天数
     */
    public void setActiveTime(Integer activeTime) {
        this.activeTime = activeTime;
    }
}