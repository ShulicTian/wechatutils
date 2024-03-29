package wechat.qiye.common.ctrl;

import wechat.qiye.addressbook.ctrl.DepartmentCtrl;
import wechat.qiye.addressbook.ctrl.PersonnelCtrl;
import wechat.qiye.addressbook.entity.DepartmentEntity;
import wechat.qiye.addressbook.entity.PersonnelEntity;
import wechat.qiye.addressbook.entity.PersonnelSingleEntity;
import wechat.qiye.addressbook.entity.QrcodeEntity;
import wechat.qiye.common.aes.SHA1;
import wechat.qiye.common.entity.JsSdkConfigEntity;
import wechat.qiye.common.entity.QiYeParamsEntity;
import wechat.qiye.common.interfaces.BaseCtrlAbs;
import wechat.qiye.message.ctrl.MessageCtrl;
import wechat.qiye.message.entity.Message;
import wechat.qiye.message.entity.MessageEntity;
import wechat.qiye.message.entity.TaskCardMessageStatus;
import wechat.qiye.utils.JsApiTicketUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tianslc
 * @date 2024/3/29
 */
public class QiYeCtrl extends BaseCtrlAbs {

    private final DepartmentCtrl departmentCtrl;

    private final PersonnelCtrl personnelCtrl;

    private final MessageCtrl messageCtrl;

    public QiYeCtrl(QiYeParamsEntity qiYeParamsEntity) {
        super(qiYeParamsEntity);
        departmentCtrl = new DepartmentCtrl(qiYeParamsEntity);
        personnelCtrl = new PersonnelCtrl(qiYeParamsEntity);
        messageCtrl = new MessageCtrl(qiYeParamsEntity);
    }
    public void changeOpenGlobalAddressBookSecret (boolean flag){
        super.changeOpenGlobalAddressBookSecret(flag);
        departmentCtrl.changeOpenGlobalAddressBookSecret(flag);
        personnelCtrl.changeOpenGlobalAddressBookSecret(flag);
        messageCtrl.changeOpenGlobalAddressBookSecret(flag);
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
    @Deprecated
    public PersonnelEntity getPersonnel(String personnelId) {
        return personnelCtrl.get(personnelId);
    }

    /**
     * 获取部门下人员列表
     *
     * @param departmentId
     * @return
     */
    @Deprecated
    public List<PersonnelEntity> getPersonnelList(String departmentId) {
        return personnelCtrl.getDepartmentPersonnelList(departmentId);
    }

    /**
     * 获取部门下人员ID列表
     *
     * @return
     */
    public List<PersonnelSingleEntity> getPersonnelIdList() {
        return personnelCtrl.getPersonnelIdList();
    }

    /**
     * 判断人员是否存在
     *
     * @param userId
     * @return
     */
    public Boolean existsByUserId(String userId) {
        List<PersonnelSingleEntity> list = getPersonnelIdList();
        return list != null && list.stream().anyMatch(obj -> obj.getUserId().equals(userId));
    }

    /**
     * 获取部门下人员列表
     *
     * @param departmentId
     * @param status
     * @return
     */
    @Deprecated
    public List<PersonnelEntity> getPersonnelListByStatus(String departmentId, Integer status) {
        return personnelCtrl.getDepartmentPersonnelDescList(departmentId).stream().filter(personnel -> status.equals(personnel.getStatus())).collect(Collectors.toList());
    }

    /**
     * 获取部门下人员列表
     *
     * @param departmentId
     * @param status
     * @return
     */
    @Deprecated
    public List<PersonnelEntity> getPersonnelListByNotStatus(String departmentId, Integer status) {
        return personnelCtrl.getDepartmentPersonnelDescList(departmentId).stream().filter(personnel -> !status.equals(personnel.getStatus())).collect(Collectors.toList());
    }

    /**
     * 获取部门下人员详情列表
     *
     * @param departmentId
     * @return
     */
    @Deprecated
    public List<PersonnelEntity> getPersonnelDescList(String departmentId) {
        return personnelCtrl.getDepartmentPersonnelDescList(departmentId);
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
    @Deprecated
    public List<DepartmentEntity> getDepartmentList(String departmentId) {
        return departmentCtrl.getList(departmentId);
    }

    /**
     * 获取部分及子部门ID
     *
     * @param departmentId
     * @return
     */
    public List<DepartmentEntity> getDepartmentIdList(String departmentId) {
        return departmentCtrl.getIdList(departmentId);
    }

    /**
     * 获取单个部门
     *
     * @param departmentId
     * @return
     */
    @Deprecated
    public DepartmentEntity getDepartment(String departmentId) {
        return departmentCtrl.get(departmentId);
    }

    /**
     * 是否存在部门
     *
     * @param departmentId
     * @return
     */
    public Boolean existsByDepartmentId(String departmentId) {
        List<DepartmentEntity> list = getDepartmentIdList(departmentId);
        return list != null && list.size() > 0;
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
                List<PersonnelEntity> personnelEntities = getPersonnelDescList(department.getId());
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

                            personnel.setDepartment(departmentIds.toArray(new String[]{}));
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
                            batchDeletePersonnel(userIds.toArray(new String[]{}));
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
     * 根据人员状态彻底删除人员
     *
     * @param departmentId
     * @param status
     * @return
     */
    public boolean deletePersonnelByNotStatus(String departmentId, Integer status) {
        List<DepartmentEntity> departmentEntities = new ArrayList<>(departmentCtrl.getIdList(departmentId));
        departmentEntities.add(new DepartmentEntity(departmentId, null, null));
        logger.debug("【查询部门列表成功】");
        Set<String> userIds = new HashSet<>();
        departmentEntities.forEach(department -> {
            List<PersonnelEntity> personnelEntities = getPersonnelListByNotStatus(department.getId(), status);
            userIds.addAll(personnelEntities.stream().map(PersonnelEntity::getUserId).collect(Collectors.toList()));
        });
        logger.debug("【查询人员列表成功】");
        List<String> userIdList = new ArrayList<>(userIds);
        if (userIdList.size() > 0) {
            if (userIdList.size() > 200) {
                int startIndex = 0;
                int endIndex = 199;
                while (userIdList.size() >= endIndex) {
                    batchDeletePersonnel(userIdList.subList(startIndex, endIndex).toArray(new String[0]));
                    if (endIndex == userIdList.size()) {
                        endIndex++;
                    } else if (userIdList.size() > (endIndex + 199)) {
                        startIndex += 199;
                        endIndex += 199;
                    } else {
                        startIndex = endIndex;
                        endIndex = userIdList.size();
                    }
                }
            } else {
                batchDeletePersonnel(userIds.toArray(new String[]{}));
            }
            logger.debug("【删除未关注人员成功】");
        }
        return true;
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
     * @return
     */
    public JsSdkConfigEntity getJsSdkConfig(String url) {

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
