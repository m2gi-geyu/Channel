package edu.uga.m2gi.ar.channels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskServer extends Task{
	private Channel channel;
    private static List<Channel> channelList = Collections.synchronizedList(new ArrayList<>());


	public TaskServer(Broker broker) {
		super(broker);
	}

	@Override
	public void run() {
        System.out.println(this.currentThread().getName());
		while (true){
	        Channel c=m_broker.accept(8888);
	        channelList.add(c);
            byte[] bytes = new byte[1024];
            int inlen = channel.read(bytes,0,20);
            String s = new String(bytes, 0, inlen);
            System.out.println(s + this.currentThread().getName());
            for(Channel ch:channelList) {
            	 ch.write(bytes,0,20);
            	}
            }
           
	}
		
	
}
