package wechat.qiye.entity;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

/**
 * 成员对外属性实体
 *
 * @author tianslc
 */
public class ExternalProfileEntity {

    /**
     * 企业简称
     */
    @SerializedName("external_corp_name")
    private String externalCorpName;

    /**
     * 企业属性
     */
    @SerializedName("external_attr")
    private JsonArray externalAttr;

    public String getExternalCorpName() {
        return externalCorpName;
    }

    public void setExternalCorpName(String externalCorpName) {
        this.externalCorpName = externalCorpName;
    }

    public JsonArray getExternalAttr() {
        return externalAttr;
    }

    public void setExternalAttr(JsonArray externalAttr) {
        this.externalAttr = externalAttr;
    }
}
