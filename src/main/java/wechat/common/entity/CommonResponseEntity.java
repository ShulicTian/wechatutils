package wechat.common.entity;

import wechat.common.constant.InterfaceTypeConstant;

/**
 * 通用响应实体
 *
 * @param <T>
 */
public class CommonResponseEntity<T> {

    /**
     * 请求的接口类型
     */
    private String interfaceType = InterfaceTypeConstant.SYS;

    /**
     * 错误码
     */
    private String code;

    /**
     * 信息
     */
    private String msg;

    /**
     * 返回结果
     */
    private T result;

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
