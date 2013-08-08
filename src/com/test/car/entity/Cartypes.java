package com.test.car.entity;

/**
 * @功能说明： 车辆类型
 */
public class Cartypes {
	private byte value;
	private String name;

	public Cartypes(){
		
	}
	public Cartypes(byte value,String name){
		this.value=value;
		this.name = name;
	}
	public byte getValue() {
		return value;
	}

	public void setValue(byte value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @方法描述:
	 * @class:Pairs.java
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
}
