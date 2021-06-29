package wechat.qiye.message.ctrl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import wechat.common.constant.BaseUrlConstant;
import wechat.common.utils.HttpsRequestUtil;
import wechat.qiye.common.entity.QiYeParamsEntity;
import wechat.qiye.common.interfaces.BaseCtrlAbs;
import wechat.qiye.message.adapter.MessageTypeAdapter;
import wechat.qiye.message.entity.Message;
import wechat.qiye.message.entity.MessageEntity;
import wechat.qiye.message.entity.SendReceiveEntity;
import wechat.qiye.message.entity.TaskCardMessageStatus;
import wechat.qiye.utils.AccessTokenUtil;

import java.nio.charset.StandardCharsets;

/**
 * 消息控制器
 *
 * @author tianslc
 */
public class MessageCtrl extends BaseCtrlAbs {

    public MessageCtrl(QiYeParamsEntity qiYeParamsEntity) {
        super(qiYeParamsEntity);
    }

    /**
     * 发送消息
     *
     * @param messageEntity
     */
    public boolean send(MessageEntity<? extends Message> messageEntity) {
        String url = BaseUrlConstant.QIYE_SEND_MESSAGE.
                replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity));
        String params = messageEntityToJsonStringByAdapter(messageEntity);
        String result = HttpsRequestUtil.httpsPost(url, params.getBytes(StandardCharsets.UTF_8));
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
                replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity));
        String result = HttpsRequestUtil.httpsPost(url, gson.toJson(taskCardMessageStatus).getBytes(StandardCharsets.UTF_8));
        SendReceiveEntity sendReceiveEntity = gson.fromJson(result, SendReceiveEntity.class);
        Integer errorCode = sendReceiveEntity.getErrcode();
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return updateMessageStatus(taskCardMessageStatus);
        }
        return isSuccess(errorCode, "更新消息状态");
    }

    /**
     * MessageEntity对象按特定适配器转换成json字符串
     *
     * @param messageEntity
     * @return
     */
    private String messageEntityToJsonStringByAdapter(MessageEntity<? extends Message> messageEntity) {
        Gson gson = new GsonBuilder().registerTypeAdapter(MessageEntity.class, new MessageTypeAdapter()).create();
        return gson.toJson(messageEntity);
    }

}
