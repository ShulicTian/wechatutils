package wechat.qiye.common.interfaces;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wechat.qiye.common.constant.QiYeErrorEnum;
import wechat.qiye.common.entity.QiYeParamsEntity;
import wechat.qiye.utils.AccessTokenUtil;

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
    protected QiYeParamsEntity qiYeParamsEntity;

    public BaseCtrlAbs(QiYeParamsEntity qiYeParamsEntity) {
        this.qiYeParamsEntity = qiYeParamsEntity;
    }

    /**
     * 是否成功
     *
     * @param code
     * @param message
     * @return
     */
    public boolean isSuccess(Integer code, String message) {
        if (QiYeErrorEnum.OK.getCode().equals(code)) {
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
        if (!isReGetToken && QiYeErrorEnum.ACCESSTOKEN_ILLEGAL.getCode().equals(code)) {
            isReGetToken = true;
            AccessTokenUtil.requestAccessToken(qiYeParamsEntity);
            logger.info("【QiYeWeChat】{}", "重新请求AccessToken");
            return true;
        }
        return false;
    }

}
