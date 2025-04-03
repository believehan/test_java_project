package com.psy7758.dto;

import java.time.LocalDate;

public class ClientInfo {
	private int regNum; // 고객 등록번호. ( 등록 순서를 최근 등록순으로 시각적으로 보기 위한 번호일뿐, 고유 등록 번호가 아님에 유의 )
	private String id;
	private String pwd;
	private String name;
	private String phoneNum;
	private LocalDate birthdate;
	private int totPoint;

	public ClientInfo() {
	}

	/**
	 * @param regNum    : 고객 등록번호 - int
	 * @param id        : 고객 ID - String
	 * @param pwd       : 고객 비밀번호 - String
	 * @param name      : 고객명. - String
	 * @param phoneNum  : 고객 연락처. - String
	 * @param birthdate : 고객 생년월일 - LocalDate
	 * @param totPoint  : 고객 누적포인트. - int
	 */
	public ClientInfo(int regNum, String id, String pwd, String name, String phoneNum, LocalDate birthdate,
			int totPoint) {
		this.regNum = regNum; // 고객 등록번호 추가.
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.phoneNum = phoneNum;
		this.birthdate = birthdate;
		this.totPoint = totPoint;
	}

	public int getRegNum() {
		return regNum;
	}

	public void setRegNum(int regNum) {
		this.regNum = regNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public int getTotPoint() {
		return totPoint;
	}

	public void setTotPoint(int totPoint) {
		this.totPoint = totPoint;
	}

	@Override
	public String toString() {
		return "ClientInfo [regNum=" + regNum + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", phoneNum="
				+ phoneNum + ", birthdate=" + birthdate + ", totPoint=" + totPoint + ", getRegNum()=" + getRegNum()
				+ ", getId()=" + getId() + ", getPwd()=" + getPwd() + ", getName()=" + getName() + ", getPhoneNum()="
				+ getPhoneNum() + ", getBirthdate()=" + getBirthdate() + ", getTotPoint()=" + getTotPoint()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}