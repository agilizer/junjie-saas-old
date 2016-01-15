package com.agilemaster.partbase.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agilemaster.partbase.entity.DocumentDir;
import com.agilemaster.partbase.service.DocumentDirService;
import com.junjie.commons.utils.JunjieConstants;
import com.junjie.commons.utils.JunjieStaticMethod;

@RestController("")
public class DocumentDirController {
	@Autowired
	DocumentDirService documentDirService;
	@RequiresPermissions("documentDir:create")
	@RequestMapping("/api/v1/documentDir/create")
	public Map<String,Object> create(String name,String description,HttpServletRequest request) {
		DocumentDir documentDir = new DocumentDir();
	    documentDir.setName(name);
	    documentDir.setDescription(description);
	    documentDir = documentDirService.create(documentDir, request);
		Map<String,Object> map = JunjieStaticMethod.genResult();
		map.put(JunjieConstants.SUCCESS, true);
		map.put(JunjieConstants.DATA, documentDir.getId());
		return map;
	}
}
