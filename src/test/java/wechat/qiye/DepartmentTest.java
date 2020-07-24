package wechat.qiye;

import com.google.gson.Gson;
import org.junit.Test;
import wechat.common.utils.QiYeWeChatUtils;
import wechat.qiye.entity.BaseParamsEntity;
import wechat.qiye.entity.DepartmentEntity;

import java.util.List;

public class DepartmentTest {

    private static QiYeWeChatUtils qiYeWeChatUtils;
    private static Gson gson = new Gson();

    static {
        BaseParamsEntity baseParamsEntity = new BaseParamsEntity();
        baseParamsEntity.setCorpId("");
        baseParamsEntity.setAddressBookSecret("");
        baseParamsEntity.setOpenGlobalAddressBookSecret(true);
        qiYeWeChatUtils = new QiYeWeChatUtils();
        qiYeWeChatUtils.openDepartmentCtrl(baseParamsEntity);
        qiYeWeChatUtils.openPersonnelCtrl(baseParamsEntity);
    }

    @Test
    public void getDepartment() {
        DepartmentEntity departmentEntitys = qiYeWeChatUtils.getDepartment("84");
        System.out.println(gson.toJson(departmentEntitys));
    }

    @Test
    public void getDepartmentList() {
        List<DepartmentEntity> departmentEntitys = qiYeWeChatUtils.getDepartmentList("84");
        System.out.println(gson.toJson(departmentEntitys));
    }

    @Test
    public void deleteDepartment() {
        boolean success = qiYeWeChatUtils.deleteDepartment("0102");
        System.out.println(gson.toJson(success));
    }

    @Test
    public void forceDeleteDepartment() {
        boolean success = qiYeWeChatUtils.forceDeleteDepartment("0102");
        System.out.println(gson.toJson(success));
    }

    @Test
    public void createDepartment() {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId("0103");
        departmentEntity.setName("钓鱼");
        departmentEntity.setNameEn("diaoyu");
        departmentEntity.setParentId("0102");
        boolean success = qiYeWeChatUtils.createDepartment(departmentEntity);
        System.out.println(success);
    }

    @Test
    public void updateDepartment() {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId("0102");
        departmentEntity.setName("钓鱼2");
        departmentEntity.setNameEn("diaoyu2");
        departmentEntity.setParentId("1");
        boolean success = qiYeWeChatUtils.updateDepartment(departmentEntity);
        System.out.println(success);
    }

}
