package org.english.operation.model.po;

import javax.persistence.*;
import org.english.operation.model.base.BaseModel;

public class Word extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_id")
    private Integer courseId;

    /**
     * 书本ID
     */
    @Column(name = "book_id")
    private Integer bookId;

    /**
     * 单元编号
     */
    @Column(name = "unit_no")
    private Integer unitNo;

    /**
     *  拼写
     */
    private String spelling;

    /**
     * 美式发音 : 美式发音
     */
    @Column(name = "sound_mark_us")
    private String soundMarkUs;

    /**
     * 英式发音 
     */
    @Column(name = "sound_mark_uk")
    private String soundMarkUk;

    /**
     * 英文句式 : 英文句式
     */
    @Column(name = "en_sentence")
    private String enSentence;

    /**
     * 中文释义 : 中文释义
     */
    private String meaning;

    /**
     * 动态 
     */
    private String dynamic;

    /**
     * 音频文件
     */
    @Column(name = "audio_url")
    private String audioUrl;

    /**
     * 排序权重
     */
    @Column(name = "top_num")
    private Integer topNum;

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
     * @return course_id
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * @param courseId
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
     * 获取单元编号
     *
     * @return unit_no - 单元编号
     */
    public Integer getUnitNo() {
        return unitNo;
    }

    /**
     * 设置单元编号
     *
     * @param unitNo 单元编号
     */
    public void setUnitNo(Integer unitNo) {
        this.unitNo = unitNo;
    }

    /**
     * 获取 拼写
     *
     * @return spelling -  拼写
     */
    public String getSpelling() {
        return spelling;
    }

    /**
     * 设置 拼写
     *
     * @param spelling  拼写
     */
    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    /**
     * 获取美式发音 : 美式发音
     *
     * @return sound_mark_us - 美式发音 : 美式发音
     */
    public String getSoundMarkUs() {
        return soundMarkUs;
    }

    /**
     * 设置美式发音 : 美式发音
     *
     * @param soundMarkUs 美式发音 : 美式发音
     */
    public void setSoundMarkUs(String soundMarkUs) {
        this.soundMarkUs = soundMarkUs;
    }

    /**
     * 获取英式发音 
     *
     * @return sound_mark_uk - 英式发音 
     */
    public String getSoundMarkUk() {
        return soundMarkUk;
    }

    /**
     * 设置英式发音 
     *
     * @param soundMarkUk 英式发音 
     */
    public void setSoundMarkUk(String soundMarkUk) {
        this.soundMarkUk = soundMarkUk;
    }

    /**
     * 获取英文句式 : 英文句式
     *
     * @return en_sentence - 英文句式 : 英文句式
     */
    public String getEnSentence() {
        return enSentence;
    }

    /**
     * 设置英文句式 : 英文句式
     *
     * @param enSentence 英文句式 : 英文句式
     */
    public void setEnSentence(String enSentence) {
        this.enSentence = enSentence;
    }

    /**
     * 获取中文释义 : 中文释义
     *
     * @return meaning - 中文释义 : 中文释义
     */
    public String getMeaning() {
        return meaning;
    }

    /**
     * 设置中文释义 : 中文释义
     *
     * @param meaning 中文释义 : 中文释义
     */
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    /**
     * 获取动态 
     *
     * @return dynamic - 动态 
     */
    public String getDynamic() {
        return dynamic;
    }

    /**
     * 设置动态 
     *
     * @param dynamic 动态 
     */
    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
    }

    /**
     * 获取音频文件
     *
     * @return audio_url - 音频文件
     */
    public String getAudioUrl() {
        return audioUrl;
    }

    /**
     * 设置音频文件
     *
     * @param audioUrl 音频文件
     */
    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
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
}