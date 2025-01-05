package com.digimenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

	@Query(value="select gname,mname,mprice,qtytype from menu,qtymast,foodgroup where menu.qid=qtymast.qid and menu.gid=foodgroup.gid",nativeQuery=true)
	public abstract List<Object[]> lstMenuCard();
}
