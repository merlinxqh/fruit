package com.fruit.pms.dao.model;

import com.fruit.base.framework.entity.BaseEntity;

/**
 * 
 * @author leo
 * @date 2017-02-27 20:42:45
 * @version 1.0.0
 * @copyright www.leo.com
 */
public class Resource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * code.
     */
    private String code;

    /**
     * 拼接上级long_code+自身code.
     */
    private String longCode;

    /**
     * 名称.
     */
    private String name;

    /**
     * 访问路径.
     */
    private String url;

    /**
     * 上级code.
     */
    private String parentCode;

    /**
     * 类型(0:非菜单,1:菜单).
     */
    private Integer type;

    /**
     * 样式.
     */
    private String iconStyle;

    /**
     * 排序字段.
     */
    private Integer sorting;

    /**
     * 描述.
     */
    private String description;

    /**
     * 
     * {@linkplain #code}
     *
     * @return the value of resource.code
     */
    public String getCode() {
        return code;
    }

    /**
     * {@linkplain #code}
     * @param code the value for resource.code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 
     * {@linkplain #longCode}
     *
     * @return the value of resource.long_code
     */
    public String getLongCode() {
        return longCode;
    }

    /**
     * {@linkplain #longCode}
     * @param longCode the value for resource.long_code
     */
    public void setLongCode(String longCode) {
        this.longCode = longCode == null ? null : longCode.trim();
    }

    /**
     * 
     * {@linkplain #name}
     *
     * @return the value of resource.name
     */
    public String getName() {
        return name;
    }

    /**
     * {@linkplain #name}
     * @param name the value for resource.name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 
     * {@linkplain #url}
     *
     * @return the value of resource.url
     */
    public String getUrl() {
        return url;
    }

    /**
     * {@linkplain #url}
     * @param url the value for resource.url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 
     * {@linkplain #parentCode}
     *
     * @return the value of resource.parent_code
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * {@linkplain #parentCode}
     * @param parentCode the value for resource.parent_code
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    /**
     * 
     * {@linkplain #type}
     *
     * @return the value of resource.type
     */
    public Integer getType() {
        return type;
    }

    /**
     * {@linkplain #type}
     * @param type the value for resource.type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 
     * {@linkplain #iconStyle}
     *
     * @return the value of resource.icon_style
     */
    public String getIconStyle() {
        return iconStyle;
    }

    /**
     * {@linkplain #iconStyle}
     * @param iconStyle the value for resource.icon_style
     */
    public void setIconStyle(String iconStyle) {
        this.iconStyle = iconStyle == null ? null : iconStyle.trim();
    }

    /**
     * 
     * {@linkplain #sorting}
     *
     * @return the value of resource.sorting
     */
    public Integer getSorting() {
        return sorting;
    }

    /**
     * {@linkplain #sorting}
     * @param sorting the value for resource.sorting
     */
    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    /**
     * 
     * {@linkplain #description}
     *
     * @return the value of resource.description
     */
    public String getDescription() {
        return description;
    }

    /**
     * {@linkplain #description}
     * @param description the value for resource.description
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}