package edu.uga.m2gi.ar.channels;

public class TaskClient extends Task{
	private Channel channel;
	

	public TaskClient(Broker broker) {
		super(broker);	
	}

	@Override
	public void run() {
        System.out.println(this.currentThread().getName());
        try {
			Channel c=m_broker.connect("server", 8888);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String out="connexion request "+m_broker.name();
		int checkWrite=channel.write(out.getBytes(),0,out.length());
		byte[] in =new byte[1024];
		int inLen =channel.read(in,0,1024);
		int checkReader=channel.read(in,0,out.length());
		System.out.print(checkWrite+"|"+checkReader);
		if(in!=null) {
			System.out.print("read:"+in.toString());		
		}
	}	
}
