package wechat.qiye.common.entity;

import wechat.common.entity.BaseParams;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 基础参数实体
 *
 * @author tianslc
 */
public class QiYeParamsEntity extends BaseParams {

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


    public QiYeParamsEntity(Properties properties) {
        this.corpId = properties.getProperty("corpId");
        this.secret = properties.getProperty("secret");
        this.agentId = properties.getProperty("agentId");
        this.encodingAESKey = properties.getProperty("encodingAESKey");
        this.addressBookSecret = properties.getProperty("addressBookSecret");
        this.isOpenGlobalAddressBookSecret = Boolean.parseBoolean(properties.getProperty("isOpenGlobalAddressBookSecret"));
        this.isOpenRedisCache = Boolean.parseBoolean(properties.getProperty("isOpenRedisCache"));
        if (this.isOpenRedisCache) {
            this.initRedisConfig(properties);
        }
    }

    public QiYeParamsEntity(String corpId, String secret, String agentId) {
        this.corpId = corpId;
        this.secret = secret;
        this.agentId = agentId;
    }

    public QiYeParamsEntity(String corpId, String addressBookSecret, boolean isOpenGlobalAddressBookSecret) {
        this.corpId = corpId;
        this.addressBookSecret = addressBookSecret;
        this.isOpenGlobalAddressBookSecret = isOpenGlobalAddressBookSecret;
    }

    public QiYeParamsEntity(String corpId, String secret, String agentId, String encodingAESKey) {
        this.corpId = corpId;
        this.secret = secret;
        this.agentId = agentId;
        this.encodingAESKey = encodingAESKey;
    }

    /**
     * 加载配置文件方式
     */
    public static synchronized QiYeParamsEntity loadProps(InputStream inputStream) {
        QiYeParamsEntity qiYeParamsEntity = null;
        Properties props = new Properties();
        InputStream in = inputStream;
        try {
            props.load(in);
            if (null != props) {
                qiYeParamsEntity = new QiYeParamsEntity(props);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return qiYeParamsEntity;
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
