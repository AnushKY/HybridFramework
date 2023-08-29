package com.mindtree.utility;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;
import org.testng.Assert;
import java.io.*;

public class TestNGAssertion extends Assertion {

	public static TestNGAssertion assertion = new TestNGAssertion();
	public static WebDriver driver = null;
	private String assertMessage = null;
	private static Map<AssertionError, IAssert> assertionErrors = Maps.newHashMap();
	public static TestNGAssertion getInstance() {return assertion;}
	
	public void fail(String message) {
		assertion.assertTrue(false, message);
	}
	
	@Override
	protected void doAssert(IAssert<?> a) {
		onBeforeAssert(a);
		try {
			assertMessage = a.getMessage();
			a.doAssert();
			onAssertSuccess(a);
		}catch(AssertionError ex) {
			onAssertFailure(a,ex);
			try {
				System.out.println("Test");
			} catch(Exception e) {
				e.printStackTrace();
				
			}
		}finally {
			onAfterAssert(a);
		}
	}
	
	
	public void executeAssert(IAssert a) {
		try {
			a.doAssert();
		}catch(AssertionError e) {
			assertionErrors.put(e,a);
		}
	}
	
	public void onAssertFailure(IAssert a) {
		try {
			a.doAssert();
		}catch(AssertionError e) {
			assertionErrors.put(e,a);
		}
	}
	
	public void clearErrorLog() {
		assertionErrors.clear();
	}
	
	public  void assertAll() {
		if(!assertionErrors.isEmpty()) {
			StringBuilder stackTrace = new StringBuilder("\n ***test failed *** the folowing asserts failed : "+assertionErrors);
			boolean first = true;
			for(Map.Entry<AssertionError, IAssert> assertionError : assertionErrors.entrySet()) {
				StringWriter errors = new StringWriter();
				if(first) {first = false;}
				else {stackTrace.append("\n");}
				assertionError.getKey().printStackTrace(new PrintWriter(errors));
				stackTrace.append(assertionError.getKey().getLocalizedMessage()+"\n"+errors+"\n");
			}
		throw new AssertionError(stackTrace.toString());
		}
	}
	
}

