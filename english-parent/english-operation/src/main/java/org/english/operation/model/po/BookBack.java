package org.english.operation.model.po;

import javax.persistence.*;
import org.english.operation.model.base.BaseModel;

@Table(name = "book_back")
public class BookBack extends BaseModel {
    /**
     * id : id

     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 书名 : 书名
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 模块名 : 模块名
     */
    @Column(name = "model_name")
    private String modelName;

    /**
     * 书编码 : 书编码
     */
    @Column(name = "book_code")
    private String bookCode;

    /**
     * 书简介 : 书简介
     */
    private String introduce;

    /**
     * 单元总数 : 单元总数
     */
    @Column(name = "totalUnitNbr")
    private Integer totalunitnbr;

    /**
     * 出版社 : 出版社
     */
    private String publisher;

    /**
     * 书本分组 : 书本分组
     */
    @Column(name = "book_group_name")
    private String bookGroupName;

    /**
     * 分组 : 分组
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 获取id : id

     *
     * @return id - id : id

     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id : id

     *
     * @param id id : id

     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取书名 : 书名
     *
     * @return book_name - 书名 : 书名
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 设置书名 : 书名
     *
     * @param bookName 书名 : 书名
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 获取模块名 : 模块名
     *
     * @return model_name - 模块名 : 模块名
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * 设置模块名 : 模块名
     *
     * @param modelName 模块名 : 模块名
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * 获取书编码 : 书编码
     *
     * @return book_code - 书编码 : 书编码
     */
    public String getBookCode() {
        return bookCode;
    }

    /**
     * 设置书编码 : 书编码
     *
     * @param bookCode 书编码 : 书编码
     */
    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    /**
     * 获取书简介 : 书简介
     *
     * @return introduce - 书简介 : 书简介
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置书简介 : 书简介
     *
     * @param introduce 书简介 : 书简介
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * 获取单元总数 : 单元总数
     *
     * @return totalUnitNbr - 单元总数 : 单元总数
     */
    public Integer getTotalunitnbr() {
        return totalunitnbr;
    }

    /**
     * 设置单元总数 : 单元总数
     *
     * @param totalunitnbr 单元总数 : 单元总数
     */
    public void setTotalunitnbr(Integer totalunitnbr) {
        this.totalunitnbr = totalunitnbr;
    }

    /**
     * 获取出版社 : 出版社
     *
     * @return publisher - 出版社 : 出版社
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * 设置出版社 : 出版社
     *
     * @param publisher 出版社 : 出版社
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * 获取书本分组 : 书本分组
     *
     * @return book_group_name - 书本分组 : 书本分组
     */
    public String getBookGroupName() {
        return bookGroupName;
    }

    /**
     * 设置书本分组 : 书本分组
     *
     * @param bookGroupName 书本分组 : 书本分组
     */
    public void setBookGroupName(String bookGroupName) {
        this.bookGroupName = bookGroupName;
    }

    /**
     * 获取分组 : 分组
     *
     * @return group_name - 分组 : 分组
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置分组 : 分组
     *
     * @param groupName 分组 : 分组
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}