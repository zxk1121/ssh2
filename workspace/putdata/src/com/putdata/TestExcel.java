package com.putdata;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestExcel {
		  public List<String> readXlsx(String name) throws IOException{  
		    String fileName = name;
		    List phoneList=new ArrayList();
		    Map<String,Object> map=new HashMap<String, Object>();
		    HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(fileName));  
		      
		    // 循环工作表Sheet 
		   
		    for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++){  
		      HSSFSheet xssfSheet = hssfWorkbook.getSheetAt(numSheet);  
		      if(xssfSheet == null){  
		        continue;  
		      }  
		        
		      // 循环行Row   
		      for(int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++ ){  
		        HSSFRow xssfRow = xssfSheet.getRow( rowNum);  
		        if(xssfRow == null){  
		          continue;  
		        }  
		          
		        // 循环列Cell     
		        for(int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++){  
		          HSSFCell xssfCell = xssfRow.getCell( cellNum);  
		          if(xssfCell == null){  
		            continue;  
		          }  
//		          System.out.println("   "+getValue(xssfCell));
		          if(map.get(getValue(xssfCell))==null){
		        	  map.put(getValue(xssfCell), getValue(xssfCell));
		        	  phoneList.add(getValue(xssfCell));
		          }
		          
		        }  
		      }
		    }
		    	for(int i=0;i<10;i++){
		    		Collections.shuffle(phoneList);
		    	}
		    	return phoneList;
		  }  
		    
		  @SuppressWarnings("static-access")  
		  private static String getValue(HSSFCell xssfCell){  
		    if(xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN){  
		      return String.valueOf( xssfCell.getBooleanCellValue());  
		    }else if(xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC){  
		      return String.valueOf( xssfCell.getNumericCellValue());  
		    }else{  
		      return String.valueOf( xssfCell.getStringCellValue());  
		    }  
		  }  
	}


