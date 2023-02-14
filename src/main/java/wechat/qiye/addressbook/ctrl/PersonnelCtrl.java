package wechat.qiye.addressbook.ctrl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import wechat.common.constant.BaseUrlConstant;
import wechat.common.utils.GsonUtil;
import wechat.common.utils.HttpsRequestUtil;
import wechat.qiye.addressbook.adapter.PersonnelTypeAdapter;
import wechat.qiye.addressbook.entity.PersonnelEntity;
import wechat.qiye.addressbook.entity.PersonnelSingleEntity;
import wechat.qiye.addressbook.entity.QrcodeEntity;
import wechat.qiye.common.constant.QiYeUriEnum;
import wechat.qiye.common.entity.QiYeParamsEntity;
import wechat.common.entity.ReceiveEntity;
import wechat.qiye.common.interfaces.BaseCtrl;
import wechat.qiye.common.interfaces.BaseCtrlAbs;
import wechat.qiye.utils.AccessTokenUtil;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * 成员控制器
 *
 * @author tianslc
 */
public class PersonnelCtrl extends BaseCtrlAbs implements BaseCtrl<PersonnelEntity> {

    public PersonnelCtrl(QiYeParamsEntity qiYeParamsEntity) {
        super(qiYeParamsEntity);
    }

    /**
     * 创建人员
     *
     * @param personnelEntity
     */
    @Override
    public boolean create(PersonnelEntity personnelEntity) {
        String url = BaseUrlConstant.QIYE_CU_PERSONNEL.replace("METHOD", QiYeUriEnum.CREATE.getUri()).replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity));
        String result = HttpsRequestUtil.httpsPost(url, personnelEntityToJsonStringByAdapter(personnelEntity).getBytes(StandardCharsets.UTF_8));
        ReceiveEntity receiveEntity = gson.fromJson(result, ReceiveEntity.class);
        Integer errorCode = receiveEntity.getErrcode();
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return create(personnelEntity);
        }
        return isSuccess(errorCode, "创建人员");
    }

    /**
     * 更新人员
     *
     * @param personnelEntity
     */
    @Override
    public boolean update(PersonnelEntity personnelEntity) {
        String url = BaseUrlConstant.QIYE_CU_PERSONNEL.replace("METHOD", QiYeUriEnum.UPDATE.getUri()).replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity));
        String result = HttpsRequestUtil.httpsPost(url, personnelEntityToJsonStringByAdapter(personnelEntity).getBytes(StandardCharsets.UTF_8));
        ReceiveEntity receiveEntity = gson.fromJson(result, ReceiveEntity.class);
        Integer errorCode = receiveEntity.getErrcode();
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return update(personnelEntity);
        }
        return isSuccess(errorCode, "更新人员");
    }

    /**
     * 获取人员
     *
     * @param personnelId
     */
    @Override
    public PersonnelEntity get(String personnelId) {
        String url = BaseUrlConstant.QIYE_RD_PERSONNEL.
                replace("METHOD", QiYeUriEnum.GET.getUri()).
                replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity)).
                replace("USERID", personnelId);
        String result = HttpsRequestUtil.httpsGet(url);
        PersonnelEntity nowPersonnel = gson.fromJson(result, PersonnelEntity.class);
        Integer errorCode = nowPersonnel.getErrcode();
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return get(personnelId);
        }
        if (isSuccess(errorCode, "获取人员")) {
            return nowPersonnel;
        }
        return null;
    }

    /**
     * 删除人员
     *
     * @param personnelId
     */
    @Override
    public boolean delete(String personnelId) {
        String url = BaseUrlConstant.QIYE_RD_PERSONNEL.
                replace("METHOD", QiYeUriEnum.DELETE.getUri()).
                replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity)).
                replace("USERID", personnelId);
        String result = HttpsRequestUtil.httpsGet(url);
        ReceiveEntity receiveEntity = gson.fromJson(result, PersonnelEntity.class);
        Integer errorCode = receiveEntity.getErrcode();
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return delete(personnelId);
        }
        return isSuccess(errorCode, "删除人员");
    }

    /**
     * 批量删除人员
     *
     * @param personnelIds
     */
    public boolean batchDelete(String[] personnelIds) {
        String url = BaseUrlConstant.QIYE_RD_PERSONNEL.
                replace("METHOD", QiYeUriEnum.BATCHDELETE.getUri()).
                replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity));
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("useridlist", GsonUtil.parseJsonArray(gson.toJson(personnelIds)));
        String result = HttpsRequestUtil.httpsPost(url, jsonObject.toString().getBytes(StandardCharsets.UTF_8));
        ReceiveEntity receiveEntity = gson.fromJson(result, PersonnelEntity.class);
        Integer errorCode = receiveEntity.getErrcode();
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return batchDelete(personnelIds);
        }
        return isSuccess(errorCode, "批量删除人员");
    }

    /**
     * PersonnelEntity对象按特定适配器转换成json字符串
     *
     * @param personnelEntity
     * @return
     */
    private String personnelEntityToJsonStringByAdapter(PersonnelEntity personnelEntity) {
        Gson gson = new GsonBuilder().registerTypeAdapter(PersonnelEntity.class, new PersonnelTypeAdapter()).create();
        return gson.toJson(personnelEntity);
    }

    /**
     * 获取部门下人员
     *
     * @param departmentId
     * @return
     */
    public List<PersonnelEntity> getDepartmentPersonnelList(String departmentId) {
        String url = BaseUrlConstant.QIYE_DEPARTMENT_PERSONNEL.
                replace("METHOD", QiYeUriEnum.SIMPLELIST.getUri()).
                replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity)).
                replace("DEPARTMENT_ID", departmentId);
        String result = HttpsRequestUtil.httpsGet(url);
        JsonObject jsonObject = GsonUtil.parseJsonObject(result);
        JsonArray userlist = jsonObject.getAsJsonArray("userlist");
        List<PersonnelEntity> personnelList = Arrays.asList(gson.fromJson(userlist, PersonnelEntity[].class));
        Integer errorCode = Integer.parseInt(jsonObject.get("errcode") + "");
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return getDepartmentPersonnelList(departmentId);
        }
        if (isSuccess(errorCode, "获取人员列表")) {
            return personnelList;
        }
        return null;
    }

    /**
     * 获取部门下人员ID
     *
     * @return
     */
    public List<PersonnelSingleEntity> getPersonnelIdList() {
        String url = BaseUrlConstant.QIYE_DEPARTMENT_PERSONNEL_ID.
                replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity));
        String result = HttpsRequestUtil.httpsGet(url);
        JsonObject jsonObject = GsonUtil.parseJsonObject(result);
        JsonArray userlist = jsonObject.getAsJsonArray("dept_user");
        List<PersonnelSingleEntity> personnelList = Arrays.asList(gson.fromJson(userlist, PersonnelSingleEntity[].class));
        Integer errorCode = Integer.parseInt(jsonObject.get("errcode") + "");
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return getPersonnelIdList();
        }
        if (isSuccess(errorCode, "获取人员列表")) {
            return personnelList;
        }
        return null;
    }

    /**
     * 获取部门下人员详情
     *
     * @param departmentId
     * @return
     */
    public List<PersonnelEntity> getDepartmentPersonnelDescList(String departmentId) {
        String url = BaseUrlConstant.QIYE_DEPARTMENT_PERSONNEL.
                replace("METHOD", QiYeUriEnum.LIST.getUri()).
                replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity)).
                replace("DEPARTMENT_ID", departmentId);
        String result = HttpsRequestUtil.httpsGet(url);
        JsonObject jsonObject = GsonUtil.parseJsonObject(result);
        JsonArray userlist = jsonObject.getAsJsonArray("userlist");
        List<PersonnelEntity> personnelList = Arrays.asList(gson.fromJson(userlist, PersonnelEntity[].class));
        Integer errorCode = Integer.parseInt(jsonObject.get("errcode") + "");
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return getDepartmentPersonnelDescList(departmentId);
        }
        if (isSuccess(errorCode, "获取人员详情列表")) {
            return personnelList;
        }
        return null;
    }

    /**
     * 获取企业二维码
     *
     * @param sizeType
     */
    public QrcodeEntity getQrcode(String sizeType) {
        String url = BaseUrlConstant.QIYE_QRCODE.
                replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity)).
                replace("SIZE_TYPE", sizeType);
        String result = HttpsRequestUtil.httpsGet(url);
        QrcodeEntity qrcodeEntity = gson.fromJson(result, QrcodeEntity.class);
        Integer errorCode = qrcodeEntity.getErrcode();
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return getQrcode(sizeType);
        }
        if (isSuccess(errorCode, "获取企业二维码")) {
            return qrcodeEntity;
        }
        return null;
    }
}
