package com.agilemaster.partbase.service;
import java.util.List;
import java.util.Set;

import com.agilemaster.partbase.entity.Resource;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface ResourceService {


    public Resource createResource(Resource resource);
    public Resource updateResource(Resource resource);
    public void deleteResource(Long resourceId);

    Resource findOne(Long resourceId);
    List<?> findAll();

}
