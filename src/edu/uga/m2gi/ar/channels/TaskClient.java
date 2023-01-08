package edu.uga.m2gi.ar.channels;

public class TaskClient extends Task{
	private Channel server;
	

	public TaskClient(Broker broker) {
		super(broker);	
	}

	@Override
	public void run() {
        System.out.println(this.currentThread().getName());
        try {
			Channel server=m_broker.connect(super.getName(), 8888);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int i=0;
        while(i<255) { 
			String out="connexion request "+m_broker.name();
			int checkWrite=server.write(out.getBytes(),0,out.length());
			byte[] in =new byte[1024];
			int inLen =server.read(in,0,1024);
			int checkReader=server.read(in,0,out.length());
			System.out.print(checkWrite+"|"+checkReader);
			if(in!=null) {
				System.out.print("read:"+in.toString());		
			}
        }
        server.disconnect();
	}	
}
