package edu.uga.m2gi.ar.channels;

import java.io.IOException;
import java.util.Hashtable;

public class Channel {
	
	CircularBuffer readerBuffer= new CircularBuffer(64);
	CircularBuffer writerBuffer= new CircularBuffer(64);
	
	Boolean isConnected;
	String server;
	String client;
	int portServer;
	int portClient;
	
	public Channel(String server,int portServer) {
		this.server=server;
		this.portServer=portServer;
		//Address a=new Address(portA,A);
		//tasks=new Hashtable<Address,Broker>();
		//tasks.put(a, b);
	}
	
	public Channel(String client,String server,int portServer) {
		this.server=server;
		this.portServer=portServer;
		this.client=client;
		//tasks=new Hashtable<Address,Broker>();
		//tasks.put(a, b);
	}
	

	
	public synchronized int read(byte[] bytes, int offset, int length) {
		if (bytes == null) {
            throw new NullPointerException();
        } else if (offset < 0 || length < 0 || length > bytes.length - offset) {
            throw new IndexOutOfBoundsException();
        } else if (length == 0) {
            return 0;
        }
		int i=0;
		for(;i<length;i++) {
			if(readerBuffer.empty()) {
				break;
			}
			byte elem=readerBuffer.pull();
			bytes[offset+i]=elem;
		}
        return i;
	}
	public synchronized int write(byte[] bytes, int offset, int length) {
		if (bytes== null)
			 throw new NullPointerException(); 
		if ((offset | length | (bytes.length - (length + offset)) | (offset + length)) < 0)
	            throw new IndexOutOfBoundsException();
		if (length == 0)
	            return 0;    
		int i=0;
        for (; i < length ; i++) {
        	if(writerBuffer.full()) {
        		break;
        	}
            writerBuffer.push(bytes[offset+i]);
        }
        return i;
	
	}
	public void disconnect() {
	
		isConnected=false;
	}
	public boolean disconnected() {
		return isConnected;
	}
}
