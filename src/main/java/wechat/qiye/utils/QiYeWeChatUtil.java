package wechat.qiye.utils;

import wechat.qiye.addressbook.ctrl.DepartmentCtrl;
import wechat.qiye.addressbook.ctrl.PersonnelCtrl;
import wechat.qiye.addressbook.entity.DepartmentEntity;
import wechat.qiye.addressbook.entity.PersonnelEntity;
import wechat.qiye.addressbook.entity.QrcodeEntity;
import wechat.qiye.auth.ctrl.LoginAuthCtrl;
import wechat.qiye.common.aes.SHA1;
import wechat.qiye.common.entity.JsSdkConfigEntity;
import wechat.qiye.common.entity.QiYeParamsEntity;
import wechat.qiye.message.ctrl.MessageCtrl;
import wechat.qiye.message.entity.Message;
import wechat.qiye.message.entity.MessageEntity;
import wechat.qiye.message.entity.TaskCardMessageStatus;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 企业微信操作工具
 *
 * @author tinaslc
 */
public class QiYeWeChatUtil {

    private PersonnelCtrl personnelCtrl;
    private DepartmentCtrl departmentCtrl;
    private LoginAuthCtrl loginAuthCtrl;
    private MessageCtrl messageCtrl;
    private Map<String, Object> ctrlCache = new HashMap<String, Object>();

    /**
     * 开启成员控制器
     *
     * @return
     */
    public void openPersonnelCtrl(QiYeParamsEntity qiYeParamsEntity) {
        String key = qiYeParamsEntity.getAgentId();
        if (!ctrlCache.containsKey(key)) {
            this.personnelCtrl = new PersonnelCtrl(qiYeParamsEntity);
            this.ctrlCache.put(key + "_personnelCtrl", this.personnelCtrl);
        } else {
            this.personnelCtrl = (PersonnelCtrl) ctrlCache.get(key);
        }
    }

    /**
     * 开启部门控制器
     *
     * @return
     */
    public void openDepartmentCtrl(QiYeParamsEntity qiYeParamsEntity) {
        String key = qiYeParamsEntity.getAgentId();
        if (!ctrlCache.containsKey(key)) {
            this.departmentCtrl = new DepartmentCtrl(qiYeParamsEntity);
            this.ctrlCache.put(key + "_departmentCtrl", this.departmentCtrl);
        } else {
            this.departmentCtrl = (DepartmentCtrl) ctrlCache.get(key);
        }
    }

    /**
     * 开启身份验证控制器
     *
     * @return
     */
    public void openLoginAuthCtrl(QiYeParamsEntity qiYeParamsEntity) {
        String key = qiYeParamsEntity.getAgentId();
        if (!ctrlCache.containsKey(key)) {
            this.loginAuthCtrl = new LoginAuthCtrl(qiYeParamsEntity);
            this.ctrlCache.put(key + "_loginAuthCtrl", this.loginAuthCtrl);
        } else {
            this.loginAuthCtrl = (LoginAuthCtrl) ctrlCache.get(key);
        }
    }

