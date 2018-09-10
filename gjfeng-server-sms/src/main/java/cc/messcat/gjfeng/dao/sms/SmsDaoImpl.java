package cc.messcat.gjfeng.dao.sms;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import cc.messcat.gjfeng.dao.BaseHibernateDaoImpl;

@Repository("smaDao")
public class SmsDaoImpl extends BaseHibernateDaoImpl<Serializable> implements SmsDao {

}
