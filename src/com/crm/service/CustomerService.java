/**
 * 
 */
package com.crm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.crm.dao.CustomerMapper;
import com.crm.model.Contact;
import com.crm.model.Customer;
import com.crm.model.Visitlog;
import com.crm.model.easyui.PageHelper;

@Service
public class CustomerService {

	@Resource
	private CustomerMapper customerMapper;

	public Customer findByName(String name) {
		return customerMapper.findByName(name);
	}
	
    public Customer getUsernameById(int id){  
        return customerMapper.getCustomerById(id);  
    }

	/**
	 * 获取该客户的所有联系人
	 * @return
	 */
	public List<Contact> getContactById(int customerId) {
		return customerMapper.getContactById(customerId);  
	}
	
	/**
	 * 获取该客户的所有拜访记录
	 * @return
	 */
	public List<Visitlog> getVisitlogById(int customerId) {
		return customerMapper.getVisitlogById(customerId);  
	}

	/**
	 * 获取总数
	 * @param user
	 * @return
	 */
	public Long getDatagridTotal(Customer customer) {
		return customerMapper.getDatagridTotal(customer);
	}

	/**
	 * 获列表 分页
	 * @param page
	 * @return
	 */
	public List<Customer> datagridCustomer(PageHelper page) {
		page.setStart((page.getPage()-1)*page.getRows());
		page.setEnd(page.getPage()*page.getRows());
		return customerMapper.datagridCustomer(page);  
	}

	//添加
	public void addCustomer(Customer customer){
		customerMapper.addCustomer(customer);
	};

	//修改
	public void editCustomer(Customer customer){
		customerMapper.editCustomer(customer);
	};
	
	//删除
	public void deleteCustomer(int id){
		customerMapper.deleteCustomer(id);
	};
    
}
