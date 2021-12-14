package wechat.qiye.addressbook.ctrl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import wechat.common.constant.BaseUrlConstant;
import wechat.common.utils.GsonUtil;
import wechat.common.utils.HttpsRequestUtil;
import wechat.qiye.addressbook.entity.DepartmentEntity;
import wechat.qiye.common.constant.QiYeUriEnum;
import wechat.qiye.common.entity.QiYeParamsEntity;
import wechat.qiye.common.entity.QiYeReceiveEntity;
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
        QiYeReceiveEntity qiYeReceiveEntity = gson.fromJson(result, QiYeReceiveEntity.class);
        Integer errorCode = qiYeReceiveEntity.getErrcode();
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
        QiYeReceiveEntity qiYeReceiveEntity = gson.fromJson(result, QiYeReceiveEntity.class);
        Integer errorCode = qiYeReceiveEntity.getErrcode();
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
        QiYeReceiveEntity qiYeReceiveEntity = gson.fromJson(result, DepartmentEntity.class);
        Integer errorCode = qiYeReceiveEntity.getErrcode();
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
        List<DepartmentEntity> list = getList(departmentId);
        if (list != null && list.size() > 0) {
            for (DepartmentEntity entity : list) {
                if (entity.getId().equals(departmentId)) {
                    return entity;
                }
            }
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
}
