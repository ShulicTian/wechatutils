package wechat.qiye;

import com.google.gson.Gson;
import org.junit.Test;
import wechat.qiye.common.entity.BaseParamsEntity;
import wechat.qiye.message.entity.ButtonEntity;
import wechat.qiye.message.entity.MessageEntity;
import wechat.qiye.message.entity.TaskCardMessage;
import wechat.qiye.message.entity.TaskCardMessageStatus;
import wechat.qiye.utils.QiYeWeChatUtil;

public class MessageTest {

    private static QiYeWeChatUtil qiYeWeChatUtil;
    private static BaseParamsEntity baseParamsEntity;
    private static Gson gson = new Gson();

    static {
        baseParamsEntity = new BaseParamsEntity();
        baseParamsEntity.setCorpId("");
        baseParamsEntity.setSecret("");
        baseParamsEntity.setAgentId("");
        qiYeWeChatUtils = new QiYeWeChatUtils();
        qiYeWeChatUtils.openMessageCtrl(baseParamsEntity);
    }

    @Test
    public void sendMessage() {
        TaskCardMessage taskCardMessage = new TaskCardMessage();
        taskCardMessage.setTitle("任务卡片Test");
        taskCardMessage.setDescription("2020年07月28日测试任务卡片消息");
        taskCardMessage.setUrl("https://www.baidu.com");
        taskCardMessage.setTaskId("A2001");
        ButtonEntity confirm = new ButtonEntity("yes1", "确定", "已确定");
        ButtonEntity cancel = new ButtonEntity("no1", "取消", "已取消");
        taskCardMessage.setBtn(new ButtonEntity[]{confirm, cancel});
        MessageEntity<TaskCardMessage> messageEntity = new MessageEntity<TaskCardMessage>();
        messageEntity.setToUser("|");
        messageEntity.setAgentId(baseParamsEntity.getAgentId());
        messageEntity.setMessage(taskCardMessage);
        qiYeWeChatUtil.sendMessage(messageEntity);

    }

    @Test
    public void updateStatus() {
        TaskCardMessageStatus taskCardMessageStatus = new TaskCardMessageStatus();
        taskCardMessageStatus.setAgentid(baseParamsEntity.getAgentId());
        taskCardMessageStatus.setTaskId("001");
        taskCardMessageStatus.setUserids(new String[]{""});
        taskCardMessageStatus.setClickedKey("yes1");
        qiYeWeChatUtil.updateMessageStatus(taskCardMessageStatus);
    }
}
