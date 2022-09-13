/**
 * @Title: StartTaskRunner.java
 * @Package com.ahead.ebook.startTask
 * @Description: TODO
 * @author 王江民		@date 2022-07-08 11:28:58	@version V1.0 
 */

package com.wjm.blog.security.stak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName: StartTaskRunner
 * @Description: TODO  
 * @author 王江民		@date 2022-07-08 11:28:58	@version V1.0 
 */

@Component
public class StartTaskRunner implements CommandLineRunner{
	
	@Autowired
	H2TaskConfig h2TaskConfig;

	/**
	 * <p>Title: run 接口实现类</p>
	 * @Description: TODO 
	 * @param args
	 * @throws Exception 
	 * @author 王江民		@date 2022-07-08 11:29:15		@version V1.0 
	 * @see CommandLineRunner#run(String[])
	 */
	@Override
	public void run(String... args) throws Exception {
//		h2TaskConfig.createTaableDDL();
	}

}
