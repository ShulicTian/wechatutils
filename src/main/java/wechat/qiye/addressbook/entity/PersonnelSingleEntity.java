package wechat.qiye.addressbook.entity;

import com.google.gson.annotations.SerializedName;
import wechat.common.entity.ReceiveEntity;

/**
 * 成员信息实体
 *
 * @author tianslc
 */
public class PersonnelSingleEntity extends ReceiveEntity {

    /**
     * 用户ID
     */
    @SerializedName("userid")
    private String userId;

    /**
     * 所在部门编号
     */
    private String department;

    public PersonnelSingleEntity() {
    }

    public PersonnelSingleEntity(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
