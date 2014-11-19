package com.agilemaster.parta.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.parta.entity.BuildProject;
import com.agilemaster.parta.util.BeanToMapUtil;
import com.junjie.commons.db.client.JunjieJdbcOptions;

@Service
public class BuildProjectDaoImpl implements BuildProjectDao{
	@Autowired
	private JunjieJdbcOptions junjieJdbcOptions;
	
	@Override
	public List list() {
		return junjieJdbcOptions.queryForList("select id,name from  BUILD_PROJECT ", null);
	}

	@Override
	public BuildProject findById(Long id) {
		Map<String,Object> queryParams = new HashMap<String,Object>();
		queryParams.put("buildId", id);
		Map<String,Object> result = junjieJdbcOptions.queryForMap("select * from  BUILD_PROJECT  where id=:buildId", queryParams);
		BuildProject buildProject = BeanToMapUtil.convertMap(BuildProject.class,result);
		return buildProject;
	}

}
