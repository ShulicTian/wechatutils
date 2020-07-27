package wechat.qiye;

import com.google.gson.Gson;
import org.junit.Test;
import wechat.common.entity.BaseParamsEntity;
import wechat.common.utils.QiYeWeChatUtils;
import wechat.qiye.addressbook.entity.PersonnelEntity;

import java.util.List;

public class PersonnelTest {

    private static QiYeWeChatUtils qiYeWeChatUtils;
    private static Gson gson = new Gson();

    static {
        BaseParamsEntity baseParamsEntity = new BaseParamsEntity();
        baseParamsEntity.setCorpId("");
        baseParamsEntity.setAddressBookSecret("");
        baseParamsEntity.setOpenGlobalAddressBookSecret(true);
        qiYeWeChatUtils = new QiYeWeChatUtils();
        qiYeWeChatUtils.openPersonnelCtrl(baseParamsEntity);
    }

    @Test
    public void getPersonInfo() {
        PersonnelEntity personnelEntitys = qiYeWeChatUtils.getPersonnel("18814470102");
        System.out.println(gson.toJson(personnelEntitys));
    }

    @Test
    public void deletePersonInfo() {
        boolean success = qiYeWeChatUtils.deletePersonnel("15361830419");
        System.out.println(gson.toJson(success));
    }

    @Test
    public void batchDeletePerson() {
        boolean success = qiYeWeChatUtils.batchDeletePersonnel(new String[]{"15361830419"});
        System.out.println(gson.toJson(success));
    }

    @Test
    public void createPersonInfo() {
        PersonnelEntity personnelEntity = new PersonnelEntity();
        personnelEntity.setUserId("15361830419");
        personnelEntity.setName("田舒利聪钓鱼小号");
        personnelEntity.setMobile("15361830419");
        personnelEntity.setDepartment(new String[]{"0102"});
        personnelEntity.setOrder(new Integer[]{0});
        boolean success = qiYeWeChatUtils.createPersonnel(personnelEntity);
        System.out.println(success);
    }

    @Test
    public void updatePersonInfo() {
        PersonnelEntity personnelEntity = new PersonnelEntity();
        personnelEntity.setUserId("15361830419");
        personnelEntity.setName("田舒利聪钓鱼小号a");
        personnelEntity.setMobile("15361830419");
        personnelEntity.setDepartment(new String[]{"0102","0103"});
        personnelEntity.setOrder(new Integer[]{0});
        boolean success = qiYeWeChatUtils.updatePersonnel(personnelEntity);
        System.out.println(success);
    }

    @Test
    public void getPersonList() {
        List<PersonnelEntity> PersonnelList = qiYeWeChatUtils.getPersonnelList("0102", "0");
        System.out.println(gson.toJson(PersonnelList));
    }

}
