package com.crm.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.crm.dao.FwqMapper;
import com.crm.model.Fwq;
import com.crm.model.easyui.PageHelper;


@Service
public class FwqService {

	@Resource
	private FwqMapper fwqMapper;
	
	
	
	/**
	 * 获取总数
	 * @param fwq
	 * @return
	 */
	public Long getDatagridTotal(Fwq fwq) {
		return fwqMapper.getDatagridTotal(fwq);  
	}


	/**
	 * 获列表 分页
	 * @param page
	 * @return
	 */
	public List<Fwq> datagridFwq(PageHelper page,Fwq fwq) {
		page.setStart((page.getPage()-1)*page.getRows());
		page.setEnd(page.getPage()*page.getRows());
		return fwqMapper.datagridFwq(page,fwq);  
	}


	public int updateFwq(Fwq fwq) {
		return fwqMapper.updateFwq(fwq);
	}


	public void add(Fwq fwq) {
		fwqMapper.add(fwq);
	}


	public void edit(Fwq fwq) {
		fwqMapper.edit(fwq);
	}


	public void deleteFwq(int id) {
		fwqMapper.deleteFwq(id);
	}
}
