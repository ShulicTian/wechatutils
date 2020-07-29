package wechat.common.constant;

public enum ErrorEnum {
    OK(0, "成功"),
    ACCESSTOKEN_ILLEGAL(40014, "不合法的access_token");

    private final Integer code;
    private final String text;

    ErrorEnum(Integer code, String text) {
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
        for (ErrorEnum error : ErrorEnum.values()) {
            if (error.getCode().equals(code)) {
                return error.getText();
            }
        }
        return null;
    }
}
