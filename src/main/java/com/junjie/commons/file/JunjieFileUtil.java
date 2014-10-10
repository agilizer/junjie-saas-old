package com.junjie.commons.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.junjie.commons.file.JunjieFile.JunjieFileType;

public class JunjieFileUtil {
	private static final Logger log = LoggerFactory
			.getLogger(JunjieFile.class);
	public static JunjieFileType genFileType(String fileName ){
        int dotPos = fileName.lastIndexOf(".");
        JunjieFileType  result =JunjieFileType.OTHERS;;
        if(dotPos==-1){
        	log.warn("fileName is wrong:"+fileName);
        }else{
        	 String extension = fileName.substring(dotPos+1).toLowerCase();
             
             if(extension=="xlsx"||extension=="xls"){
                 result =JunjieFileType.EXCEL;
             }else if(extension=="doc" || extension=="docx"){
                 result = JunjieFileType.WORD;
             }else if(extension=="pdf"){
                 result = JunjieFileType.PDF;
             } else if("jpg,jpeg,gif,bmp,png".indexOf(extension)!=-1){
                 result = JunjieFileType.PHOTO;
             }
        }
        return result;
    }
	public static boolean checkFileType(String fileName,String typeStr){
        boolean result = false;
        if(fileName!=null&&typeStr!=null){
            String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
            result =  typeStr.indexOf(suffix.toLowerCase())==-1?false:true;
        }
        return result;
    }
}
