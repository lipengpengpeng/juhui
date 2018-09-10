package cc.messcat.datasource;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class DataSourceExchange implements MethodBeforeAdvice,AfterReturningAdvice{

	@Override    
    public void afterReturning(Object returnValue, Method method,    
            Object[] args, Object target) throws Throwable {    
        //DataSourceHolder.clearDataSource();    
    }    
    
    @Override    
    public void before(Method method, Object[] args, Object target)    
            throws Throwable {    
        //这里DataSource是自定义的注解，不是java里的DataSource接口  
    	String name=target.getClass().getName();
    	DataSourceHolder.clearDataSource();
    	if (method.isAnnotationPresent(DataSource.class)){
            DataSource datasource = method.getAnnotation(DataSource.class);    
            DataSourceHolder.setDataSource(datasource.name());    
        }else if ("cc.messcat.service.sign".equals(name.substring(0, name.lastIndexOf(".")))) {
    		DataSourceHolder.setDataSource(DataSource.slave1); 
		}else{    
        	DataSourceHolder.setDataSource(DataSource.master);
        }
    	
    }    

}
