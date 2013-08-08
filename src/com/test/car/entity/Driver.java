package com.test.car.entity;

public class Driver {
	/*
	 * 信息序号 int 2位日期 + 4位当天累加的序号 如：160001，170200 姓名 string 性别 byte 0：男 1：女 电话
	 * string 手机号码 积分 int 星级 int 所在公司 string
	 */
	private boolean isWork;// 是否在线
	private String name;// 司机姓名
	private String telphone;// 司机联系方式
//	private String level;// 星级（调度给的）
	private int driversex;// 司机性别
	private String suozaigongsi;// 所在公司
	private String serviceNo;//司机服务号
	
	private String carNo;//司机车牌号
	

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public int getDriversex() {
		return driversex;
	}

	public void setDriversex(int driversex) {
		this.driversex = driversex;
	}

	public String getSuozaigongsi() {
		return suozaigongsi;
	}

	public void setSuozaigongsi(String suozaigongsi) {
		this.suozaigongsi = suozaigongsi;
	}

	public boolean isWork() {
		return isWork;
	}

	public void setWork(boolean isWork) {
		this.isWork = isWork;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
//
//	public String getLevel() {
//		return level;
//	}
//
//	public void setLevel(String level) {
//		this.level = level;
//	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

}
