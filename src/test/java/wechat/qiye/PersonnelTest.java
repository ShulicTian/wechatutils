package wechat.qiye;

import com.google.gson.Gson;
import org.junit.Test;
import wechat.common.entity.BaseParamsEntity;
import wechat.qiye.addressbook.entity.PersonnelEntity;
import wechat.qiye.utils.QiYeWeChatUtil;

import java.util.List;

public class PersonnelTest {

    private static QiYeWeChatUtil qiYeWeChatUtil;
    private static Gson gson = new Gson();

    static {
        BaseParamsEntity baseParamsEntity = new BaseParamsEntity("", "", true);
        qiYeWeChatUtil = new QiYeWeChatUtil();
        qiYeWeChatUtil.openPersonnelCtrl(baseParamsEntity);
    }

    @Test
    public void getPersonInfo() {
        PersonnelEntity personnelEntitys = qiYeWeChatUtils.getPersonnel("");
        System.out.println(gson.toJson(personnelEntitys));
    }

    @Test
    public void deletePersonInfo() {
        boolean success = qiYeWeChatUtils.deletePersonnel("");
        System.out.println(gson.toJson(success));
    }

    @Test
    public void batchDeletePerson() {
        boolean success = qiYeWeChatUtils.batchDeletePersonnel(new String[]{""});
        System.out.println(gson.toJson(success));
    }

    @Test
    public void createPersonInfo() {
        PersonnelEntity personnelEntity = new PersonnelEntity();
        personnelEntity.setUserId("");
        personnelEntity.setName("田舒利聪钓鱼小号");
        personnelEntity.setMobile("");
        personnelEntity.setDepartment(new String[]{"0102"});
        personnelEntity.setOrder(new Integer[]{0});
        boolean success = qiYeWeChatUtil.createPersonnel(personnelEntity);
        System.out.println(success);
    }

    @Test
    public void updatePersonInfo() {
        PersonnelEntity personnelEntity = new PersonnelEntity();
        personnelEntity.setUserId("");
        personnelEntity.setName("田舒利聪钓鱼小号a");
        personnelEntity.setMobile("");
        personnelEntity.setDepartment(new String[]{"0102","0103"});
        personnelEntity.setOrder(new Integer[]{0});
        boolean success = qiYeWeChatUtil.updatePersonnel(personnelEntity);
        System.out.println(success);
    }

    @Test
    public void getPersonList() {
        List<PersonnelEntity> PersonnelList = qiYeWeChatUtil.getPersonnelList("0102", "0");
        System.out.println(gson.toJson(PersonnelList));
    }

}
