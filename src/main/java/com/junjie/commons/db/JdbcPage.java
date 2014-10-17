package com.junjie.commons.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 分页信息bean，页面标识从0开始。
 * @author abel.lee
 *
 */
@SuppressWarnings("rawtypes")
public class JdbcPage  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7882299080501080103L;
	private long pageNumber;
    private long pagesAvailable;
    
	private List<Map<String, Object>> pageItems = new ArrayList<Map<String, Object>>();

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPagesAvailable(long pagesAvailable) {
        this.pagesAvailable = pagesAvailable;
    }

    public void setPageItems(List pageItems) {
        this.pageItems = pageItems;
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public long getPagesAvailable() {
        return pagesAvailable;
    }

    public List getPageItems() {
        return pageItems;
    }
}