package wechat.qiye.auth.ctrl;

import com.google.gson.JsonObject;
import wechat.common.constant.BaseUrlConstant;
import wechat.common.utils.GsonUtil;
import wechat.common.utils.HttpsRequestUtil;
import wechat.qiye.common.entity.QiYeParamsEntity;
import wechat.qiye.common.interfaces.BaseCtrlAbs;
import wechat.qiye.utils.AccessTokenUtil;

/**
 * 登入认证工具类
 *
 * @author tianslc
 */
public class LoginAuthCtrl extends BaseCtrlAbs {

    public LoginAuthCtrl(QiYeParamsEntity qiYeParamsEntity) {
        super(qiYeParamsEntity);
    }

    /**
     * 拼接OAuth2 Url
     *
     * @param redirectUrl
     * @param state
     * @return
     */
    public String getAuthUrl(String redirectUrl, String state, String scope) {
        return BaseUrlConstant.WX_OAUTH2.replace("APPID", qiYeParamsEntity.getCorpId()).
                replace("REDIRECT_URI", redirectUrl).
                replace("STATE", state).
                replace("SCOPE", scope);
    }

    /**
     * 拼接qrcode OAuth Url
     *
     * @param redirectUrl
     * @param state
     * @return
     */
    public String getQrcodeAuthUrl(String redirectUrl, String state) {
        String url = BaseUrlConstant.QIYE_QRCODE_OAUTH.replace("CORPID", qiYeParamsEntity.getCorpId()).
                replace("AGENTID", qiYeParamsEntity.getAgentId()).
                replace("REDIRECT_URI", redirectUrl).
                replace("STATE", state);
        return url;
    }

    /**
     * 根据code获取用户ID
     *
     * @param code
     * @return
     */
    public JsonObject getQiYeUserInfoByCode(String code) {
        String url = BaseUrlConstant.QIYE_GETUSERINFO.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity)).
                replace("CODE", code);
        String result = HttpsRequestUtil.httpsGet(url);
        JsonObject jsonObject = GsonUtil.parseJsonObject(result);
        Integer errorCode = Integer.parseInt(jsonObject.get("errcode") + "");
        if (isTokenLose(errorCode)) {
            return getQiYeUserInfoByCode(code);
        }
        if (isSuccess(errorCode, "获取用户信息")) {
            return jsonObject;
        }
        return null;
    }

}
