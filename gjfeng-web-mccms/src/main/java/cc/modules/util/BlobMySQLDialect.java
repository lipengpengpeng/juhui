package cc.modules.util;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;

public class BlobMySQLDialect extends MySQLDialect {
	public BlobMySQLDialect() {
		super();
		
		registerHibernateType(Types.DECIMAL, Hibernate.BIG_DECIMAL.getName());  
		  
        registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());  
  
        registerFunction("GROUP_CONCAT", new StandardSQLFunction(  
                "GROUP_CONCAT", Hibernate.STRING));  
	}
}
