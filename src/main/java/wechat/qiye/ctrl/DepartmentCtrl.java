package wechat.qiye.ctrl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import wechat.common.constant.BaseConstant;
import wechat.common.entity.BaseCtrlAbs;
import wechat.common.entity.BaseReceiveEntity;
import wechat.common.interfaces.BaseCtrl;
import wechat.common.utils.GsonUtils;
import wechat.common.utils.HttpsRequestUtils;
import wechat.qiye.entity.BaseParamsEntity;
import wechat.qiye.entity.DepartmentEntity;
import wechat.qiye.utils.AccessTokenUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 部门控制器
 *
 * @author tianslc
 */
public class DepartmentCtrl extends BaseCtrlAbs implements BaseCtrl<DepartmentEntity> {

    public DepartmentCtrl(BaseParamsEntity baseParamsEntity) {
        super(baseParamsEntity);
    }

    /**
     * 创建部门
     *
     * @param departmentEntity
     */
    public boolean create(DepartmentEntity departmentEntity) {
        String url = BaseConstant.QIYE_CU_DEPARTMENT.replace("METHOD", BaseConstant.CREATE).replace("ACCESS_TOKEN", AccessTokenUtils.getAccessToken(baseParamsEntity));
        String result = HttpsRequestUtils.httpsPost(url, gson.toJson(departmentEntity).getBytes());
        BaseReceiveEntity baseReceiveEntity = gson.fromJson(result, BaseReceiveEntity.class);
        Integer errorCode = baseReceiveEntity.getErrcode();
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
        String url = BaseConstant.QIYE_CU_DEPARTMENT.replace("METHOD", BaseConstant.UPDATE).replace("ACCESS_TOKEN", AccessTokenUtils.getAccessToken(baseParamsEntity));
        String result = HttpsRequestUtils.httpsPost(url, gson.toJson(departmentEntity).getBytes());
        BaseReceiveEntity baseReceiveEntity = gson.fromJson(result, BaseReceiveEntity.class);
        Integer errorCode = baseReceiveEntity.getErrcode();
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
        String url = BaseConstant.QIYE_RD_DEPARTMENT.
                replace("METHOD", BaseConstant.DELETE).
                replace("ACCESS_TOKEN", AccessTokenUtils.getAccessToken(baseParamsEntity)).
                replace("ID", departmentId);
        String result = HttpsRequestUtils.httpsGet(url);
        BaseReceiveEntity baseReceiveEntity = gson.fromJson(result, DepartmentEntity.class);
        Integer errorCode = baseReceiveEntity.getErrcode();
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
        if (list.size() > 0) {
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
        String url = BaseConstant.QIYE_RD_DEPARTMENT.
                replace("METHOD", BaseConstant.LIST).
                replace("ACCESS_TOKEN", AccessTokenUtils.getAccessToken(baseParamsEntity)).
                replace("ID", departmentId);
        String result = HttpsRequestUtils.httpsGet(url);
        JsonObject jsonObject = GsonUtils.parseJsonObject(result);
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
