package net.saowoba.backstreettoy.test.testcase.logging;

import java.io.ByteArrayInputStream;

import junit.framework.Assert;
import net.saowoba.backstreettoy.switches.annotation.dataobject.StreamParameters;
import net.saowoba.backstreettoy.switches.annotation.dataobject.StringParameters;
import net.saowoba.backstreettoy.switches.annotation.dataobject.StreamParameters.StreamInfo;

import org.junit.Test;

public class ParameterLoggingTest {

	@Test
	public void testStringParameterToString() {
		StringParameters sp = new StringParameters();
		sp.setParameter("a", new String[]{"12345"});
		sp.setParameter("b", new String[]{"abcde"});
		
		String str = sp.toString();
		System.out.println(str);
		Assert.assertTrue(str.contains("a=[12345]"));
		Assert.assertTrue(str.contains("b=[abcde]"));
	}
	
	
	@Test
	public void testStringParameterToStringWithExclude() {
		StringParameters sp = new StringParameters();
		sp.setParameter("a", new String[]{"12345"});
		sp.setParameter("b", new String[]{"abcde"});
		
		String str = sp.toString(new String[]{"b"});
		System.out.println(str);
		Assert.assertTrue(str.contains("a=[12345]"));
		Assert.assertFalse(str.contains("b=[abcde]"));
	}
	
	@Test
	public void testStreamParameterToString() {
		StreamParameters sp = new StreamParameters();
		
		StreamInfo siA = new StreamInfo();
		siA.setName("a.xls");
		siA.setContentType("binary/excel");
		siA.setSize(2048L);
		siA.setStream(new ByteArrayInputStream(new byte[]{}));
		sp.setParameter("a", siA);
		
		StreamInfo siB = new StreamInfo();
		siB.setName("b.doc");
		siB.setContentType("binary/word");
		siB.setSize(4096L);
		siB.setStream(new ByteArrayInputStream(new byte[]{}));
		sp.setParameter("b", siB);
		
		String str = sp.toString();
		System.out.println(str);
		Assert.assertTrue(str.contains("a=contentType:binary/excel;size:2048;name:a.xls"));
		Assert.assertTrue(str.contains("b=contentType:binary/word;size:4096;name:b.doc"));
	}
	
	
	@Test
	public void testStreamParameterToStringWithExclude() {
		StreamParameters sp = new StreamParameters();
		
		StreamInfo siA = new StreamInfo();
		siA.setName("a.xls");
		siA.setContentType("binary/excel");
		siA.setSize(2048L);
		siA.setStream(new ByteArrayInputStream(new byte[]{}));
		sp.setParameter("a", siA);
		
		StreamInfo siB = new StreamInfo();
		siB.setName("b.doc");
		siB.setContentType("binary/word");
		siB.setSize(4096L);
		siB.setStream(new ByteArrayInputStream(new byte[]{}));
		sp.setParameter("b", siB);
		
		String str = sp.toString(new String[]{"b"});
		System.out.println(str);
		Assert.assertTrue(str.contains("a=contentType:binary/excel;size:2048;name:a.xls"));
		Assert.assertFalse(str.contains("b=contentType:binary/word;size:4096;name:b.doc"));
	}
}
