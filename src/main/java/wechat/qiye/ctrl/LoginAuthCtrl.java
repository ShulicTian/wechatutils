package wechat.qiye.ctrl;

import com.google.gson.JsonObject;
import wechat.common.constant.BaseConstant;
import wechat.common.entity.BaseCtrlAbs;
import wechat.common.utils.GsonUtils;
import wechat.common.utils.HttpsRequestUtils;
import wechat.qiye.entity.BaseParamsEntity;
import wechat.qiye.utils.AccessTokenUtils;

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
        String url = BaseConstant.QIYE_OAUTH2.replace("CORPID", baseParamsEntity.getCorpId()).
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
        String url = BaseConstant.QIYE_GETUSERINFO.replace("ACCESS_TOKEN", AccessTokenUtils.getAccessToken(baseParamsEntity)).
                replace("CODE", code);
        String result = HttpsRequestUtils.httpsGet(url);
        JsonObject jsonObject = GsonUtils.parseJsonObject(result);
        Integer errorCode = Integer.parseInt(jsonObject.get("errcode") + "");
        if (isTokenLose(errorCode)) {
            return getUserIdByCode(code);
        }
        if (isSuccess(errorCode, "获取部门列表")) {
            return jsonObject.get("userId").toString();
        }
        return null;
    }

}