    /**
     * 开启消息控制器
     *
     * @return
     */
    public void openMessageCtrl(QiYeParamsEntity qiYeParamsEntity) {
        String key = qiYeParamsEntity.getAgentId();
        if (!ctrlCache.containsKey(key)) {
            this.messageCtrl = new MessageCtrl(qiYeParamsEntity);
            this.ctrlCache.put(key + "_messageCtrl", this.messageCtrl);
        } else {
            this.messageCtrl = (MessageCtrl) ctrlCache.get(key);
        }
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
     * 获取部门下人员列表
     *
     * @param departmentId
     * @param fetchChild
     * @param status
     * @return
     */
    public List<PersonnelEntity> getPersonnelListByStatus(String departmentId, String fetchChild, Integer status) {
        return personnelCtrl.getDepartmentPersonnelDescList(departmentId, fetchChild).stream().filter(personnel -> status.equals(personnel.getStatus())).collect(Collectors.toList());
    }

    /**
     * 获取部门下人员列表
     *
     * @param departmentId
     * @param fetchChild
     * @param status
     * @return
     */
    public List<PersonnelEntity> getPersonnelListByNotStatus(String departmentId, String fetchChild, Integer status) {
        return personnelCtrl.getDepartmentPersonnelDescList(departmentId, fetchChild).stream().filter(personnel -> !status.equals(personnel.getStatus())).collect(Collectors.toList());
    }

    /**
     * 获取部门下人员详情列表
     *
     * @param departmentId
     * @param fetchChild
     * @return
     */
    public List<PersonnelEntity> getPersonnelDescList(String departmentId, String fetchChild) {
        return personnelCtrl.getDepartmentPersonnelDescList(departmentId, fetchChild);
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
        if (departmentEntities != null && departmentEntities.size() > 0) {
            departmentEntities.sort(Comparator.comparing(DepartmentEntity::getOrder));
            departmentEntities.forEach(department -> {
                List<PersonnelEntity> personnelEntities = getPersonnelDescList(department.getId(), "0");
                if (personnelEntities != null && personnelEntities.size() > 0) {
                    List<String> userIds = new ArrayList<>();
                    personnelEntities.forEach(personnel -> {
                        if (personnel.getDepartment().length == 1) {
                            userIds.add(personnel.getUserId());
                        } else {
                            List<String> departmentIds = Arrays.stream(personnel.getDepartment()).collect(Collectors.toList());
                            int index = departmentIds.indexOf(department.getId());
                            departmentIds.remove(index);

                            List<Integer> orderList = new ArrayList<>(Arrays.asList(personnel.getOrder()));
                            orderList.remove(index);
                            personnel.setOrder(orderList.toArray(new Integer[]{}));

                            List<Integer> laderList = new ArrayList<>(Arrays.asList(personnel.getIsLaderInDept()));
                            laderList.remove(index);
                            personnel.setIsLaderInDept(laderList.toArray(new Integer[]{}));

                            personnel.setDepartment(departmentIds.toArray(new String[0]));
                            personnel.setMainDepartment(departmentIds.get(0));
                            updatePersonnel(personnel);
                        }
                    });
                    if (userIds.size() > 0) {
                        if (userIds.size() > 200) {
                            int startIndex = 0;
                            int endIndex = 199;
                            while (userIds.size() >= endIndex) {
                                batchDeletePersonnel(userIds.subList(startIndex, endIndex).toArray(new String[0]));
                                if (endIndex == userIds.size()) {
                                    endIndex++;
                                } else if (userIds.size() > (endIndex + 199)) {
                                    startIndex += 199;
                                    endIndex += 199;
                                } else {
                                    startIndex = endIndex;
                                    endIndex = userIds.size();
                                }
                            }
                        } else {
                            batchDeletePersonnel(userIds.toArray(new String[0]));
                        }
                    }
                }
                deleteDepartment(department.getId());
            });
            return true;
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

    /**
     * 获取jssdkconfig
     *
     * @param qiYeParamsEntity
     * @return
     */
    public JsSdkConfigEntity getJsSdkConfig(QiYeParamsEntity qiYeParamsEntity, String url) {

        JsSdkConfigEntity jsSdkConfigEntity = new JsSdkConfigEntity();
        String ticket = JsApiTicketUtil.getJsApiTicket(qiYeParamsEntity);
        String nonceStr = UUID.randomUUID().toString();
        long timestamp = System.currentTimeMillis() / 1000;
        String signature = SHA1.getSHA1("jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url);

        jsSdkConfigEntity.setAppId(qiYeParamsEntity.getCorpId());
        jsSdkConfigEntity.setTimestamp(timestamp);
        jsSdkConfigEntity.setNonceStr(nonceStr);
        jsSdkConfigEntity.setSignature(signature);

        return jsSdkConfigEntity;
    }

    /**
     * 获取企业二维码
     *
     * @param sizeType qrcode尺寸类型，1: 171 x 171; 2: 399 x 399; 3: 741 x 741; 4: 2052 x 2052
     * @return
     */
    public QrcodeEntity getQrCodeUrl(String sizeType) {
        return personnelCtrl.getQrcode(sizeType);
    }

}
