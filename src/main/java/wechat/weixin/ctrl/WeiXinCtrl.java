package wechat.weixin.ctrl;

import wechat.common.cache.RedisSwitch;
import wechat.weixin.entity.WeiXinParamsEntity;

/**
 * @author tianslc
 * @date 2024/3/29
 */
public class WeiXinCtrl extends RedisSwitch {

    private WeiXinParamsEntity commonParams = null;

    public WeiXinCtrl(WeiXinParamsEntity commonParams) {
        this.commonParams = commonParams;
    }
}
