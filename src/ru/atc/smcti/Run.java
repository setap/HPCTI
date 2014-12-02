package ru.atc.smcti;

import java.io.UnsupportedEncodingException;

import com.google.code.jdde.client.*;

public class Run {

	
	public static void main(String[] args) throws UnsupportedEncodingException {

//		System.setProperty( "java.library.path", "   ." );
		System.setProperty( "java.library.path", "C:\\smcti\\" );
		System.out.println(System.getProperty("java.library.path"));
		
		ClientConversation conv = null;
		DdeClient client = null;
		
		try {
			client = new DdeClient();
			conv = client.connect("HP_Service_Manager", "Actions");	
		} catch(Exception e) {
			 System.out.println("Have not started DDE Server "+
					 "(restart thin client or boot fat one in memory) " + e);
			 System.exit(0);
		} 
		
		System.out.println("Call user phone is " + args[0]);

/*		String command = "[SystemEvent(\"ReceiveInteraction\","+
									"\"Callback Phone\",\"" + args[0] + "\"," +
									"\"Cause Code\",\"�������\"," +
									"\"Initial Impact\",\"4\"," +
									"\"Severity\",\"4\"," +
									"\"Priority Code\",\"4\"," +
									"\"Caller Name\",\"������������ ���\"," +
									"\"Callback Contact\",\"������������ ���\"," +
									"\"Category\",\"��������\"" +
									")]";*/
		
		StringBuilder c = new StringBuilder();
		c.append("[SystemEvent(\"ReceiveInteraction\"").append(",");
		c.append("\"Callback Phone\",\"" + args[0] + "\"").append(",");
		c.append("\"Cause Code\",\"phone\"").append(",");
		c.append("\"Initial Impact\",\"4\"" ).append(",");
		c.append("\"Severity\",\"4\"").append(",");
		c.append("\"Priority Code\",\"4\"").append(",");
		c.append("\"Caller Name\",\"Jennifer Falcon\"").append(",");
		c.append("\"Callback Contact\",\"Jennifer Falcon\"").append(",");
		c.append("\"Category\",\"��������\"");
		c.append(")]");
		
		String cm = c.toString();
		
/*        byte[] utf8Bytes = null;
        
		try {
			utf8Bytes = command.getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
        String command_in_ut8 = new String(utf8Bytes, "UTF8");*/
        
		try {
        	conv.execute(cm);
        } catch(Exception e) {
        	System.out.println(" " + e);
        } finally {
        	System.exit(0);
        }		
	}
}
