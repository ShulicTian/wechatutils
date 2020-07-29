package wechat.common.entity;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wechat.common.constant.ErrorEnum;
import wechat.common.utils.AccessTokenUtil;

/**
 * 基础控制器继承类
 *
 * @author tianslc
 */
public abstract class BaseCtrlAbs {
    protected Logger logger = LogManager.getLogger(BaseCtrlAbs.class);
    protected Gson gson = new Gson();
    /**
     * 控制是否重新获取AccessToken
     */
    protected boolean isReGetToken = false;
    protected BaseParamsEntity baseParamsEntity;

    public BaseCtrlAbs(BaseParamsEntity baseParamsEntity) {
        this.baseParamsEntity = baseParamsEntity;
    }

    /**
     * 是否成功
     *
     * @param code
     * @param message
     * @return
     */
    public boolean isSuccess(Integer code, String message) {
        if (ErrorEnum.OK.getCode().equals(code)) {
            isReGetToken = false;
            logger.info("【QiYeWeChat】{}{} ", message, "成功");
            return true;
        }
        logger.info("【QiYeWeChat】{}{}[{}]", message, "失败", code);
        return false;
    }

    /**
     * AccessToken是否失效
     *
     * @param code
     * @return
     */
    public boolean isTokenLose(Integer code) {
        // 只会重新请求一次，若还是不合法则不重复请求
        if (!isReGetToken && ErrorEnum.ACCESSTOKEN_ILLEGAL.getCode().equals(code)) {
            isReGetToken = true;
            AccessTokenUtil.requestAccessToken(baseParamsEntity);
            logger.info("【QiYeWeChat】{}", "重新请求AccessToken");
            return true;
        }
        return false;
    }

}
