package wechat.common.entity;

/**
 * 基础参数实体
 *
 * @author tianslc
 */
public class BaseParamsEntity {

    /**
     * 企业ID
     */
    private String corpId;

    /**
     * 应用密钥
     */
    private String secret;

    /**
     * 应用ID
     */
    private String agentId;

    /**
     * 消息密钥
     */
    private String encodingAESKey;

    /**
     * 通讯录密钥
     */
    private String addressBookSecret;

    /**
     * 是否开启全局通讯录操作
     */
    private boolean isOpenGlobalAddressBookSecret = false;


    public BaseParamsEntity() {
    }

    public BaseParamsEntity(String corpId, String secret) {
        this.corpId = corpId;
        this.secret = secret;
    }

    public BaseParamsEntity(String corpId, String addressBookSecret, boolean isOpenGlobalAddressBookSecret) {
        this.corpId = corpId;
        this.addressBookSecret = addressBookSecret;
        this.isOpenGlobalAddressBookSecret = isOpenGlobalAddressBookSecret;
    }

    public BaseParamsEntity(String corpId, String secret, String agentId, String encodingAESKey, String addressBookSecret, boolean isOpenGlobalAddressBookSecret) {
        this.corpId = corpId;
        this.secret = secret;
        this.agentId = agentId;
        this.encodingAESKey = encodingAESKey;
        this.addressBookSecret = addressBookSecret;
        this.isOpenGlobalAddressBookSecret = isOpenGlobalAddressBookSecret;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }

    public String getAddressBookSecret() {
        return addressBookSecret;
    }

    public void setAddressBookSecret(String addressBookSecret) {
        this.addressBookSecret = addressBookSecret;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public boolean isOpenGlobalAddressBookSecret() {
        return isOpenGlobalAddressBookSecret;
    }

    public void setOpenGlobalAddressBookSecret(boolean openGlobalAddressBookSecret) {
        isOpenGlobalAddressBookSecret = openGlobalAddressBookSecret;
    }
}
