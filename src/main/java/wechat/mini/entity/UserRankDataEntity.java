package wechat.mini.entity;

import java.util.List;
import java.util.Map;

/**
 * @author tianslc
 * @date 2022/11/29
 */
public class UserRankDataEntity {
    private String openId;
    private String signature;
    private String sigMethod;
    private List<Map<String, Object>> kvList;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSigMethod() {
        return sigMethod;
    }

    public void setSigMethod(String sigMethod) {
        this.sigMethod = sigMethod;
    }

    public List<Map<String, Object>> getKvList() {
        return kvList;
    }

    public void setKvList(List<Map<String, Object>> kvList) {
        this.kvList = kvList;
    }
}
