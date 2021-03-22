package wechat.qiye;

import com.google.gson.Gson;
import org.junit.Test;
import wechat.qiye.addressbook.entity.PersonnelEntity;
import wechat.qiye.common.entity.BaseParamsEntity;
import wechat.qiye.utils.QiYeWeChatUtil;

import java.util.ArrayList;
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
        List<PersonnelEntity> PersonnelList = qiYeWeChatUtil.getPersonnelList("84", "0");
        List<String> list = new ArrayList<>();
        int ind = 0;
        for (PersonnelEntity person : PersonnelList) {
            list.add(person.getUserId());
            ind++;
            if (ind == 200) {
//                qiYeWeChatUtil.batchDeletePersonnel(list.toArray(new String[list.size()]));
                list = new ArrayList<>();
                ind = 0;
            }
        }

//        System.out.println(gson.toJson(PersonnelList));
    }

}
