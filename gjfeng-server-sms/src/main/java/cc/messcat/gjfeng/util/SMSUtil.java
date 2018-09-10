package cc.messcat.gjfeng.util;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;

import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import cc.messcat.gjfeng.common.constant.CommonProperties;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SMSUtil{
    public static boolean sendMessage(String mobile, Map<String, String> content, String templateCode) {
        if (mobile.length() == 0 || mobile == null) {
            return false;
        }
        try {
            //设置超时时间-可自行调整
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            //初始化ascClient需要的几个参数
            final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
            final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
            //替换成你的AK
            final String accessKeyId = CommonProperties.MNS_ACCESSKEYID;
            final String accessKeySecret = CommonProperties.MNS_ACCESSKEYSECRET;
         
            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
            request.setPhoneNumbers(mobile);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("凤凰云商");
            //request.setSignName("天天易购");
            //request.setSignName("优佳兑商城");
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            request.setTemplateParam(JSONObject.fromObject(content).toString());
            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                return true;
            }
        
        
        } catch (Exception e) {
            throw new RuntimeException("短信发送失败");
        }

        return false;
    }
    
    /*public static void main(String[] args) {
    	
    	Map<String,String> contents = new HashMap<String,String>();
        contents.put( "variable0", "李冠华" );
        contents.put( "variable1", "100" );
    	boolean result = sendMessage("15626421877",contents, CommonProperties.MNS_TEMPLATE_DRAWCASH_SUCC);
    	System.out.println(JSONObject.fromObject(contents).toString());
    	System.out.println(result);
    }*/
}