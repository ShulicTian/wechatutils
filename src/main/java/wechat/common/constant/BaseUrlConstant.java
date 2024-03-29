package wechat.common.constant;

/**
 * 基础常量
 *
 * @author tainslc
 */
public class BaseUrlConstant {

    /**
     * 企业微信获取AccessToken的URL（GET）
     */
    public static String QIYE_ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET";

    /**
     * 企业微信创建更新人员的URL（POST）
     */
    public static String QIYE_CU_PERSONNEL = "https://qyapi.weixin.qq.com/cgi-bin/user/METHOD?access_token=ACCESS_TOKEN";

    /**
     * 企业微信获取删除人员的URL（GET）
     */
    public static String QIYE_RD_PERSONNEL = "https://qyapi.weixin.qq.com/cgi-bin/user/METHOD?access_token=ACCESS_TOKEN&userid=USERID";

    /**
     * 企业微信获取部门人员的URL（GET）
     */
    public static String QIYE_DEPARTMENT_PERSONNEL = "https://qyapi.weixin.qq.com/cgi-bin/user/METHOD?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID";

    /**
     * 企业微信获取部门人员ID的URL（POST）
     */
    public static String QIYE_DEPARTMENT_PERSONNEL_ID = "https://qyapi.weixin.qq.com/cgi-bin/user/list_id?access_token=ACCESS_TOKEN";

    /**
     * 企业微信创建更新部门的URL（POST）
     */
    public static String QIYE_CU_DEPARTMENT = "https://qyapi.weixin.qq.com/cgi-bin/department/METHOD?access_token=ACCESS_TOKEN";

    /**
     * 企业微信获取删除部门的URL（GET）
     */
    public static String QIYE_RD_DEPARTMENT = "https://qyapi.weixin.qq.com/cgi-bin/department/METHOD?access_token=ACCESS_TOKEN&id=#ID";

    /**
     * 企业微信二维码登录认证的URL（LINK）
     */
    public static String QIYE_QRCODE_OAUTH = "https://open.work.weixin.qq.com/wwopen/sso/qrConnect?appid=CORPID&agentid=AGENTID&redirect_uri=REDIRECT_URI&state=STATE";

    /**
     * 企业微信获取用户信息的URL（GET）
     */
    public static String QIYE_GETUSERINFO = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";

    /**
     * 企业微信发送消息的URL（POST）
     */
    public static String QIYE_SEND_MESSAGE = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";

    /**
     * 企业微信更新消息的URL（POST）
     */
    public static String QIYE_UPDATE_MESSAGE = "https://qyapi.weixin.qq.com/cgi-bin/message/update_taskcard?access_token=ACCESS_TOKEN";

    /**
     * 企业微信发送消息链接的URL（POST）
     */
    public static String QIYE_SEND_LINKMESSAGE = "https://qyapi.weixin.qq.com/cgi-bin/linkedcorp/message/send?access_token=ACCESS_TOKEN";

    /**
     * 企业微信消息统计查询的URL（POST）
     */
    public static String QIYE_COUNT_MESSAGE = "https://qyapi.weixin.qq.com/cgi-bin/message/get_statistics?access_token=ACCESS_TOKEN";

    /**
     * 企业微信获取JSAPI_TICKET的URL（GET）
     */
    public static String QIYE_JSAPI_TICKET = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=ACCESS_TOKEN";

    /**
     * 企业微信获取应用的JSAPI_TICKET的URL（GET）
     */
    public static String QIYE_APP_JSAPI_TICKET = "https://qyapi.weixin.qq.com/cgi-bin/ticket/get?access_token=ACCESS_TOKEN&type=agent_config";

    /**
     * 企业微信获取加入企业二维码（GET）
     */
    public static String QIYE_QRCODE = "https://qyapi.weixin.qq.com/cgi-bin/corp/get_join_qrcode?access_token=ACCESS_TOKEN&size_type=SIZE_TYPE";

    /**
     * 微信获取Token的URL（GET）
     */
    public static String WX_GET_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=GRANTTYPE&appid=APPID&secret=SECRET";

    /**
     * 小程序登陆（GET）
     */
    public static String MINI_LOGIN = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

    /**
     * 小程序获取手机号（POST）
     */
    public static String MINI_GET_MOBILE = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=ACCESS_TOKEN";

    /**
     * 上报用户数据接口（POST）
     */
    public static String SUBMIT_USER_DATA = "https://api.weixin.qq.com/wxa/set_user_storage?access_token=ACCESS_TOKEN&signature=SIGNATURE&openid=OPENID&sig_method=SIG_METHOD";

    /**
     * 微信网页链接登录认证的URL（LINK）
     */
    public static String WX_OAUTH2 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

    /**
     * 获取Web版Token
     */
    public static String WX_WEB_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    /**
     * 刷新Web版Token
     */
    public static String WX_WEB_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

    /**
     * 获取Web版用户信息
     */
    public static String WX_WEB_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**
     * 验证Web版Token是否有效
     */
    public static String WX_WEB_VALID_TOKEN = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";

}
