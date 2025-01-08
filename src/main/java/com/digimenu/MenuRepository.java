package com.digimenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

	@Query(value="select gname,mname,mprice,qtytype from menu,qtymast,foodgroup where menu.qid=qtymast.qid and menu.gid=foodgroup.gid order by gname desc",nativeQuery=true)
	public abstract List<Object[]> lstMenuCard();
	
	@Query(value="select mid,gname,mname,mprice,qtytype,menu.gid,menu.qid from menu,qtymast,foodgroup where menu.qid=qtymast.qid and menu.gid=foodgroup.gid order by gname desc",nativeQuery=true)
	public abstract List<Object[]> lstMenu();
}
