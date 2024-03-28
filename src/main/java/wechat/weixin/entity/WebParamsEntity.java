package wechat.weixin.entity;

import wechat.common.entity.BaseParams;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 微信接口基本参数
 *
 * @author tianslc
 */
public class WebParamsEntity extends BaseParams {

    private String appId;
    private String secret;
    private String code;
    private String openId;

    public WebParamsEntity(String appId, String secret, String code) {
        this.appId = appId;
        this.secret = secret;
        this.code = code;
    }

    public WebParamsEntity(Properties properties) {
        this.appId = properties.getProperty("webAppId");
        this.secret = properties.getProperty("webSecret");
        this.isOpenRedisCache = Boolean.parseBoolean(properties.getProperty("isOpenRedisCache"));
        if (this.isOpenRedisCache) {
            this.initRedisConfig(properties);
        }
    }

    /**
     * 加载配置文件方式
     */
    public static synchronized WebParamsEntity loadProps(InputStream inputStream) {
        WebParamsEntity entity = null;
        Properties props = new Properties();
        InputStream in = inputStream;
        try {
            props.load(in);
            if (null != props) {
                entity = new WebParamsEntity(props);
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
        return entity;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
