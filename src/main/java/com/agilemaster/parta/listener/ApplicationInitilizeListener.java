package com.agilemaster.parta.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.agilemaster.parta.bootstrap.BootstrapService;

public class ApplicationInitilizeListener implements ServletContextListener {
	private static final Logger log = LoggerFactory
			.getLogger(ApplicationInitilizeListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		String[] profiles = ctx.getEnvironment().getActiveProfiles();
		log.info("-------------------------env----------------------->"+StringUtils.toString(profiles));
		BootstrapService bootstrapService = ctx.getBean(BootstrapService.class);
		bootstrapService.init();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
