package com.util;

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

public class ReadAddExcel {
	  public Map<String,List<String>> readXlsx() throws IOException{  
	    String fileName = "//root//Desktop//xls文件//地址2.xls";
	    Map<String,List<String>> map=new HashMap<String,List<String>>();
	    HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(fileName));  
	     int j=0;
	     int count=0;
	    // 循环工作表Sheet 
	    for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++){  
	      HSSFSheet xssfSheet = hssfWorkbook.getSheetAt(numSheet);
	      List<String> list=new ArrayList<String>();
	      if(xssfSheet == null){  
	        continue;  
	      }
//	      System.out.println(xssfSheet.getSheetName()+":"+xssfSheet.getRow(j).getLastCellNum());
//	      j++;
//	      count=count+xssfSheet.getLastRowNum();
//	      System.out.println(count);
	      // 循环行Row   
	      for(int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++ ){
	        HSSFRow xssfRow = xssfSheet.getRow( rowNum);  
	        if(xssfRow == null){  
	          continue;  
	        }  
	      
	        // 循环列Cell     
	        for(int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++){  
	          HSSFCell xssfCell = xssfRow.getCell(cellNum);  
	          if(xssfCell == null){  
	            continue;  
	          }  
//	          System.out.println("   "+getValue(xssfCell));
	          
	          list.add(getValue(xssfCell));
	        }  
	      }
	      for(int i=0;i<1000;i++){
	    		Collections.shuffle(list);
	      }
	      map.put(""+numSheet, list);
	    }
	    	
	    return map;
	  }
	  
	  public static List<String> listreadXlsx(String name) throws IOException{  
		    String fileName = name;
		    List<String> list=new ArrayList<String>();
		    HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(fileName));  
		    // 循环工作表Sheet 
		    for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++){  
		      HSSFSheet xssfSheet = hssfWorkbook.getSheetAt(numSheet);
		      if(xssfSheet == null){  
		        continue;  
		      }
//		      System.out.println(xssfSheet.getSheetName()+":"+xssfSheet.getRow(j).getLastCellNum());
//		      j++;
//		      count=count+xssfSheet.getLastRowNum();
//		      System.out.println(count);
		      // 循环行Row   
		      for(int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++ ){
		        HSSFRow xssfRow = xssfSheet.getRow( rowNum);  
		        if(xssfRow == null){  
		          continue;  
		        }  
		      
		        // 循环列Cell     
		        for(int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++){  
		          HSSFCell xssfCell = xssfRow.getCell(cellNum);  
		          if(xssfCell == null){  
		            continue;  
		          }  
//		          System.out.println("   "+getValue(xssfCell));
		          
		          list.add(getValue(xssfCell));
		        }  
		      }
		      for(int i=0;i<1000;i++){
		    		Collections.shuffle(list);
		      }
		    }
		    	
		    return list;
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


