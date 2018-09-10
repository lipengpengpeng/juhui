package cc.modules.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * excel导出工具类
 * 
 * @author StevenWang
 * 
 */
public class ExcelExportUtil {

	/**
	 * 
	 * @param exportFileName 导出文件名称
	 * @param sheetName 工作表名称
	 * @param headLine 文件头占的行数
	 * @param headContents 文件头内容
	 * @param titles 内容标题
	 * @param datas 导出的数据内容
	 * @param clazz 导出的数据类型
	 * @param paramNames 参数名称
	 * @param footContents 底部内容
	 */
	@SuppressWarnings("unchecked")
	public static String doExport(String exportFileName,String sheetName, int headLine,
			String[] headContents, String[] titles, List datas, Class clazz,
			String[] paramNames,List<String[]> footContents) {
		int sheetIndex=0;
		int rowIndex=0;
		final int MAX_EXCELROW=65535;
		WritableWorkbook wwb;
		String exptempdir=PropertiesFileReader.get("report.exp.temp.path", "/app.properties");
		String excelpath = exptempdir + exportFileName  + ".xls";
		try {
			//final String sheetName = "report"; // 工作表名
			OutputStream out=new FileOutputStream(excelpath);
			wwb = Workbook.createWorkbook(out); // excel工作簿
			
			//绘制excel表格
			WritableSheet sheet = wwb.createSheet(sheetName, sheetIndex);
			sheetIndex++;
			Label label;
			WritableCellFormat cellformat=new WritableCellFormat(NumberFormats.TEXT);
			cellformat.setAlignment(Alignment.CENTRE);
			cellformat.setVerticalAlignment(VerticalAlignment.CENTRE);
			//绘制表头
			int timelablecount=0;
			int titleSize = titles.length;
			for(int i = 0; i<headLine; i++) {
				label=new Label(0,rowIndex,headContents[i],cellformat);
				sheet.mergeCells(0, i, titleSize-1, i);//合并第1列第n行 到第m列第n行
				sheet.addCell(label);
				sheet.setRowView(i, 600, false);
				rowIndex++;
			}
			//绘制内容标题
			sheet.setRowView(headLine, 600, false);
			for(int i= 0; i<titleSize; i++) {
				sheet.setColumnView(i, 15);//绘制单元格大小
			}
			for(int i=0;i<titles.length;i++){
				label=new Label(timelablecount+i,rowIndex,titles[i],cellformat);
				sheet.addCell(label);
			}
			rowIndex++;
			//绘制数据
			
			//实例化对象
			Object javaBean = clazz.newInstance();
			//获取改对象的方法
			Method[] methods = javaBean.getClass().getMethods();
			
			if(CollectionUtil.isListNotEmpty(datas)) {
				int line = 1;
				for(Object obj : datas){
					
					sheet.addCell(new Label(0,rowIndex,line+"",cellformat));
					int j =1;
					//遍历要输出的参数名称
					for(String param : paramNames) {
						//遍历方法名称，如果方法名称跟输出参数一致，则用反射机制获取该参数的值
						for(Method method0 : methods) {
							if(method0.getName().startsWith("get") && method0.getName().toUpperCase().contains(param)){
								Object value = method0.invoke(obj, new Object[] {});
								String strValue = ""; 
								if(null != value)
									strValue = String.valueOf(value);
								sheet.addCell(new Label(j,rowIndex,strValue,cellformat));
							}
						}
						j++;
					}
					
					sheet.setRowView(rowIndex, 300, false);
					line++;
					rowIndex++;
				}
				//输出底部内容
				if(CollectionUtil.isListNotEmpty(footContents)) {
					for(String[] fc : footContents) {
						int j =0;
						for(String content : fc) {
							sheet.addCell(new Label(j,rowIndex,content,cellformat));
							j++;
						}
						rowIndex++;
					}
				}
				if(rowIndex==MAX_EXCELROW-1){
					rowIndex=0;
					sheet = wwb.createSheet(sheetName, sheetIndex);
					sheetIndex++;
				}
			}
			wwb.write();
			wwb.close();
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return excelpath;
	}
	
	public static String doExport2(String exportFileName,String sheetName, int headLine,
		String[] headContents, String[] titles, List datas, Class clazz,
		String[] paramNames,List<String[]> footContents) {
	int sheetIndex=0;
	int rowIndex=0;
	final int MAX_EXCELROW=65535;
	WritableWorkbook wwb;
	String exptempdir=PropertiesFileReader.get("report.exp.temp.path", "/app.properties");
	String excelpath = exptempdir + exportFileName  + ".xls";
	try {
		//final String sheetName = "report"; // 工作表名
		OutputStream out=new FileOutputStream(excelpath);
		wwb = Workbook.createWorkbook(out); // excel工作簿
		
		//绘制excel表格
		WritableSheet sheet = wwb.createSheet(sheetName, sheetIndex);
		sheetIndex++;
		Label label;
		WritableCellFormat cellformat=new WritableCellFormat(NumberFormats.TEXT);
		cellformat.setAlignment(Alignment.CENTRE);
		cellformat.setVerticalAlignment(VerticalAlignment.CENTRE);
		//绘制表头
		int timelablecount=0;
		int titleSize = titles.length;
		for(int i = 0; i<headLine; i++) {
			label=new Label(0,rowIndex,headContents[i],cellformat);
			sheet.mergeCells(0, i, titleSize-1, i);//合并第1列第n行 到第m列第n行
			sheet.addCell(label);
			sheet.setRowView(i, 600, false);
			rowIndex++;
		}
		//绘制内容标题
		sheet.setRowView(headLine, 600, false);
		for(int i= 0; i<titleSize; i++) {
			sheet.setColumnView(i, 15);//绘制单元格大小
		}
		for(int i=0;i<titles.length;i++){
			label=new Label(timelablecount+i,rowIndex,titles[i],cellformat);
			sheet.addCell(label);
		}
		rowIndex++;
		//绘制数据
		
		//实例化对象
		Object javaBean = clazz.newInstance();
		//获取改对象的方法
		Method[] methods = javaBean.getClass().getMethods();
		
		if(CollectionUtil.isListNotEmpty(datas)) {
			//int line = 1;
			for(Object obj : datas){
				
				//sheet.addCell(new Label(0,rowIndex,line+"",cellformat));
				int j =0;
				//遍历要输出的参数名称
				for(String param : paramNames) {
					//遍历方法名称，如果方法名称跟输出参数一致，则用反射机制获取该参数的值
					for(Method method0 : methods) {
						if(method0.getName().startsWith("get") && method0.getName().toUpperCase().contains(param)){
							Object value = method0.invoke(obj, new Object[] {});
							String strValue = ""; 
							if(null != value)
								strValue = String.valueOf(value);
							sheet.addCell(new Label(j,rowIndex,strValue,cellformat));
							break;
						}
					}
					j++;
				}
				
				sheet.setRowView(rowIndex, 300, false);
				//line++;
				rowIndex++;
			}
			//输出底部内容
			if(CollectionUtil.isListNotEmpty(footContents)) {
				for(String[] fc : footContents) {
					int j =0;
					for(String content : fc) {
						sheet.addCell(new Label(j,rowIndex,content,cellformat));
						j++;
					}
					rowIndex++;
				}
			}
			if(rowIndex==MAX_EXCELROW-1){
				rowIndex=0;
				sheet = wwb.createSheet(sheetName, sheetIndex);
				sheetIndex++;
			}
		}
		wwb.write();
		wwb.close();
		out.flush();
		out.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return excelpath;
}
 
	@SuppressWarnings("unchecked")
	public static String doExport3(String exportFileName,String sheetName, int headLine,
			String[] headContents, String[] titles, List datas, Class clazz,
			String[] paramNames,List<String[]> footContents) {
		int sheetIndex=0;
		int rowIndex=0;
		final int MAX_EXCELROW=65535;
		WritableWorkbook wwb;
//		String exptempdir=PropertiesFileReader.get("report.exp.temp.path", "/app.properties");
		String exptempdir = ServletActionContext.getServletContext().getRealPath("/upload/enterprice/files");
		String excelpath = exptempdir +File.separator+ exportFileName  + ".xls";
		try {
			//final String sheetName = "report"; // 工作表名
			OutputStream out=new FileOutputStream(excelpath);
			wwb = Workbook.createWorkbook(out); // excel工作簿
			
			//绘制excel表格
			WritableSheet sheet = wwb.createSheet(sheetName, sheetIndex);
			sheetIndex++;
			Label label;
			WritableCellFormat cellformat=new WritableCellFormat(NumberFormats.TEXT);
			cellformat.setAlignment(Alignment.CENTRE);
			cellformat.setVerticalAlignment(VerticalAlignment.CENTRE);
			//绘制表头
			int timelablecount=0;
			int titleSize = titles.length;
//			for(int i = 0; i<headLine; i++) {
//				label=new Label(0,rowIndex,headContents[i],cellformat);
//				sheet.mergeCells(0, i, titleSize-1, i);//合并第1列第n行 到第m列第n行
//				sheet.addCell(label);
//				sheet.setRowView(i, 600, false);
//				rowIndex++;
//			}
			//绘制内容标题
			sheet.setRowView(headLine, 270, false);
			for(int i= 0; i<titleSize; i++) {
				sheet.setColumnView(i, 15);//绘制单元格大小
			}
			for(int i=0;i<titles.length;i++){
				label=new Label(timelablecount+i,rowIndex,titles[i],cellformat);
				sheet.addCell(label);
			}
			rowIndex++;
			//绘制数据
			
			//实例化对象
//			Object javaBean = clazz.newInstance();
			//获取改对象的方法
//			Method[] methods = javaBean.getClass().getMethods();
			
//			if(CollectionUtil.isListNotEmpty(datas)) {
//				int line = 1;
//				for(Object obj : datas){
//					
//					sheet.addCell(new Label(0,rowIndex,line+"",cellformat));
//					int j =1;
//					//遍历要输出的参数名称
//					for(String param : paramNames) {
//						//遍历方法名称，如果方法名称跟输出参数一致，则用反射机制获取该参数的值
//						for(Method method0 : methods) {
//							if(method0.getName().startsWith("get") && method0.getName().toUpperCase().contains(param)){
//								Object value = method0.invoke(obj, new Object[] {});
//								String strValue = ""; 
//								if(null != value)
//									strValue = String.valueOf(value);
//								sheet.addCell(new Label(j,rowIndex,strValue,cellformat));
//							}
//						}
//						j++;
//					}
//					
//					sheet.setRowView(rowIndex, 300, false);
//					line++;
//					rowIndex++;
//				}
				//输出内容
				if(CollectionUtil.isListNotEmpty(footContents)) {
					for(String[] fc : footContents) {
						int j =0;
						for(String content : fc) {
							sheet.addCell(new Label(j,rowIndex,content,cellformat));
							j++;
						}
						rowIndex++;
					}
				}
				if(rowIndex==MAX_EXCELROW-1){
					rowIndex=0;
					sheet = wwb.createSheet(sheetName, sheetIndex);
					sheetIndex++;
				}
//			}
			wwb.write();
			wwb.close();
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return excelpath;
	}	
}
