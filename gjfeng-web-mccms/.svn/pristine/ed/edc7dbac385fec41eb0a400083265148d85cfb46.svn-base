package cc.messcat.datasource;

import cc.modules.util.StringUtil;

/**
 * 数据源操作
 * @author Karhs
 *
 */
public class DataSourceHolder {

	//线程本地环境
    private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();
    
    //设置数据源
    public static void setDataSource(String dataSourceType) {
        dataSources.set(dataSourceType);
    }
    //获取数据源
    public static String getDataSource() {
    	if (StringUtil.isBlank(dataSources.get())) {
    		setDataSource(DataSource.master);
		}
        return dataSources.get();
    }
    //清除数据源
    public static void clearDataSource() {
        dataSources.remove();
    }
}
