package com.digimenu;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Qtymast {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int qid;
	
	@NotBlank(message="qtytype Cannot Blank")
	private String qtytype;

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getQtytype() {
		return qtytype;
	}

	public void setQtytype(String qtytype) {
		this.qtytype = qtytype;
	}
}
