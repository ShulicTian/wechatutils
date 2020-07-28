package wechat.common.entity;

import redis.clients.jedis.JedisPoolConfig;

/**
 * redis配置
 *
 * @author tianslc
 */
public class RedisConfig extends JedisPoolConfig {
    /**
     * ip地址
     */
    private String address;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 认证密码
     */
    private String auth;

    public RedisConfig() {
        super();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public JedisPoolConfig getParent() {
        return this;
    }
}
