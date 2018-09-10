package cc.messcat.gjfeng.common.wechat.popular.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;



public class WordAPI {
	
	
	
	public  boolean createWord(Map<String,Object> dataMap,String fileName,String modeName){
		/*ActionContext ctx = ActionContext.getContext();  
	    HttpServletResponse response = (HttpServletResponse) ctx.get("com.opensymphony.xwork2.dispatcher.HttpServletResponse");  
	    response.setContentType("application/msword");  
        configuration.setClassForTemplateLoading(this.getClass(), "file/");  //FTL文件所存在的位置 
        Template t=null;
        try {  
            t = configuration.getTemplate(modeName); //文件名    "wordModel.ftl"
        } catch (IOException e) {  
            e.printStackTrace();  
            return false;
        }  
        try {
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName+".doc", "UTF-8"));
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            t.process(dataMap, out);  
            out.close();  
        } catch (FileNotFoundException e1) {  
            e1.printStackTrace();  
            return false;
        }catch(Exception e){
        	e.printStackTrace();
        	return false;
        }*/
        return true;
    }  
  
    private static Map<String, Object> getData(Map<String, Object> dataMap) {  
        dataMap.put("sign", "2015-01-01");
        dataMap.put("User", "小飞");  
        dataMap.put("Title", "合同名称001");
        dataMap.put("Number", "number_001");
        dataMap.put("Class", "已开票");
        dataMap.put("Balance", "5555.0");  
        dataMap.put("Cycle", "1年");  
        dataMap.put("Responsibility", "陈永健");
        dataMap.put("People", "1");
        dataMap.put("Date", "2015-09-18 10:52:25");
        dataMap.put("State", "已审核");
        dataMap.put("remark", "1");
        return dataMap;
       /* List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();  
        for (int i = 0; i < 10; i++) {  
            
        }  */
          
          
       // dataMap.put("list", list);  
    }  
		
}
