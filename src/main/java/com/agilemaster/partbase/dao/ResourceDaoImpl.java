package com.agilemaster.partbase.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agilemaster.partbase.entity.Resource;
import com.agilemaster.partbase.service.ShareService;
import com.junjie.commons.db.client.JunjieJdbcOptions;
/**
 * 
 * @author abel.lee
 *  2014年9月23日
 */
@Repository
public class ResourceDaoImpl implements ResourceDao {
    @Autowired
    private ShareService  shareService;
    @Autowired
	private JunjieJdbcOptions junjieJdbcOptions;

	@Override
	public Resource createResource(Resource resource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource updateResource(Resource resource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteResource(Long resourceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Resource findOne(Long resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> findAll() {
		return junjieJdbcOptions.queryForList("select id,name  from SYS_RESOURCE where AVAILABLE=1", null);
	}
    
   

}
