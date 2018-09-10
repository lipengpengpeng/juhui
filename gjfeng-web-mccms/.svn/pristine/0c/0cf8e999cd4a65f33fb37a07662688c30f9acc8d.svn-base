package cc.modules.util;

import java.util.Map;

public class SQLUtil {

	private final static String INSERT = "insert into";

	private final static String DELETE = "delete from";

	private final static String UPDATE = "update";

	private final static String SELECT = "select";
	
	/**
	 * insert into `authorities`(`ID`,`NAME`,`DISPLAY_NAME`,`AUTHORITIES_TYPE`,`
	 * AUTHORITIES_ID`) values (1,'EP_BASE','基本配置','0','0')
	 * 
	 * @param table
	 * @return
	 * @author Panda
	 */
	public static String getInsertSQL(String table, String columns) {
		StringBuffer sql = new StringBuffer();
		sql.append(INSERT);
		sql.append(" ");
		sql.append(table);
		sql.append("(" + columns + ")");
		sql.append(" values");
		sql.append("(" + getPropertyParamWithColon(getPropertyNames(columns)) + ")");
		return sql.toString();
	}

	/**
	 * delete from user where 1=1 and ID = :id
	 * @param table
	 * @return
	 * @author Panda
	 */
	public static String getDeleteSQL(String table,java.util.Map<String, Object> attrs){
		StringBuffer sql = new StringBuffer();
		sql.append(DELETE);
		sql.append(" ");
		sql.append(table);
		sql.append(" where 1=1");
		for(String key :attrs.keySet()){
			sql.append(" and ");
			sql.append(getColumnName(key));
			sql.append("=:");
			sql.append(key);
		}
		return sql.toString();
	}
	
	/**
	 * update user set name='panda'  where id='100001';
	 * @param table
	 * @return
	 * @author Panda
	 */
	public static String getUpdateSQL(String table,java.util.Map<String, Object> params,java.util.Map<String, Object> attrs){
		StringBuffer sql = new StringBuffer();
		sql.append(UPDATE);
		sql.append(" ");
		sql.append(table);
		sql.append(" set ");
		if(null!=params&&!params.isEmpty()){
			for(String key :params.keySet()){
				sql.append(getColumnName(key));
				sql.append("=:");
				sql.append(key);
				sql.append(",");
			}
			sql.deleteCharAt(sql.length()-1);
		}
		sql.append(" where 1=1");
		for(String key :attrs.keySet()){
			sql.append(" and ");
			sql.append(getColumnName(key));
			sql.append("=:");
			sql.append(key);
		}
		return sql.toString();
	}
	
	/**
	 * select * from table where xxxxx order by xx asc/desc
	 * 
	 * @param table
	 * @param columns
	 * @param attrs
	 * @param sort
	 * @return
	 * @author Panda
	 */
	public static String getSelectSQL(String table,String columns,Map<String, Object> attrs,String sort){
		StringBuffer sql = new StringBuffer();
		sql.append(SELECT);
		sql.append(" ");
		if(null==columns){
			sql.append("*");
		}else{
			sql.append(columns);
		}
		sql.append(" from ");
		sql.append(table);
		sql.append(" where 1=1");
		if(null!=attrs&&!attrs.isEmpty())
			for(String key :attrs.keySet()){
				if(null==attrs.get(key))
					continue;
				if("null".equals(attrs.get(key)))
					continue;
				sql.append(" and ");
				sql.append(getColumnName(key));
				sql.append("=:");
				sql.append(key);
			}
		if(null != sort){
			sql.append(" ");
			sql.append(sort);
		}
		return sql.toString();
	}
	
	public static String getWithoutId(String columns) {
		return columns.substring(3);
	}

	public static String getPropertyNames(String columns) {
		char[] arr = columns.toCharArray();
		StringBuffer attrs = new StringBuffer(columns.length());
		boolean isConvert = true;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == ',') {
				attrs.append(arr[i]);
				continue;
			}
			if (arr[i] == '_') {
				isConvert = false;
				continue;
			}
			if (isConvert) {
				arr[i] += 32;
			}
			isConvert = true;
			attrs.append(arr[i]);
		}
		return attrs.toString();
	}

	public static String getPropertyParamWithColon(String arrs) {
		arrs = arrs.replace(",", ",:");
		arrs = ":" + arrs;
		return arrs;
	}

	/**
	 * 将类名转成数据库表名
	 * 例如
	 * EveryhourOrderForm -> everyhour_order_form
	 * @param className
	 * @return
	 * @author Panda ~_@
	 * @Date 2016年1月7日
	 */
	public static String getTableName(String className) {
		int index = className.lastIndexOf(".");
		className = (index > 0) ? className.substring(index) + 1 : className;
		StringBuffer tableName = new StringBuffer();
		char[] charArray = className.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if ('A' <= charArray[i] && 'Z' >= charArray[i]) {
				if (i != 0) {
					tableName.append("_");
				}
				charArray[i] += 32;
			}
			tableName.append(charArray[i]);
		}
		return tableName.toString().toLowerCase();
	}
	
	public static String getTableName(String className,String suffix) {
		if (null == suffix) {
			return getTableName(className);
		} else {
			return getTableName(className) + "_" + suffix.toLowerCase();
		}
	}
	
	public static String getColumnName(String className){
		return getTableName(className).toUpperCase();
	}
	
	/**
	 * 获取查询表名的sql语句
	 * @param likeStr
	 * @return
	 */
	public static String getSelectTableNamesSQL(String likeStr, String withoutStr){
		return new StringBuilder().append("SELECT table_name FROM information_schema.tables WHERE table_schema='galaxy_pcserver_cs' AND table_type='base table' AND table_name LIKE '").append(likeStr).append("' AND table_name NOT LIKE '").append(withoutStr).append("'").toString();
	}
	
	
	public static void main(String[] args) {
		String FORM_COLUMN_NAMES = "CLIENT_TYPE_,UPDATE_TIME_,UPDATE_BY_,REBATE_,TOTAL_BONUS_,DOMAIN_";
		getPropertyParamWithColon(getPropertyNames(FORM_COLUMN_NAMES));
	}
}
