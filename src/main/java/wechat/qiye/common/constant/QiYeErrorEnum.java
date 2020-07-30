package wechat.qiye.common.constant;

/**
 * 企业微信错误代码
 *
 * @author tianslc
 */

public enum QiYeErrorEnum {
    OK(0, "成功"),
    ACCESSTOKEN_ILLEGAL(40014, "不合法的access_token");

    private final Integer code;
    private final String text;

    QiYeErrorEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static String getTextByCode(String code) {
        for (QiYeErrorEnum error : QiYeErrorEnum.values()) {
            if (error.getCode().equals(code)) {
                return error.getText();
            }
        }
        return null;
    }
}
