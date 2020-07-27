package wechat.qiye.message.ctrl;

import wechat.common.constant.BaseUrlConstant;
import wechat.common.entity.BaseCtrlAbs;
import wechat.common.entity.BaseParamsEntity;
import wechat.common.utils.AccessTokenUtils;
import wechat.common.utils.HttpsRequestUtils;
import wechat.qiye.message.entity.Message;
import wechat.qiye.message.entity.MessageEntity;
import wechat.qiye.message.entity.SendReceiveEntity;
import wechat.qiye.message.entity.TaskCardMessageStatus;

/**
 * 消息控制器
 *
 * @author tianslc
 */
public class MessageCtrl extends BaseCtrlAbs {

    public MessageCtrl(BaseParamsEntity baseParamsEntity) {
        super(baseParamsEntity);
    }

    /**
     * 发送消息
     *
     * @param messageEntity
     */
    public boolean send(MessageEntity<? extends Message> messageEntity) {
        String url = BaseUrlConstant.QIYE_SEND_MESSAGE.
                replace("ACCESS_TOKEN", AccessTokenUtils.getAccessToken(baseParamsEntity));
        String result = HttpsRequestUtils.httpsPost(url, gson.toJson(messageEntity).getBytes());
        SendReceiveEntity sendReceiveEntity = gson.fromJson(result, SendReceiveEntity.class);
        Integer errorCode = sendReceiveEntity.getErrcode();
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return send(messageEntity);
        }
        return isSuccess(errorCode, "发送消息");
    }

    /**
     * 更新任务卡片消息
     *
     * @param taskCardMessageStatus
     */
    public boolean updateMessageStatus(TaskCardMessageStatus taskCardMessageStatus) {
        String url = BaseUrlConstant.QIYE_UPDATE_MESSAGE.
                replace("ACCESS_TOKEN", AccessTokenUtils.getAccessToken(baseParamsEntity));
        String result = HttpsRequestUtils.httpsPost(url, gson.toJson(taskCardMessageStatus).getBytes());
        SendReceiveEntity sendReceiveEntity = gson.fromJson(result, SendReceiveEntity.class);
        Integer errorCode = sendReceiveEntity.getErrcode();
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return updateMessageStatus(taskCardMessageStatus);
        }
        return isSuccess(errorCode, "更新消息状态");
    }

}
