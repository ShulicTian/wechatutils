package wechat.common.constant;

/**
 * 基础常量
 *
 * @author tainslc
 */
public class BaseConstant {

    public static String GET = "get";
    public static String CREATE = "create";
    public static String UPDATE = "update";
    public static String DELETE = "delete";
    public static String LIST = "list";
    public static String SIMPLELIST = "simplelist";
    public static String BATCHDELETE = "batchdelete";

    /**
     * 企业微信获取AccessToken的URL
     */
    public static String QIYE_ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET";

    /**
     * 企业微信操作人员的URL
     */
    public static String QIYE_CU_PERSONNEL = "https://qyapi.weixin.qq.com/cgi-bin/user/METHOD?access_token=ACCESS_TOKEN";

    /**
     * 企业微信操作人员的URL
     */
    public static String QIYE_RD_PERSONNEL = "https://qyapi.weixin.qq.com/cgi-bin/user/METHOD?access_token=ACCESS_TOKEN&userid=USERID";

    /**
     * 企业微信操作人员的URL
     */
    public static String QIYE_DEPARTMENT_PERSONNEL = "https://qyapi.weixin.qq.com/cgi-bin/user/METHOD?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";

    /**
     * 企业微信操作部门的URL
     */
    public static String QIYE_CU_DEPARTMENT = "https://qyapi.weixin.qq.com/cgi-bin/department/METHOD?access_token=ACCESS_TOKEN";

    /**
     * 企业微信操作部门的URL
     */
    public static String QIYE_RD_DEPARTMENT = "https://qyapi.weixin.qq.com/cgi-bin/department/METHOD?access_token=ACCESS_TOKEN&id=ID";

    /**
     * 企业微信网页链接认证的URL
     */
    public static String QIYE_OAUTH2 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

    /**
     * 企业微信获取用户信息的URL
     */
    public static String QIYE_GETUSERINFO = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";

}
