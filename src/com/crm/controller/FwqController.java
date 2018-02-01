/**
 * 
 */
package com.crm.controller;

import java.util.List;
import java.util.Timer;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.model.Customer;
import com.crm.model.Fwq;
import com.crm.model.User;
import com.crm.model.easyui.DataGrid;
import com.crm.model.easyui.Json;
import com.crm.model.easyui.PageHelper;
import com.crm.service.FwqService;
import com.crm.service.UserService;
import com.crm.task.MyTask;

@Controller
public class FwqController {
	
	private final Logger log = LoggerFactory.getLogger(FwqController.class);
	
	@Resource
	private FwqService fwqService;
	
	@RequestMapping(value = "/fwq/list",method = RequestMethod.GET)
    public String fwqList(Model model) {
        return "fwq/list";
    }
	
	
	@ResponseBody
	@RequestMapping(value="/fwq/datagrid")
	public DataGrid datagrid(PageHelper page,Fwq fwq) throws Exception{
		
		if(fwq!=null){
			if(fwq.getIpdz()!=null && fwq.getIpdz().equals("no")){
				fwq.setIpdz(null);
			}
			if(fwq.getPort()!=null && fwq.getPort()==-1){
				fwq.setPort(null);
			}
			if(fwq.getFlag()!=null && fwq.getFlag()==-1){
				fwq.setFlag(null);
			}
			
		}
		
		DataGrid dg = new DataGrid();
		dg.setTotal(fwqService.getDatagridTotal(fwq));
		List<Fwq> list = fwqService.datagridFwq(page,fwq);
		
		for(int i=0;i<list.size();i++){
			Fwq f =  list.get(i);
			Timer timer = new Timer();
			 timer.schedule(new MyTask(f), 1000, 2000);
			 Thread.sleep(2000);
				if (f.getFlag()==1) {							
					timer.cancel();

				}else{
					
					timer.cancel();
				}
				
		}
		List<Fwq> lists = fwqService.datagridFwq(page,fwq);
		dg.setRows(lists);
		return dg;
	}
	
	/**
	 * 新增服务器
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/fwq/addFwq",method = RequestMethod.POST)
    public Json addFwq(Fwq fwq) {
		Json j = new Json();
		
		try {
			fwqService.add(fwq);
            j.setSuccess(true);
            j.setMsg("用户新增成功！");
            j.setObj(fwq);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }
	
	
	/**
     * 修改服务器
     * 
     * @param fwq
     * @return
     */
	@ResponseBody
    @RequestMapping(value = "/fwq/editFwq",method = RequestMethod.POST)
    public Json editFwq(Fwq fwq) {
        Json j = new Json();
        log.debug("穿过来的用户ID为："+fwq.getId());
        try {
        	fwqService.edit(fwq);
            j.setSuccess(true);
            j.setMsg("编辑成功！");
            j.setObj(fwq);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }
	
	/**
	 * 删除某个服务器
	 * @param fwq
	 * @param out
	 */
	@ResponseBody
	@RequestMapping(value = "/fwq/deleteFwq",method = RequestMethod.POST)
	public Json deleteFwq(Fwq fwq) {
		Json j = new Json();
        log.debug("穿过来的用户ID为："+fwq.getId());
        try {
        	fwqService.deleteFwq(fwq.getId());
			j.setSuccess(true);
	        j.setMsg("删除成功！");
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
	}
}
