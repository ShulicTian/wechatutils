package wechat.qiye.addressbook.adapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wechat.qiye.addressbook.entity.PersonnelEntity;

import java.io.IOException;

/**
 * 成员信息实体序列化适配器
 *
 * @author tianslc
 */
public class PersonnelTypeAdapter extends TypeAdapter<PersonnelEntity> {

    private Logger logger = LogManager.getLogger(PersonnelTypeAdapter.class);

    @Override
    public void write(JsonWriter out, PersonnelEntity value) throws IOException {
        out.beginObject();

        Gson gson = new Gson();
        boolean isNext = true;

        //判断userId是否为空
        if (StringUtils.isEmpty(value.getUserId())) {
            isNext = false;
            logger.error("【PersonnelTypeAdapter】{}不能为空", "userId");
        } else {
            out.name("userid");
            out.value(value.getUserId());
        }

        //判断name是否为空
        if (StringUtils.isEmpty(value.getName())) {
            isNext = false;
            logger.error("【PersonnelTypeAdapter】{}不能为空", "name");
        } else {
            out.name("name");
            out.value(value.getName());
        }

        // 若手机号和邮箱都为空则不继续
        if (StringUtils.isEmpty(value.getMobile()) && StringUtils.isEmpty(value.getEmail())) {
            isNext = false;
        }

        if (isNext) {
            if (StringUtils.isNotEmpty(value.getAlias())) {
                out.name("alias");
                out.value(value.getAlias());
            }

            if (StringUtils.isNotEmpty(value.getMobile())) {
                out.name("mobile");
                out.value(value.getMobile());
            }

            if (value.getDepartment() != null && value.getDepartment().length != 0) {
                out.name("department");
                out.jsonValue(gson.toJson(value.getDepartment()));
                if (value.getOrder() != null && value.getOrder().length != 0) {
                    out.name("order");
                    out.jsonValue(gson.toJson(value.getOrder()));
                }
                if (value.getIsLaderInDept() != null && value.getIsLaderInDept().length != 0) {
                    out.name("is_lader_in_dept");
                    out.jsonValue(gson.toJson(value.getIsLaderInDept()));
                }
            }

            if (StringUtils.isNotEmpty(value.getPosition())) {
                out.name("position");
                out.value(value.getPosition());
            }

            if (StringUtils.isNotEmpty(value.getGender())) {
                out.name("gender");
                out.value(value.getGender());
            }

            if (StringUtils.isNotEmpty(value.getEmail())) {
                out.name("email");
                out.value(value.getEmail());
            }

            if (value.getEnable() != null) {
                out.name("enable");
                out.value(value.getEnable());
            }

            if (StringUtils.isNotEmpty(value.getAvatarMediaid())) {
                out.name("avatar_mediaid");
                out.value(value.getAvatarMediaid());
            }

            if (StringUtils.isNotEmpty(value.getTelephone())) {
                out.name("telephone");
                out.value(value.getTelephone());
            }

            if (StringUtils.isNotEmpty(value.getAddress())) {
                out.name("address");
                out.value(value.getAddress());
            }

            if (StringUtils.isNotEmpty(value.getMainDepartment())) {
                out.name("main_department");
                out.value(value.getMainDepartment());
            }

            if (value.getExtattr() != null && value.getExtattr().getAttrs() != null && value.getExtattr().getAttrs().size() != 0) {
                out.name("extattr");
                out.jsonValue(gson.toJson(value.getExtattr()));
            }

            if (value.getToInvite() != null) {
                out.name("to_invite");
                out.value(value.getToInvite());
            }

            if (StringUtils.isNotEmpty(value.getExternalPosition())) {
                out.name("external_position");
                out.value(value.getExternalPosition());
            }

            if (value.getExternalProfile() != null) {
                boolean isNotNull = value.getExternalProfile().getExternalAttr() != null && value.getExternalProfile().getExternalAttr().size() != 0;
                if (StringUtils.isNotEmpty(value.getExternalProfile().getExternalCorpName()) || isNotNull) {
                    out.name("external_profile");
                    out.jsonValue(gson.toJson(value.getExternalProfile()));
                }
            }
        }

        out.endObject();
    }

    @Override
    public PersonnelEntity read(JsonReader in) throws IOException {
        return null;
    }
}
