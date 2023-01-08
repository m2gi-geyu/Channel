package edu.uga.m2gi.ar.channels;

import java.util.Hashtable;
import java.util.LinkedList;

public class TableChannel {
	public static Hashtable<Integer,String> server=new Hashtable<Integer,String>();
	public static Hashtable<String,Channel> clientChannel=new Hashtable<String,Channel>();
	public static Hashtable<Integer,LinkedList<String>> server2client=new Hashtable<Integer,LinkedList<String>>();

	
	public static void createTable(int port,String name) {
		if(!server2client.containsKey(port)) {
			server2client.put(port,new LinkedList<String>());
		}
		server.put(port, name);
	}
	
	public static Channel getClient(int port) {
		return clientChannel.get(server2client.get(port).getFirst());
	}
	
	public static Channel connect(int serverPort,String clientName) {
		if(server2client.containsKey(serverPort)) {
			server2client.get(serverPort).add(clientName);
		} else {
			server2client.put(serverPort,new LinkedList<String>());
			server2client.get(serverPort).add(clientName);
		}	
		
		if(clientChannel.containsKey(clientName)) {
			return clientChannel.get(clientName);
		} else {
			Channel c=new Channel(server.get(serverPort),serverPort,clientName);
			clientChannel.put(clientName,c);	
			return c;
		}
	}
	
	public static void disconnect(int portServer,String nameClient) {
		clientChannel.remove(nameClient);
		server2client.get(portServer).remove(nameClient);
		
	}
	
}
