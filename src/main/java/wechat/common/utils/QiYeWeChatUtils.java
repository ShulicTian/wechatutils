package wechat.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wechat.common.entity.BaseParamsEntity;
import wechat.qiye.addressbook.ctrl.DepartmentCtrl;
import wechat.qiye.addressbook.ctrl.PersonnelCtrl;
import wechat.qiye.addressbook.entity.DepartmentEntity;
import wechat.qiye.addressbook.entity.PersonnelEntity;
import wechat.qiye.auth.ctrl.LoginAuthCtrl;
import wechat.qiye.message.ctrl.MessageCtrl;
import wechat.qiye.message.entity.Message;
import wechat.qiye.message.entity.MessageEntity;
import wechat.qiye.message.entity.TaskCardMessageStatus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 企业微信控制器
 *
 * @author tinaslc
 */
public class QiYeWeChatUtils {

    private PersonnelCtrl personnelCtrl;
    private DepartmentCtrl departmentCtrl;
    private LoginAuthCtrl loginAuthCtrl;
    private MessageCtrl messageCtrl;
    private Map<String, Object> ctrlCache = new HashMap<String, Object>();
    private Logger logger = LogManager.getLogger(QiYeWeChatUtils.class);

    /**
     * 加载配置文件方式
     */
    private synchronized BaseParamsEntity loadProps(String path) {
        BaseParamsEntity baseParamsEntity = null;
        Properties props = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(new File(path));
            props.load(in);
            if (null != props) {
                baseParamsEntity = new BaseParamsEntity(props);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("读取配置文件异常", e);
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return baseParamsEntity;
    }

    /**
     * 开启成员控制器
     *
     * @return
     */
    public void openPersonnelCtrl(BaseParamsEntity baseParamsEntity) {
        String key = baseParamsEntity.getAgentId();
        if (!ctrlCache.containsKey(key)) {
            this.personnelCtrl = new PersonnelCtrl(baseParamsEntity);
            this.ctrlCache.put(key + "_personnelCtrl", this.personnelCtrl);
        } else {
            this.personnelCtrl = (PersonnelCtrl) ctrlCache.get(key);
        }
    }

    /**
     * 开启成员控制器（配置文件方式）
     *
     * @return
     */
    public void openPersonnelCtrlByPath(String path) {
        this.openPersonnelCtrl(this.loadProps(path));
    }

    /**
     * 开启部门控制器
     *
     * @return
     */
    public void openDepartmentCtrl(BaseParamsEntity baseParamsEntity) {
        String key = baseParamsEntity.getAgentId();
        if (!ctrlCache.containsKey(key)) {
            this.departmentCtrl = new DepartmentCtrl(baseParamsEntity);
            this.ctrlCache.put(key + "_departmentCtrl", this.departmentCtrl);
        } else {
            this.departmentCtrl = (DepartmentCtrl) ctrlCache.get(key);
        }
    }

    /**
     * 开启部门控制器（配置文件方式）
     *
     * @return
     */
    public void openDepartmentCtrlByPath(String path) {
        this.openDepartmentCtrl(this.loadProps(path));
    }

    /**
     * 开启身份验证控制器
     *
     * @return
     */
    public void openLoginAuthCtrl(BaseParamsEntity baseParamsEntity) {
        String key = baseParamsEntity.getAgentId();
        if (!ctrlCache.containsKey(key)) {
            this.loginAuthCtrl = new LoginAuthCtrl(baseParamsEntity);
            this.ctrlCache.put(key + "_loginAuthCtrl", this.loginAuthCtrl);
        } else {
            this.loginAuthCtrl = (LoginAuthCtrl) ctrlCache.get(key);
        }
    }

    /**
     * 开启身份认证控制器（配置文件方式）
     *
     * @return
     */
    public void openLoginAuthCtrlByPath(String path) {
        this.openLoginAuthCtrl(this.loadProps(path));
    }

    /**
     * 开启消息控制器
     *
     * @return
     */
    public void openMessageCtrl(BaseParamsEntity baseParamsEntity) {
        String key = baseParamsEntity.getAgentId();
        if (!ctrlCache.containsKey(key)) {
            this.messageCtrl = new MessageCtrl(baseParamsEntity);
            this.ctrlCache.put(key + "_messageCtrl", this.messageCtrl);
        } else {
            this.messageCtrl = (MessageCtrl) ctrlCache.get(key);
        }
    }

    /**
     * 开启消息控制器（配置文件方式）
     *
     * @return
     */
    public void openMessageCtrlByPath(String path) {
        this.openMessageCtrl(this.loadProps(path));
    }

    /**
     * 关闭单个应用控制器
     *
     * @return
     */
    public void closeCtrlByAgentId(String agentId) {
        this.ctrlCache.remove(agentId + "_personnelCtrl");
        this.ctrlCache.remove(agentId + "_departmentCtrl");
        this.ctrlCache.remove(agentId + "_loginAuthCtrl");
    }

    /**
     * 关闭所有应用控制器
     *
     * @return
     */
    public void closeAllCtrl() {
        this.ctrlCache.clear();
    }

    /**
     * 创建人员
     *
     * @param personnelEntity
     * @return
     */
    public boolean createPersonnel(PersonnelEntity personnelEntity) {
        return personnelCtrl.create(personnelEntity);
    }

    /**
     * 更新人员
     *
     * @param personnelEntity
     * @return
     */
    public boolean updatePersonnel(PersonnelEntity personnelEntity) {
        return personnelCtrl.update(personnelEntity);
    }

    /**
     * 获取人员
     *
     * @param personnelId
     * @return
     */
    public PersonnelEntity getPersonnel(String personnelId) {
        return personnelCtrl.get(personnelId);
    }

    /**
     * 获取部门下人员列表
     *
     * @param departmentId
     * @param fetchChild
     * @return
     */
    public List<PersonnelEntity> getPersonnelList(String departmentId, String fetchChild) {
        return personnelCtrl.getDepartmentPersonnelList(departmentId, fetchChild);
    }

    /**
     * 获取部门下人员详情列表
     *
     * @param departmentId
     * @param fetchChild
     * @return
     */
    public List<PersonnelEntity> getPersonnelDescList(String departmentId, String fetchChild) {
        return personnelCtrl.getDepartmentPersonneDesclList(departmentId, fetchChild);
    }

    /**
     * 删除人员
     *
     * @param personnelId
     * @return
     */
    public boolean deletePersonnel(String personnelId) {
        return personnelCtrl.delete(personnelId);
    }

    /**
     * 删除人员
     *
     * @param personnelIds
     * @return
     */
    public boolean batchDeletePersonnel(String[] personnelIds) {
        return personnelCtrl.batchDelete(personnelIds);
    }

    /**
     * 创建部门
     *
     * @param departmentEntity
     * @return
     */
    public boolean createDepartment(DepartmentEntity departmentEntity) {
        return departmentCtrl.create(departmentEntity);
    }

    /**
     * 更新部门
     *
     * @param departmentEntity
     * @return
     */
    public boolean updateDepartment(DepartmentEntity departmentEntity) {
        return departmentCtrl.update(departmentEntity);
    }

    /**
     * 获取部门列表
     *
     * @param departmentId
     * @return
     */
    public List<DepartmentEntity> getDepartmentList(String departmentId) {
        return departmentCtrl.getList(departmentId);
    }

    /**
     * 获取单个部门
     *
     * @param departmentId
     * @return
     */
    public DepartmentEntity getDepartment(String departmentId) {
        return departmentCtrl.get(departmentId);
    }

    /**
     * 删除部门
     *
     * @param departmentId
     * @return
     */
    public boolean deleteDepartment(String departmentId) {
        return departmentCtrl.delete(departmentId);
    }

    /**
     * 强行删除部门（删除子部门及移除人员）
     *
     * @param departmentId
     * @return
     */
    public boolean forceDeleteDepartment(String departmentId) {
        List<DepartmentEntity> departmentEntities = getDepartmentList(departmentId);
        List<String> departments;
        List<String> ids = null;
        List<PersonnelEntity> personnelEntities = null;
        String id = "";
        int ind = 0;
        for (int i = departmentEntities.size() - 1; i >= 0; i--) {
            id = departmentEntities.get(i).getId();
            personnelEntities = getPersonnelDescList(id, "0");
            ids = new ArrayList<String>();
            for (PersonnelEntity personnelEntity : personnelEntities) {
                departments = new ArrayList<String>(Arrays.asList(personnelEntity.getDepartment()));
                if (departments.size() > 1) {
                    ind = departments.indexOf(id);
                    departments.remove(ind);
                    personnelEntity.getOrder()[ind] = null;
                    personnelEntity.getIsLaderInDept()[ind] = null;
                    personnelEntity.setDepartment(departments.toArray(new String[departments.size()]));
                    updatePersonnel(personnelEntity);
                } else {
                    ids.add(personnelEntity.getUserId());
                }
            }
            batchDeletePersonnel(ids.toArray(new String[ids.size()]));
            deleteDepartment(departmentEntities.get(i).getId());
        }

        return false;
    }

    /**
     * 发送消息
     *
     * @param messageEntity
     * @return
     */
    public boolean sendMessage(MessageEntity<? extends Message> messageEntity) {
        return messageCtrl.send(messageEntity);
    }

    /**
     * 更新任务卡片消息的状态
     *
     * @param taskCardMessageStatus
     * @return
     */
    public boolean updateMessageStatus(TaskCardMessageStatus taskCardMessageStatus) {
        return messageCtrl.updateMessageStatus(taskCardMessageStatus);
    }

}
