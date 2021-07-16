package com.atguigu.pojo;

import java.io.Serializable;

/**
 * 自由行(单项旅行)
 * Entity Class 实体类，与表进行映射
 * ORM   Object(类) - Ralationship(表)  Mapping(映射)
 * 属性与字段映射
 * 属性类型与字段类型进行映射
 * 映射尽量表名，字段名称保持一致
 *
 * 实体类对象都是用于封装表的数据，需要实现可序列化接口。经常需要将Bean对象转换为json格式
 */
public class TravelItem implements Serializable {
    private Integer id;//主键
    private String code;//自由行项目编号
    private String name;//项目名称
    private String sex;//适用性别
    private String age;//适用年龄（范围），例如：20-50
    private Float price;//价格
    private String type;//参团类型
    private String remark;//项目说明
    private String attention;//注意事项

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
