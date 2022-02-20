package lamportClock.server;

import lamportClock.client.Client4;
import lamportClock.rmi.*;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Random;
/**
 * @author adharsh
 *
 */
public class Server4 {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	static String name = "P4";
	static Clock clock = new Clock();
	static String address = "rmi://localhost:1903/server4";
	public static void main(String[] args){
		// TODO Auto-generated method stub
		try {
			// Object of interface implementation class
			Receive stub = new RemoteReceive();
			//rmi registry within the server with port 1903
			LocateRegistry.createRegistry(1903);
			// Bind remote object with the name
			Naming.rebind(address,stub);
			// Create a client thread and start
			Client4 client = new Client4(name,clock);
			client.start();
			for(int i = 0;i<15;i++)
			 {
			 	// generate 0 or 1 randomly, if 0 call internal() else send()
				Random rand = new Random();
				int n = rand.nextInt(2);
				if( n == 0)
				{
					client.internal();
				}
				if(n == 1)
				{
					client.send();
				}
			}
			  
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}

}

