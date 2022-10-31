package wechat.qiye.addressbook.ctrl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import wechat.common.constant.BaseUrlConstant;
import wechat.common.utils.GsonUtil;
import wechat.common.utils.HttpsRequestUtil;
import wechat.qiye.addressbook.entity.DepartmentEntity;
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
 * 部门控制器
 *
 * @author tianslc
 */
public class DepartmentCtrl extends BaseCtrlAbs implements BaseCtrl<DepartmentEntity> {

    public DepartmentCtrl(QiYeParamsEntity qiYeParamsEntity) {
        super(qiYeParamsEntity);
    }

    /**
     * 创建部门
     *
     * @param departmentEntity
     */
    public boolean create(DepartmentEntity departmentEntity) {
        String url = BaseUrlConstant.QIYE_CU_DEPARTMENT.replace("METHOD", QiYeUriEnum.CREATE.getUri()).replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity));
        String result = HttpsRequestUtil.httpsPost(url, gson.toJson(departmentEntity).getBytes(StandardCharsets.UTF_8));
        ReceiveEntity receiveEntity = gson.fromJson(result, ReceiveEntity.class);
        Integer errorCode = receiveEntity.getErrcode();
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return create(departmentEntity);
        }
        return isSuccess(errorCode, "创建部门");
    }

    /**
     * 更新部门
     *
     * @param departmentEntity
     */
    public boolean update(DepartmentEntity departmentEntity) {
        String url = BaseUrlConstant.QIYE_CU_DEPARTMENT.replace("METHOD", QiYeUriEnum.UPDATE.getUri()).replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity));
        String result = HttpsRequestUtil.httpsPost(url, gson.toJson(departmentEntity).getBytes(StandardCharsets.UTF_8));
        ReceiveEntity receiveEntity = gson.fromJson(result, ReceiveEntity.class);
        Integer errorCode = receiveEntity.getErrcode();
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return update(departmentEntity);
        }
        return isSuccess(errorCode, "更新部门");
    }

    /**
     * 删除部门
     *
     * @param departmentId
     */
    public boolean delete(String departmentId) {
        String url = BaseUrlConstant.QIYE_RD_DEPARTMENT.
                replace("METHOD", QiYeUriEnum.DELETE.getUri()).
                replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity)).
                replace("ID", departmentId);
        String result = HttpsRequestUtil.httpsGet(url);
        ReceiveEntity receiveEntity = gson.fromJson(result, DepartmentEntity.class);
        Integer errorCode = receiveEntity.getErrcode();
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return delete(departmentId);
        }
        return isSuccess(errorCode, "删除部门");
    }

    /**
     * 获取部门
     *
     * @param departmentId
     */
    public DepartmentEntity get(String departmentId) {
        String url = BaseUrlConstant.QIYE_RD_DEPARTMENT.
                replace("METHOD", QiYeUriEnum.GET.getUri()).
                replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity)).
                replace("#ID", departmentId);
        String result = HttpsRequestUtil.httpsGet(url);
        JsonObject jsonObject = GsonUtil.parseJsonObject(result);
        JsonObject departments = jsonObject.getAsJsonObject("department");
        DepartmentEntity department = gson.fromJson(departments, DepartmentEntity.class);
        Integer errorCode = Integer.parseInt(jsonObject.get("errcode") + "");
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return get(departmentId);
        }
        if (isSuccess(errorCode, "获取部门列表")) {
            return department;
        }
        return null;
    }

    /**
     * 获取部门列表
     *
     * @param departmentId
     */
    public List<DepartmentEntity> getList(String departmentId) {
        String url = BaseUrlConstant.QIYE_RD_DEPARTMENT.
                replace("METHOD", QiYeUriEnum.LIST.getUri()).
                replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity)).
                replace("#ID", departmentId);
        String result = HttpsRequestUtil.httpsGet(url);
        JsonObject jsonObject = GsonUtil.parseJsonObject(result);
        JsonArray departments = jsonObject.getAsJsonArray("department");
        List<DepartmentEntity> list = Arrays.asList(gson.fromJson(departments, DepartmentEntity[].class));
        Integer errorCode = Integer.parseInt(jsonObject.get("errcode") + "");
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return getList(departmentId);
        }
        if (isSuccess(errorCode, "获取部门列表")) {
            return list;
        }
        return null;
    }

    /**
     * 获取部门ID列表
     *
     * @param departmentId
     */
    public List<DepartmentEntity> getIdList(String departmentId) {
        String url = BaseUrlConstant.QIYE_RD_DEPARTMENT.
                replace("METHOD", QiYeUriEnum.SIMPLELIST.getUri()).
                replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity)).
                replace("#ID", departmentId);
        String result = HttpsRequestUtil.httpsGet(url);
        JsonObject jsonObject = GsonUtil.parseJsonObject(result);
        JsonArray departments = jsonObject.getAsJsonArray("department_id");
        List<DepartmentEntity> list = Arrays.asList(gson.fromJson(departments, DepartmentEntity[].class));
        Integer errorCode = Integer.parseInt(jsonObject.get("errcode") + "");
        // 第一次请求如果token失效会重新获取token再请求一次
        if (isTokenLose(errorCode)) {
            return getList(departmentId);
        }
        if (isSuccess(errorCode, "获取部门ID列表")) {
            return list;
        }
        return null;
    }
}
