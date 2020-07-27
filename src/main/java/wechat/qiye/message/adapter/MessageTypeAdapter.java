package wechat.qiye.message.adapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wechat.qiye.message.entity.*;

import java.io.IOException;

/**
 * 成员信息实体序列化适配器
 *
 * @author tianslc
 */
public class MessageTypeAdapter<T> extends TypeAdapter<BaseMessage<T>> {

    private Logger logger = LogManager.getLogger(MessageTypeAdapter.class);

    @Override
    public void write(JsonWriter out, BaseMessage<T> value) throws IOException {
        out.beginObject();

        Gson gson = new Gson();
        boolean isNext = true;

        //判断touser、toparty、totag是否都为空
        if (StringUtils.isEmpty(value.getToUser()) && StringUtils.isEmpty(value.getToParty()) && StringUtils.isEmpty(value.getToTag())) {
            isNext = false;
            logger.error("【MessageTypeAdapter】{}不能都为空", "touser|toparty|totag");
        }

        if (StringUtils.isEmpty(value.getMsgType())) {
            isNext = false;
            logger.error("【MessageTypeAdapter】{}不能为空", "类型");
        }

        if (StringUtils.isEmpty(value.getAgentId())) {
            isNext = false;
            logger.error("【MessageTypeAdapter】{}不能为空", "AgentId");
        }

        if (value.getMessage() == null) {
            isNext = false;
            logger.error("【MessageTypeAdapter】{}不能为空", "消息体");
        }

        if (isNext) {

            if (StringUtils.isNotEmpty(value.getToUser())) {
                out.name("touser");
                out.value(value.getToUser());
            }

            if (StringUtils.isNotEmpty(value.getToParty())) {
                out.name("toparty");
                out.value(value.getToParty());
            }

            if (StringUtils.isNotEmpty(value.getToTag())) {
                out.name("totag");
                out.value(value.getToTag());
            }

            if (StringUtils.isNotEmpty(value.getAgentId())) {
                out.name("agentid");
                out.value(value.getAgentId());
            }

            if (StringUtils.isNotEmpty(value.getMsgType())) {
                out.name("msgtype");
                out.value(value.getMsgType());
            }

            if (value.getMessage() != null) {
                if (value.getMessage() instanceof TextMessage) {
                    out.name("text");
                }
                if (value.getMessage() instanceof ImageMessage) {
                    out.name("image");
                }
                if (value.getMessage() instanceof VoiceMessage) {
                    out.name("voice");
                }
                if (value.getMessage() instanceof VideoMessage) {
                    out.name("video");
                }
                if (value.getMessage() instanceof FileMessage) {
                    out.name("file");
                }
                if (value.getMessage() instanceof TextCardMessage) {
                    out.name("textcard");
                }
                if (value.getMessage() instanceof NewsMessage) {
                    out.name("news");
                }
                if (value.getMessage() instanceof MpnewsMessage) {
                    out.name("mpnews");
                }
                if (value.getMessage() instanceof MarkdownMessage) {
                    out.name("markdown");
                }
                if (value.getMessage() instanceof MiniprogramNoticeMessage) {
                    out.name("miniprogram_notice");
                }
                if (value.getMessage() instanceof TaskCardMessage) {
                    out.name("taskcard");
                }
                out.jsonValue(gson.toJson(value.getMessage()));
            }

            if (StringUtils.isNotEmpty(value.getSafe())) {
                out.name("safe");
                out.value(value.getSafe());
            }

            if (StringUtils.isNotEmpty(value.getEnableIdTrans())) {
                out.name("enable_id_trans");
                out.value(value.getEnableIdTrans());
            }

            if (StringUtils.isNotEmpty(value.getEnableDuplicateCheck())) {
                out.name("enable_duplicate_check");
                out.value(value.getEnableDuplicateCheck());
            }

            if (StringUtils.isNotEmpty(value.getDuplicateCheckInterval())) {
                out.name("duplicate_check_interval");
                out.value(value.getDuplicateCheckInterval());
            }

        }
        out.endObject();
    }

    @Override
    public BaseMessage<T> read(JsonReader in) throws IOException {
        return null;
    }
}
