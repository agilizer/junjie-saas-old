package com.agilemaster.partbase.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.partbase.entity.DocumentDir;
import com.agilemaster.partbase.service.ShareService;
import com.junjie.commons.db.client.JunjieJdbcOptions;
import com.junjie.commons.utils.JunjieCounter;

@Service
public class DocumentDirDaoImpl implements DocumentDirDao{
	@Autowired
	private ShareService shareService;
	@Autowired
	private JunjieJdbcOptions junjieJdbcOptions;
	@Autowired
	JunjieCounter junjieCounter;
	@Override
	public DocumentDir save(DocumentDir documentDir) {
		Long id = shareService.saveAutoGenId(documentDir);
		documentDir.setId(id);
		return documentDir;
	}

}
