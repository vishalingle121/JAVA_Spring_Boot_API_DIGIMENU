package com.digimenu;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Menu {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int mid;
	
	@NotBlank(message="mname Cannot Blank")
	private String mname;
	
	@Min(value = 1, message = "mprice Cannot be zero or negative")
	private int mprice;
	@Min(value = 1, message = "gid Cannot be zero or negative")
	private int gid;
	@Min(value = 1, message = "qid Cannot be zero or negative")
	private int qid;
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public int getMprice() {
		return mprice;
	}
	public void setMprice(int mprice) {
		this.mprice = mprice;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
}
