package com.reyco.testMybatisPlus.core.domain;

import java.io.Serializable;

public class Subject implements Serializable{
	private Integer id;
	private String name;
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", remark=" + remark + "]";
	}
}
