package com.junjie.commons.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class PaginationHelper {

    @SuppressWarnings("unchecked")
	public static JdbcPage fetchPage(
            final NamedParameterJdbcTemplate jt,
            final String sqlCountRows,
            final String sqlFetchRows,
            final Map<String, ?> paramMap,
            long max,
            long offset) {
        // determine how many rows are available
        final long rowCount = jt.queryForObject(sqlCountRows, paramMap,Long.class);
        final long searchMax,searchOffset;
     // create the page object
        final JdbcPage page = new JdbcPage();
        if(max==0&&offset==0){
        	searchMax = rowCount;
        	searchOffset = 0;
        	  page.setPageNumber(0);
              page.setPagesAvailable(1);
        }else{
        	searchMax = max;
        	searchOffset = offset;
        	 // calculate the number of pages
            long pageCount = rowCount / max;
            if (rowCount > max * pageCount) {
                pageCount++;
            }
            page.setPageNumber(offset/max);
            page.setPagesAvailable(pageCount);
        }
       
        jt.query(
                sqlFetchRows,
                paramMap,
                new ResultSetExtractor<JdbcPage>() {
                    public JdbcPage extractData(ResultSet rs) throws SQLException, DataAccessException {
                        final List<Map<String, Object>> pageItems = page.getPageItems();
                        ResultSetMetaData rsmd = rs.getMetaData(); 
                        int currentRow = 0;
                        while (rs.next() && currentRow < searchOffset + searchMax) {
                        	 if (currentRow >= searchOffset) {
	                        	Map<String, Object> map = new HashMap<String, Object>();   
	                            int columnCount = rsmd.getColumnCount();  
	                            for(int i=0;i<columnCount;i++){  
	                                String columnName = rsmd.getColumnName(i+1);  
	                                map.put(columnName, rs.getObject(i+1));  
	                            }  
	                            pageItems.add(map);  
                        	 }
                            currentRow++;
                        }
                        return page;
                    }
                });
        return page;
    }

}
