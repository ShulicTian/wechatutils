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
    public String getQiYeUserIdByCode(String code) {
        String url = BaseUrlConstant.QIYE_GETUSERINFO.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity)).
                replace("CODE", code);
        String result = HttpsRequestUtil.httpsGet(url);
        JsonObject jsonObject = GsonUtil.parseJsonObject(result);
        Integer errorCode = Integer.parseInt(jsonObject.get("errcode") + "");
        if (isTokenLose(errorCode)) {
            return getQiYeUserIdByCode(code);
        }
        if (isSuccess(errorCode, "获取用户信息")) {
            String id = jsonObject.get("UserId").getAsString();
            if (id == null) {
                id = jsonObject.get("UserId").getAsString();
            }
            return id;
        }
        return null;
    }

}
