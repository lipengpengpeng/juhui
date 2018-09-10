package cc.messcat.gjfeng.common.tianmao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import cc.messcat.gjfeng.common.netFriends.HttpUtils;
import cc.messcat.gjfeng.common.tianmao.bean.CatDetail;
import net.sf.json.JSONObject;

public class JdOtherUtil {
	// 获取商品分类
			public static List<CatDetail> getTianMaoProCart() {
				Map<String, String> map = new HashMap<String, String>();
				map.put("mod", "aihua");
				map.put("act", "jdfh");
				map.put("param", "types");						
				map.put("kh", "fenghuang");

				// 请求域名
				String host = "http://aihua.likecs.com";
				// 请求后缀
				String path = "/index.php";
				// 请求类型
				String method = "GET";
				// 设置请求头
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "text/xml;charset=utf-8");
				try {
					HttpResponse response = HttpUtils.doGet(host, path, method, headers, map);

					// 获取response的body
					String str = EntityUtils.toString(response.getEntity());
					JSONObject json = JSONObject.fromObject(str);
					String dataStr=json.getString("data");
					JSONObject dataJson = JSONObject.fromObject(dataStr);
					Iterator<String> it=dataJson.keys();
					List<CatDetail> list=new ArrayList<>();
					while(it.hasNext()){
						CatDetail cat=new CatDetail();
						String catId=it.next();
						cat.setId(catId);
						String name=dataJson.getString(catId);
						cat.setCatName(name);
						list.add(cat);
					}
					return list;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			/**
			 * 获取分类列表
			 * @return
			 */
			@SuppressWarnings({ "rawtypes", "static-access" })
			public static List getProductList(String type,String page){
				Map<String, String> map = new HashMap<String, String>();
				map.put("mod", "aihua");
				map.put("act", "jdfh");
				map.put("param", "init");	
				map.put("type",type);	
				map.put("page",page);	
				map.put("kh", "fenghuang");

				// 请求域名
				String host = "http://aihua.likecs.com";
				// 请求后缀
				String path = "/index.php";
				// 请求类型
				String method = "GET";
				// 设置请求头
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "text/xml;charset=utf-8");
				try {
					HttpResponse response = HttpUtils.doGet(host, path, method, headers, map);

					// 获取response的body
					String str = EntityUtils.toString(response.getEntity());
					// 把json字符串转为json对象
					com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(str);
					String data=jsonObject.getString("data");
					List product=jsonObject.parseArray(data);							
					return product;

				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			/**
			 * 搜索获取商品列表
			 * @return
			 */
			@SuppressWarnings({ "rawtypes", "static-access" })
			public static List getSerchProductList(String keyword,String page){
				Map<String, String> map = new HashMap<String, String>();
				map.put("mod", "aihua");
				map.put("act", "jdfh");
				map.put("param", "init");	
				map.put("keyword",keyword);	
				map.put("page",page);	
				map.put("kh", "fenghuang");

				// 请求域名
				String host = "http://aihua.likecs.com";
				// 请求后缀
				String path = "/index.php";
				// 请求类型
				String method = "GET";
				// 设置请求头
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "text/xml;charset=utf-8");
				try {
					HttpResponse response = HttpUtils.doGet(host, path, method, headers, map);

					// 获取response的body
					String str = EntityUtils.toString(response.getEntity());
					// 把json字符串转为json对象
					com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(str);
					String data=jsonObject.getString("data");
					List product=jsonObject.parseArray(data);							
					return product;

				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			/**
			 * 获取商品详情
			 * @return
			 */
			@SuppressWarnings({"static-access" })
			public static CatDetail getProductDetail(String proId){
				Map<String, String> map = new HashMap<String, String>();
				map.put("mod", "aihua");
				map.put("act", "jdfh");
				map.put("param", "show");	
				map.put("id",proId);			
				map.put("kh", "fenghuang");

				// 请求域名
				String host = "http://aihua.likecs.com";
				// 请求后缀
				String path = "/index.php";
				// 请求类型
				String method = "GET";
				// 设置请求头
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "text/xml;charset=utf-8");
				try {
					HttpResponse response = HttpUtils.doGet(host, path, method, headers, map);

					// 获取response的body
					String str = EntityUtils.toString(response.getEntity());
					// 把json字符串转为json对象
					com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(str);
					String data=jsonObject.getString("data");
					CatDetail catDetail=jsonObject.parseObject(data, CatDetail.class);
								
					return catDetail;

				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}			
}
