package com.crm.task;

import java.io.IOException;
import java.net.Socket;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.crm.model.Fwq;
import com.crm.service.FwqService;
import com.crm.util.common.SpringContextUtil;

/**
 * 判断服务器连接
 * @author lipei
   2018年1月22日
 *
 */
public class MyTask extends TimerTask {
	
	
      private Fwq fwq;
      
      private int code;
	
  	

	public MyTask(Fwq fwq) {
		  
		this.fwq = fwq;
	}

		@Override
		public void run() {
			
			 FwqService fwqService = (FwqService) SpringContextUtil.getBean("fwqService");

			try {

				Socket s = new Socket(fwq.getIpdz(),fwq.getPort());

				fwq.setFlag(1);
				
				
				code = fwqService.updateFwq(fwq);

				System.err.println("Connected to " + s.getInetAddress()
						+ " on port " + s.getPort() + " from port "
						+ s.getLocalPort() + " of " + s.getLocalAddress());

			} catch (IOException e) {
				fwq.setFlag(0);
				System.out.println(fwqService);
				code = fwqService.updateFwq(fwq);
				System.err.println(e.getMessage());
				
}

}

}
