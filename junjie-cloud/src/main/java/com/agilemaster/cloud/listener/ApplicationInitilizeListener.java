package com.agilemaster.cloud.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.agilemaster.cloud.entity.Company;
import com.agilemaster.cloud.repository.CompanyRepository;

public class ApplicationInitilizeListener implements ServletContextListener {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ApplicationInitilizeListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LOGGER.debug("initializing..");
		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		CompanyRepository companyRepository = ctx.getBean(CompanyRepository.class);
		Company company = new Company();
		String key="2d18f4fa-fdcb-491c-a030-bb2f1154699c";
		if(companyRepository.findByAuthKey(key)==null){
			company.setAddress("滨州医学院烟台附属医院地址");
			company.setName("滨州医学院烟台附属医院");
			company.setAuthKey(key);
			companyRepository.save(company);
		}		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
