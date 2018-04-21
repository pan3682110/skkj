package org.english.operation.model.po;

import javax.persistence.*;
import org.english.operation.model.base.BaseModel;

@Table(name = "word_back")
public class WordBack extends BaseModel {
    /**
     * id : id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 书本ID : 书本ID
     */
    @Column(name = "book_id")
    private Integer bookId;

    /**
     * 拼写 : 拼写
     */
    private String spelling;

    /**
     * 美式发音 : 美式发音
     */
    @Column(name = "sound_mark_us")
    private String soundMarkUs;

    /**
     * 英式发音 : 英式发音
     */
    @Column(name = "sound_mark_uk")
    private String soundMarkUk;

    /**
     * 单元编号 : 单元编号
     */
    @Column(name = "unit_nbr")
    private Integer unitNbr;

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
     * 动态 : 动态
     */
    private String dynamic;

    /**
     * 网上路径 : 网上路径
     */
    private String url;

    /**
     * 本地路径 : 本地路径
     */
    private String path;

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
     * 获取书本ID : 书本ID
     *
     * @return book_id - 书本ID : 书本ID
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * 设置书本ID : 书本ID
     *
     * @param bookId 书本ID : 书本ID
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取拼写 : 拼写
     *
     * @return spelling - 拼写 : 拼写
     */
    public String getSpelling() {
        return spelling;
    }

    /**
     * 设置拼写 : 拼写
     *
     * @param spelling 拼写 : 拼写
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
     * 获取英式发音 : 英式发音
     *
     * @return sound_mark_uk - 英式发音 : 英式发音
     */
    public String getSoundMarkUk() {
        return soundMarkUk;
    }

    /**
     * 设置英式发音 : 英式发音
     *
     * @param soundMarkUk 英式发音 : 英式发音
     */
    public void setSoundMarkUk(String soundMarkUk) {
        this.soundMarkUk = soundMarkUk;
    }

    /**
     * 获取单元编号 : 单元编号
     *
     * @return unit_nbr - 单元编号 : 单元编号
     */
    public Integer getUnitNbr() {
        return unitNbr;
    }

    /**
     * 设置单元编号 : 单元编号
     *
     * @param unitNbr 单元编号 : 单元编号
     */
    public void setUnitNbr(Integer unitNbr) {
        this.unitNbr = unitNbr;
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
     * 获取动态 : 动态
     *
     * @return dynamic - 动态 : 动态
     */
    public String getDynamic() {
        return dynamic;
    }

    /**
     * 设置动态 : 动态
     *
     * @param dynamic 动态 : 动态
     */
    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
    }

    /**
     * 获取网上路径 : 网上路径
     *
     * @return url - 网上路径 : 网上路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置网上路径 : 网上路径
     *
     * @param url 网上路径 : 网上路径
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取本地路径 : 本地路径
     *
     * @return path - 本地路径 : 本地路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置本地路径 : 本地路径
     *
     * @param path 本地路径 : 本地路径
     */
    public void setPath(String path) {
        this.path = path;
    }
}