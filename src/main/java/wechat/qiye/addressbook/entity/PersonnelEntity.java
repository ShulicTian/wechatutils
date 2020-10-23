package wechat.qiye.addressbook.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import wechat.qiye.common.entity.QiYeReceiveEntity;

/**
 * 成员信息实体
 *
 * @author tianslc
 */
public class PersonnelEntity extends QiYeReceiveEntity {

    /**
     * 用户ID
     */
    @SerializedName("userid")
    private String userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 别名
     */
    private String alias;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 所在部门编号
     */
    private String[] department;

    /**
     * 部门内排序
     */
    private Integer[] order;

    /**
     * 职务信息
     */
    private String position;

    /**
     * 性别
     */
    private String gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 所在的部门内是否为上级
     */
    @SerializedName("is_leader_in_dept")
    private Integer[] isLaderInDept;

    /**
     * 启用/禁用成员
     */
    private Integer enable;

    /**
     * 成员头像的mediaid
     */
    @SerializedName("avatar_mediaid")
    private String avatarMediaid;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 地址
     */
    private String address;

    /**
     * 主部门
     */
    @SerializedName("main_department")
    private String mainDepartment;

    /**
     * 自定义字段
     */
    private ExtattrEntity extattr;

    /**
     * 是否邀请该成员使用企业微信
     */
    @SerializedName("to_invite")
    private Boolean toInvite = false;

    /**
     * 成员对外职务
     */
    @SerializedName("external_position")
    private String externalPosition;

    /**
     * 成员对外属性
     */
    @SerializedName("external_profile")
    private ExternalProfileEntity externalProfile;

    /**
     * 头像url
     */
    @Expose(serialize = false)
    private String avatar;

    /**
     * 头像缩略图url
     */
    @Expose(serialize = false)
    @SerializedName("thumb_avatar")
    private String thumbAvatar;

    /**
     * 全局唯一。对于同一个服务商，不同应用获取到企业内同一个成员的open_userid是相同的，最多64个字节。仅第三方应用可获取
     */
    @Expose(serialize = false)
    @SerializedName("open_userid")
    private String openUserid;

    /**
     * 激活状态: 1=已激活，2=已禁用，4=未激活，5=退出企业
     */
    @Expose(serialize = false)
    private Integer status;

    /**
     * 成员个人二维码
     */
    @Expose(serialize = false)
    @SerializedName("qr_code")
    private String qrCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String[] getDepartment() {
        return department;
    }

    public void setDepartment(String[] department) {
        this.department = department;
    }

    public Integer[] getOrder() {
        return order;
    }

    public void setOrder(Integer[] order) {
        this.order = order;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer[] getIsLaderInDept() {
        return isLaderInDept;
    }

    public void setIsLaderInDept(Integer[] isLaderInDept) {
        this.isLaderInDept = isLaderInDept;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getAvatarMediaid() {
        return avatarMediaid;
    }

    public void setAvatarMediaid(String avatarMediaid) {
        this.avatarMediaid = avatarMediaid;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMainDepartment() {
        return mainDepartment;
    }

    public void setMainDepartment(String mainDepartment) {
        this.mainDepartment = mainDepartment;
    }

    public ExtattrEntity getExtattr() {
        return extattr;
    }

    public void setExtattr(ExtattrEntity extattr) {
        this.extattr = extattr;
    }

    public Boolean getToInvite() {
        return toInvite;
    }

    public void setToInvite(Boolean toInvite) {
        this.toInvite = toInvite;
    }

    public String getExternalPosition() {
        return externalPosition;
    }

    public void setExternalPosition(String externalPosition) {
        this.externalPosition = externalPosition;
    }

    public ExternalProfileEntity getExternalProfile() {
        return externalProfile;
    }

    public void setExternalProfile(ExternalProfileEntity externalProfile) {
        this.externalProfile = externalProfile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getThumbAvatar() {
        return thumbAvatar;
    }

    public void setThumbAvatar(String thumbAvatar) {
        this.thumbAvatar = thumbAvatar;
    }

    public String getOpenUserid() {
        return openUserid;
    }

    public void setOpenUserid(String openUserid) {
        this.openUserid = openUserid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
