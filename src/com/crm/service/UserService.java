/**
 * 
 */
package com.crm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.crm.dao.UserMapper;
import com.crm.model.SysMenu;
import com.crm.model.User;
import com.crm.model.easyui.PageHelper;

@Service
public class UserService {

	@Resource
	private UserMapper userMapper;
	/**
	 * @param username
	 * @return
	 */
	public User findUserByName(String username) {
		return userMapper.findUserByName(username);
	}
	
	//将查询到的数据缓存到myCache中,并使用方法名称加上参数中的userNo作为缓存的key  
    //通常更新操作只需刷新缓存中的某个值,所以为了准确的清除特定的缓存,故定义了这个唯一的key,从而不会影响其它缓存值  
    @Cacheable(value="myCache", key="#id")  
    public String getUsernameById(int id){  
        System.out.println("数据库中查到此用户号[" + id + "]对应的用户名为[" + userMapper.getUsernameById(id) + "]");  
        return userMapper.getUsernameById(id);  
    }

	/**
	 * 获取该用户权限的菜单
	 * @param userId
	 * @return
	 */
	public List<SysMenu> getMenu(int userId) {
		return userMapper.getMenuByUserId(userId);  
	}

	/**
	 * 获取用户总数
	 * @param user
	 * @return
	 */
	public Long getDatagridTotal(User user,Integer sysid) {
		return userMapper.getDatagridTotal(user,sysid);  
	}

	/**
	 * 获取用户列表
	 * @param page
	 * @return
	 */
	public List<User> datagridUser(PageHelper page,Integer sysid) {
		page.setStart((page.getPage()-1)*page.getRows());
		page.setEnd(page.getPage()*page.getRows());
		return userMapper.datagridUser(page,sysid);  
	}

	/**
	 * 新增用户
	 * @param user
	 */
	public void add(User user) {
		userMapper.addUser(user);  
	}

	/**
	 * 编辑用户
	 * @param user
	 */
	public void edit(User user) {
		userMapper.editUser(user);  
	}  
    
	/**
	 * 删除用户
	 * @param id
	 */
	public void deleteUser(int id){
		userMapper.deleteUser(id);
	}
    
    
}
