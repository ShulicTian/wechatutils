package wechat.mini.entity;

import wechat.common.entity.BaseParams;
import wechat.qiye.common.entity.QiYeParamsEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 微信接口基本参数
 *
 * @author tianslc
 */
public class MiniParamsEntity extends BaseParams {

    private String appId;
    private String secret;
    private String jsCode;

    public MiniParamsEntity(Properties properties) {
        this.appId = properties.getProperty("appId");
        this.secret = properties.getProperty("appSecret");
        this.isOpenRedisCache = Boolean.parseBoolean(properties.getProperty("isOpenRedisCache"));
        if (this.isOpenRedisCache) {
            this.initRedisConfig(properties);
        }
    }

    /**
     * 加载配置文件方式
     */
    public static synchronized MiniParamsEntity loadProps(InputStream inputStream) {
        MiniParamsEntity entity = null;
        Properties props = new Properties();
        InputStream in = inputStream;
        try {
            props.load(in);
            if (null != props) {
                entity = new MiniParamsEntity(props);
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

    public String getJsCode() {
        return jsCode;
    }

    public void setJsCode(String jsCode) {
        this.jsCode = jsCode;
    }
}
