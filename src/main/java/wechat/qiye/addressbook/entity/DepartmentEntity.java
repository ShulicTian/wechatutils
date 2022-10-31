package wechat.qiye.addressbook.entity;

import com.google.gson.annotations.SerializedName;
import wechat.common.entity.ReceiveEntity;

/**
 * 部门实体
 *
 * @author tianslc
 */
public class DepartmentEntity extends ReceiveEntity {
    /**
     * 部门ID
     */
    private String id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门英文名称
     */
    @SerializedName("name_en")
    private String nameEn;

    /**
     * 部门父级ID
     */
    @SerializedName("parentid")
    private String parentId;

    /**
     * 部门排序
     */
    private String order;

    public DepartmentEntity() {
    }

    public DepartmentEntity(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
