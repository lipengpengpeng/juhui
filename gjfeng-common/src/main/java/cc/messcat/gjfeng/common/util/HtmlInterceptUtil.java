package cc.messcat.gjfeng.common.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unchecked")
public class HtmlInterceptUtil {
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式  
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式  
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符  
      
    /** 
     * @param htmlStr 
     * @return 
     *  删除Html标签 
     */  
    public static String delHTMLTag(String htmlStr) {  
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
        Matcher m_script = p_script.matcher(htmlStr);  
        htmlStr = m_script.replaceAll(""); // 过滤script标签  
  
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
        Matcher m_style = p_style.matcher(htmlStr);  
        htmlStr = m_style.replaceAll(""); // 过滤style标签  
  
        /*Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
        Matcher m_html = p_html.matcher(htmlStr);  
        htmlStr = m_html.replaceAll(""); // 过滤html标签  
*/  
        /*Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
        Matcher m_space = p_space.matcher(htmlStr);  
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签  */        
        return htmlStr.trim(); // 返回文本字符串  
    }  
    
    /**  
     *   
     * 基本功能：替换标记以正常显示  
     * <p>  &lt;&gt;&quot;&amp;
     *   
     * @param input  
     * @return String  
     */  
    public static String replaceTag(String input) {   
        if ("".equals(input) || null == input) {   
            return input;   
        }   
        StringBuffer filtered = new StringBuffer(input.length());   
        char c;   
        for (int i = 0; i <= input.length() - 1; i++) {   
            c = input.charAt(i);   
            switch (c) {   
            case '<':   
                filtered.append("&lt;");   
                break;   
            case '>':   
                filtered.append("&gt;");   
                break;   
            case '"':   
                filtered.append("&quot;");   
                break;   
            case '&':   
                filtered.append("&amp;");   
                break;   
            default:   
                filtered.append(c);   
            }   
  
        }   
        return (filtered.toString());   
    }   
      
    public static String getTextFromHtml(String htmlStr){ 
//    	  htmlStr = delHTMLTag(htmlStr);  
//        htmlStr = htmlStr.replaceAll(" ", "");  
//        htmlStr = htmlStr.substring(0, htmlStr.indexOf("。")+1);  
    	htmlStr = replaceTag(htmlStr);
        return htmlStr;  
    }  
      
 /*   public static void main(String[] args) {  
        String str = "<script type='text/javascript'>var All={};All.btn=document.getElementById('btn');All.int=document.getElementById('int');window.onload=function(){All.btn.onclick=function(){var myRan=Math.round(Math.random()*20);All.int.value=myRan;}}</script>";
    	str = str + "<div style='text-align:center;'> <p>整治“四风”</p>   清弊除垢<br/><span style='font-size:14px;'> </span><span style='font-size:18px;'>公司召开党的群众路线教育实践活动动员大会</span><br/></div>";   
    	System.out.println(getTextFromHtml(str));  
    }  
*/
}
