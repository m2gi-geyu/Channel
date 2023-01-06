import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.uga.m2gi.ar.channels.Broker;
import edu.uga.m2gi.ar.channels.Task;
import edu.uga.m2gi.ar.channels.TaskClient;
import edu.uga.m2gi.ar.channels.TaskServer;
import edu.uga.m2gi.ar.channels.Channel;
public class main {
    
	public static void main(String[] args) throws InterruptedException {
		Broker server= new Broker("server");
		Broker clientA= new Broker("Client1");
		Broker clientB= new Broker("Client2");
		Broker clientC= new Broker("Client3");
		Broker clientD= new Broker("Client4");
		Broker clientE= new Broker("Client5");
		
        ExecutorService service = Executors.newFixedThreadPool(6);

        TaskServer ts=new TaskServer(server);
    	service.submit(ts);

        TaskClient tc1=new TaskClient(clientA);
    	service.submit(tc1);

        TaskClient tc2=new TaskClient(clientB);
    	service.submit(tc2);
    	
    	TaskClient tc3=new TaskClient(clientC);
    	service.submit(tc3);

    	TaskClient tc4=new TaskClient(clientD);
    	service.submit(tc4);
    	
    	TaskClient tc5=new TaskClient(clientE);
    	service.submit(tc5);
    	ts.start();
    	tc1.start();
    	tc2.start();
    	tc3.start();
    	tc4.start();
    	tc5.start();

	}
	
	

}


