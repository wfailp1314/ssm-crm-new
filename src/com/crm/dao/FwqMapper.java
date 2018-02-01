package com.crm.dao;

import java.util.List;

import com.crm.model.Fwq;
import com.crm.model.easyui.PageHelper;


public interface FwqMapper {

	public Long getDatagridTotal(Fwq fwq);

	public List<Fwq> datagridFwq(PageHelper page,Fwq fwq);
	
	public int updateFwq(Fwq fwq);

	public void add(Fwq fwq);

	public void edit(Fwq fwq);

	public void deleteFwq(int id);
}
