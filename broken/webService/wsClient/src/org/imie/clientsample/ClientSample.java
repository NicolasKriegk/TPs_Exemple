package org.imie.clientsample;

import org.imie.*;

public class ClientSample {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        BasicServiceService service1 = new BasicServiceService();
	        System.out.println("Create Web Service...");
	        BasicService port1 = service1.getBasicServicePort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.executeService());
	        System.out.println("Create Web Service...");
	        BasicService port2 = service1.getBasicServicePort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port2.executeService());
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
