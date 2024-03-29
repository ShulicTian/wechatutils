package wechat.qiye;

import com.google.gson.Gson;
import org.junit.Test;
import wechat.qiye.addressbook.entity.DepartmentEntity;
import wechat.qiye.common.entity.BaseParamsEntity;
import wechat.qiye.utils.QiYeWeChatUtil;

import java.util.List;

public class DepartmentTest {

    private static Gson gson = new Gson();

    /*

        static {
            BaseParamsEntity baseParamsEntity = new BaseParamsEntity("", "", true);
            qiYeWeChatUtil = new QiYeWeChatUtil();
            qiYeWeChatUtil.openDepartmentCtrl(baseParamsEntity);
            qiYeWeChatUtil.openPersonnelCtrl(baseParamsEntity);
        }

        @Test
        public void getDepartment() {
            DepartmentEntity departmentEntitys = qiYeWeChatUtil.getDepartment("84");
            System.out.println(gson.toJson(departmentEntitys));
        }

        @Test
        public void getDepartmentList() {
            List<DepartmentEntity> departmentEntitys = qiYeWeChatUtil.getDepartmentList("84");
            System.out.println(gson.toJson(departmentEntitys));
        }

        @Test
        public void deleteDepartment() {
            boolean success = qiYeWeChatUtil.deleteDepartment("0102");
            System.out.println(gson.toJson(success));
        }

        @Test
        public void forceDeleteDepartment() {
            boolean success = qiYeWeChatUtil.forceDeleteDepartment("0102");
            System.out.println(gson.toJson(success));
        }

        @Test
        public void createDepartment() {
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setId("0102");
            departmentEntity.setName("钓鱼");
            departmentEntity.setNameEn("diaoyu");
            departmentEntity.setParentId("1");
            boolean success = qiYeWeChatUtil.createDepartment(departmentEntity);
            System.out.println(success);
        }

        @Test
        public void updateDepartment() {
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setId("0102");
            departmentEntity.setName("钓鱼2");
            departmentEntity.setNameEn("diaoyu2");
            departmentEntity.setParentId("1");
            boolean success = qiYeWeChatUtil.updateDepartment(departmentEntity);
            System.out.println(success);
        }
    */

}
