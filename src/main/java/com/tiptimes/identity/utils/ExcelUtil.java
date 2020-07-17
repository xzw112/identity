package com.tiptimes.identity.utils;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

public class ExcelUtil {  
	/**
	 * 生产excelUtil
	 * @param sheetName	标签名称
	 * @param title		第一行名称
	 * @param values	内容
	 * @param width		列宽度
	 * @return
	 */
	public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, int[] width){
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = null;
        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
            sheet.setColumnWidth(i, width[i]);
        }
        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
	}

    /**
     * 生产excelUtil 特殊表格
     * @param sheetName	标签名称
     * @param title		第一行名称
     * @param values	内容
     * @param width		列宽度
     * @return
     */
    public static HSSFWorkbook getCustomizedHSSFWorkbook(String headTitle, String sheetName, String[] title, String[][] values, Integer[] width){
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(1);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        //创建表头
        HSSFRow headRow = sheet.createRow(0);
        CellRangeAddress firstRowRegion = new CellRangeAddress(0, 0, (short) 0, (short) 9); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
        sheet.addMergedRegion(firstRowRegion);
        headRow.createCell(0).setCellValue(headTitle);
        headRow.createCell(1);
        headRow.createCell(2);
        headRow.createCell(3);
        headRow.createCell(4);
        headRow.createCell(5);
        headRow.createCell(6);
        headRow.createCell(7);
        headRow.createCell(8);
        headRow.createCell(9);
        for (Cell cell : headRow) {
            cell.setCellStyle(style);
        }

        HSSFCell cell;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
            sheet.setColumnWidth(i, width[i]);
        }
        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 2);
            for(int j=0;j<values[i].length;j++){
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }

    /**
     * 项目统计区级单位导出
     * @param headTitle
     * @param sheetName
     * @param title
     * @param values
     * @param width
     * @return
     */
    public static HSSFWorkbook getDistrictHSSFWorkbook(String headTitle, String sheetName, String[] title, String[][] values, int[] width) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(2);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        HSSFCellStyle leftStyle = wb.createCellStyle();
        leftStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式
        leftStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        leftStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        leftStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        leftStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        //创建表头
        HSSFRow headRow = sheet.createRow(0);
        CellRangeAddress firstRowRegion = new CellRangeAddress(0, 0, (short) 0, (short) 9); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
        sheet.addMergedRegion(firstRowRegion);
        headRow.createCell(0).setCellValue(headTitle);
        headRow.createCell(1);
        headRow.createCell(2);
        headRow.createCell(3);
        headRow.createCell(4);
        headRow.createCell(5);
        headRow.createCell(6);
        headRow.createCell(7);
        headRow.createCell(8);
        headRow.createCell(9);
        for (Cell cell : headRow) {
            cell.setCellStyle(style);
        }

        //创建第二行
        HSSFRow secondRow = sheet.createRow(1);
        CellRangeAddress secondRegion = new CellRangeAddress(1, 1, (short) 0, (short) 2); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
        sheet.addMergedRegion(secondRegion);
        secondRow.createCell(0).setCellValue("单位盖章：");
        secondRow.createCell(1);
        secondRow.createCell(2);

        CellRangeAddress thirdRegion = new CellRangeAddress(1, 1, (short) 3, (short) 5); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
        sheet.addMergedRegion(thirdRegion);
        secondRow.createCell(3).setCellValue("填表人：");
        secondRow.createCell(4);
        secondRow.createCell(5);

        CellRangeAddress forthRegion = new CellRangeAddress(1, 1, (short) 6, (short) 9); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
        sheet.addMergedRegion(forthRegion);
        secondRow.createCell(6).setCellValue("联系电话：");
        secondRow.createCell(7);
        secondRow.createCell(8);
        secondRow.createCell(9);

        for (Cell cell : secondRow) {
            cell.setCellStyle(leftStyle);
        }

        Cell cell;
        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
            sheet.setColumnWidth(i, width[i]);
        }
        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 3);
            for(int j=0;j<values[i].length;j++){
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }

	public static void setResponseHeader(HttpServletResponse response, HttpServletRequest request, String fileName) {
         try {  
			String browser = "";
			// 清空response
			response.reset();
			// 设置response的Header
			browser = request.getHeader("User-Agent");
			if (-1 < browser.indexOf("MSIE 6.0") || -1 < browser.indexOf("MSIE 7.0")) {
				// IE6, IE7 浏览器
				response.addHeader("content-disposition",
				"attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
				fileName = new String(fileName.getBytes(),"ISO8859-1");
			} else if (-1 < browser.indexOf("MSIE 8.0")) {
				// IE8
				response.addHeader("content-disposition",
				"attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
				fileName = URLEncoder.encode(fileName, "UTF-8");
			} else if (-1 < browser.indexOf("MSIE 9.0")) {
				// IE9
				response.addHeader("content-disposition",
				"attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
				fileName = URLEncoder.encode(fileName, "UTF-8");
			} else if (-1 < browser.indexOf("Chrome")) {
				// 谷歌
				response.addHeader("content-disposition",
				"attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
				fileName = URLEncoder.encode(fileName, "UTF-8");
			} else if (-1 < browser.indexOf("Safari")) {
				// 苹果
				response.addHeader("content-disposition",
				"attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
				fileName = new String(fileName.getBytes(),"ISO8859-1");
			} else {
				// 火狐或者其他的浏览器
				response.addHeader("content-disposition",
				"attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
				fileName = URLEncoder.encode(fileName, "UTF-8");
			}
			response.setContentType("application/octet-stream;charset=utf-8");  
			response.setHeader("Content-Disposition", "attachment;filename="+ fileName);  
			response.addHeader("Pargam", "no-cache");  
			response.addHeader("Cache-Control", "no-cache");  
         } catch (Exception ex) {  
            ex.printStackTrace();  
         }  
    }

}
