package wechat.qiye.common.entity;

import com.google.gson.annotations.Expose;

/**
 * 企业微信返回基础字段
 *
 * @author tianslc
 */
public class QiYeReceiveEntity {

    /**
     * 企业微信错误代码
     */
    @Expose(serialize = false)
    private Integer errcode;

    /**
     * 企业微信错误信息
     */
    @Expose(serialize = false)
    private String errmsg;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
