/**
* OWASP Benchmark Project v1.2beta
*
* This file is part of the Open Web Application Security Project (OWASP)
* Benchmark Project. For details, please see
* <a href="https://www.owasp.org/index.php/Benchmark">https://www.owasp.org/index.php/Benchmark</a>.
*
* The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
* of the GNU General Public License as published by the Free Software Foundation, version 2.
*
* The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
* even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* @author Dave Wichers <a href="https://www.aspectsecurity.com">Aspect Security</a>
* @created 2015
*/

package org.owasp.benchmark.testcode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BenchmarkTest01070")
public class BenchmarkTest01070 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	
		String param = request.getHeader("vector");
		if (param == null) param = "";

		String bar = new Test().doSomething(param);
		
	    try {
		    java.util.Random numGen = java.security.SecureRandom.getInstance("SHA1PRNG");
		
		    // Get 40 random bytes
		    byte[] randomBytes = new byte[40];
		    getNextNumber(numGen, randomBytes);
		    
	        String rememberMeKey = org.owasp.esapi.ESAPI.encoder().encodeForBase64(randomBytes, true);
	
			String user = "SafeBystander";
			String fullClassName = this.getClass().getName();
			String testCaseNumber = fullClassName.substring(fullClassName.lastIndexOf('.')+1+"BenchmarkTest".length());
			user+= testCaseNumber;
			
			String cookieName = "rememberMe" + testCaseNumber;
			
			boolean foundUser = false;
			javax.servlet.http.Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; !foundUser && i < cookies.length; i++) {
					javax.servlet.http.Cookie cookie = cookies[i];
					if (cookieName.equals(cookie.getName())) {
						if (cookie.getValue().equals(request.getSession().getAttribute(cookieName))) {
							foundUser = true;
						}
					}
				}
			}

			
			if (foundUser) {
				response.getWriter().println("Welcome back: " + user + "<br/>");			
			} else {			
				javax.servlet.http.Cookie rememberMe = new javax.servlet.http.Cookie(cookieName, rememberMeKey);
				rememberMe.setSecure(true);
				rememberMe.setPath("/benchmark/" + this.getClass().getSimpleName());
				request.getSession().setAttribute(cookieName, rememberMeKey);
				response.addCookie(rememberMe);
				response.getWriter().println(user + " has been remembered with cookie: " + rememberMe.getName() 
						+ " whose value is: " + rememberMe.getValue() + "<br/>");
			}
				    
	    } catch (java.security.NoSuchAlgorithmException e) {
			System.out.println("Problem executing SecureRandom.nextBytes() - TestCase");
			throw new ServletException(e);
	    } finally {
			response.getWriter().println("Randomness Test java.security.SecureRandom.nextBytes(byte[]) executed");	    
	    }
	}
	    	
	void getNextNumber(java.util.Random generator, byte[] barray) {
		generator.nextBytes(barray);
	}  // end doPost

    private class Test {

        public String doSomething(String param) throws ServletException, IOException {

		// Chain a bunch of propagators in sequence
		String a46282 = param; //assign
		StringBuilder b46282 = new StringBuilder(a46282);  // stick in stringbuilder
		b46282.append(" SafeStuff"); // append some safe content
		b46282.replace(b46282.length()-"Chars".length(),b46282.length(),"Chars"); //replace some of the end content
		java.util.HashMap<String,Object> map46282 = new java.util.HashMap<String,Object>();
		map46282.put("key46282", b46282.toString()); // put in a collection
		String c46282 = (String)map46282.get("key46282"); // get it back out
		String d46282 = c46282.substring(0,c46282.length()-1); // extract most of it
		String e46282 = new String( new sun.misc.BASE64Decoder().decodeBuffer( 
		    new sun.misc.BASE64Encoder().encode( d46282.getBytes() ) )); // B64 encode and decode it
		String f46282 = e46282.split(" ")[0]; // split it on a space
		org.owasp.benchmark.helpers.ThingInterface thing = org.owasp.benchmark.helpers.ThingFactory.createThing();
		String bar = thing.doSomething(f46282); // reflection

            return bar;
        }
    } // end innerclass Test

} // end DataflowThruInnerClass
