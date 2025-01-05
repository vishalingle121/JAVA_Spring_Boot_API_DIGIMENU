package com.digimenu;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Login {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int lid;
	
	@NotBlank(message="uname Cannot Blank")
	private String uname;
	@NotBlank(message="pwd Cannot Blank")
	private String pwd;
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}
