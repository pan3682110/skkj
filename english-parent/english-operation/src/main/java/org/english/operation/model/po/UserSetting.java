package org.english.operation.model.po;

import javax.persistence.*;
import org.english.operation.model.base.BaseModel;

@Table(name = "user_setting")
public class UserSetting extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 1 开启 2关闭
     */
    private Integer voice;

    /**
     * 1 开启 2关闭
     */
    @Column(name = "auto_review")
    private Integer autoReview;

    /**
     * 1 显示 2不显示
     */
    @Column(name = "word_daoji")
    private Integer wordDaoji;

    /**
     * 默认5个 (5 10 15 20)
     */
    @Column(name = "word_num")
    private Integer wordNum;

    /**
     * 默认5秒(5 6 7 8 50)
     */
    @Column(name = "word_time")
    private Integer wordTime;

    /**
     * 10 20 30 40 50
     */
    @Column(name = "max_word_num")
    private Integer maxWordNum;

    /**
     * 1 2 3 4 5
     */
    @Column(name = "right_second")
    private Integer rightSecond;

    /**
     * (0 不进行 5 10 15 20)
     */
    @Column(name = "reverse_recall")
    private Integer reverseRecall;

    /**
     * 1 开启 2关闭
     */
    @Column(name = "all_recall")
    private Integer allRecall;

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
     * 获取1 开启 2关闭
     *
     * @return voice - 1 开启 2关闭
     */
    public Integer getVoice() {
        return voice;
    }

    /**
     * 设置1 开启 2关闭
     *
     * @param voice 1 开启 2关闭
     */
    public void setVoice(Integer voice) {
        this.voice = voice;
    }

    /**
     * 获取1 开启 2关闭
     *
     * @return auto_review - 1 开启 2关闭
     */
    public Integer getAutoReview() {
        return autoReview;
    }

    /**
     * 设置1 开启 2关闭
     *
     * @param autoReview 1 开启 2关闭
     */
    public void setAutoReview(Integer autoReview) {
        this.autoReview = autoReview;
    }

    /**
     * 获取1 显示 2不显示
     *
     * @return word_daoji - 1 显示 2不显示
     */
    public Integer getWordDaoji() {
        return wordDaoji;
    }

    /**
     * 设置1 显示 2不显示
     *
     * @param wordDaoji 1 显示 2不显示
     */
    public void setWordDaoji(Integer wordDaoji) {
        this.wordDaoji = wordDaoji;
    }

    /**
     * 获取默认5个 (5 10 15 20)
     *
     * @return word_num - 默认5个 (5 10 15 20)
     */
    public Integer getWordNum() {
        return wordNum;
    }

    /**
     * 设置默认5个 (5 10 15 20)
     *
     * @param wordNum 默认5个 (5 10 15 20)
     */
    public void setWordNum(Integer wordNum) {
        this.wordNum = wordNum;
    }

    /**
     * 获取默认5秒(5 6 7 8 50)
     *
     * @return word_time - 默认5秒(5 6 7 8 50)
     */
    public Integer getWordTime() {
        return wordTime;
    }

    /**
     * 设置默认5秒(5 6 7 8 50)
     *
     * @param wordTime 默认5秒(5 6 7 8 50)
     */
    public void setWordTime(Integer wordTime) {
        this.wordTime = wordTime;
    }

    /**
     * 获取10 20 30 40 50
     *
     * @return max_word_num - 10 20 30 40 50
     */
    public Integer getMaxWordNum() {
        return maxWordNum;
    }

    /**
     * 设置10 20 30 40 50
     *
     * @param maxWordNum 10 20 30 40 50
     */
    public void setMaxWordNum(Integer maxWordNum) {
        this.maxWordNum = maxWordNum;
    }

    /**
     * 获取1 2 3 4 5
     *
     * @return right_second - 1 2 3 4 5
     */
    public Integer getRightSecond() {
        return rightSecond;
    }

    /**
     * 设置1 2 3 4 5
     *
     * @param rightSecond 1 2 3 4 5
     */
    public void setRightSecond(Integer rightSecond) {
        this.rightSecond = rightSecond;
    }

    /**
     * 获取(0 不进行 5 10 15 20)
     *
     * @return reverse_recall - (0 不进行 5 10 15 20)
     */
    public Integer getReverseRecall() {
        return reverseRecall;
    }

    /**
     * 设置(0 不进行 5 10 15 20)
     *
     * @param reverseRecall (0 不进行 5 10 15 20)
     */
    public void setReverseRecall(Integer reverseRecall) {
        this.reverseRecall = reverseRecall;
    }

    /**
     * 获取1 开启 2关闭
     *
     * @return all_recall - 1 开启 2关闭
     */
    public Integer getAllRecall() {
        return allRecall;
    }

    /**
     * 设置1 开启 2关闭
     *
     * @param allRecall 1 开启 2关闭
     */
    public void setAllRecall(Integer allRecall) {
        this.allRecall = allRecall;
    }
}