package com.agilemaster.partbase.service;

import java.util.Calendar;
import java.util.Map;

import javax.print.CancelablePrintJob;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.partbase.StaticMethod;
import com.agilemaster.partbase.dao.DocumentDirDao;
import com.agilemaster.partbase.entity.BuildProject;
import com.agilemaster.partbase.entity.DocumentDir;
import com.junjie.commons.utils.JunjieStaticMethod;

@Service
public class DocumentDirServiceImpl implements DocumentDirService{
	private static final Logger log = LoggerFactory
			.getLogger(DocumentDirServiceImpl.class);
	@Autowired
	UserService userService;
	@Autowired
	DocumentDirDao documentDirdao;
	@Override
	public DocumentDir create(DocumentDir documentDir,
			HttpServletRequest request) {
		Long parentId = JunjieStaticMethod.genLongValue(request, "parentId");
		if(null!=parentId){
	    	DocumentDir parentDocDir= new DocumentDir();
	    	parentDocDir.setId(parentId);
	    	documentDir.setParentDir(parentDocDir);
		}
		documentDir.setAuthor(userService.currentUser());
		Calendar calendar = Calendar.getInstance();
		documentDir.setDateCreated(calendar);
		documentDir.setLastUpdated(calendar);
		BuildProject buildProject = new BuildProject();
		Long buildProjectId = JunjieStaticMethod.genLongValue(request, "buildProject.id");
		if(null!=buildProjectId){
			buildProject.setId(buildProjectId);
			documentDir.setBuildProject(buildProject);
		}
		documentDir = documentDirdao.save(documentDir);
		return documentDir;
	}

}
