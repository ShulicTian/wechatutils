package wechat.qiye.auth.ctrl;

import com.google.gson.JsonObject;
import wechat.common.constant.BaseUrlConstant;
import wechat.common.utils.AccessTokenUtil;
import wechat.common.utils.GsonUtil;
import wechat.common.utils.HttpsRequestUtil;
import wechat.qiye.common.entity.BaseParamsEntity;
import wechat.qiye.common.interfaces.BaseCtrlAbs;

/**
 * 登入认证工具类
 *
 * @author tianslc
 */
public class LoginAuthCtrl extends BaseCtrlAbs {

    public LoginAuthCtrl(BaseParamsEntity baseParamsEntity) {
        super(baseParamsEntity);
    }

    /**
     * 拼接OAuth2 Url
     *
     * @param redirectUrl
     * @param state
     * @return
     */
    public String getAuthUrl(String redirectUrl, String state) {
        String url = BaseUrlConstant.QIYE_OAUTH2.replace("CORPID", baseParamsEntity.getCorpId()).
                replace("REDIRECT_URI", redirectUrl).
                replace("STATE", state);
        return url;
    }

    /**
     * 拼接qrcode OAuth Url
     *
     * @param redirectUrl
     * @param state
     * @return
     */
    public String getQrcodeAuthUrl(String redirectUrl, String state) {
        String url = BaseUrlConstant.QIYE_QRCODE_OAUTH.replace("CORPID", baseParamsEntity.getCorpId()).
                replace("AGENTID", baseParamsEntity.getAgentId()).
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
    public String getUserIdByCode(String code) {
        String url = BaseUrlConstant.QIYE_GETUSERINFO.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(baseParamsEntity)).
                replace("CODE", code);
        String result = HttpsRequestUtil.httpsGet(url);
        JsonObject jsonObject = GsonUtil.parseJsonObject(result);
        Integer errorCode = Integer.parseInt(jsonObject.get("errcode") + "");
        if (isTokenLose(errorCode)) {
            return getUserIdByCode(code);
        }
        if (isSuccess(errorCode, "获取部门列表")) {
            String id = jsonObject.get("UserId").toString();
            if (id == null) {
                id = jsonObject.get("OpenId").toString();
            }
            return id;
        }
        return null;
    }

}
