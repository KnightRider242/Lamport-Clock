package lamportClock.server;

import lamportClock.client.Client1;
import lamportClock.rmi.*;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Random;
/**
 * @author adharsh
 *
 */
public class Server1 {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	static String name = "P1";
	static Clock clock = new Clock();
	static String address = "rmi://localhost:1900/server1";
	public static void main(String[] args){
		// TODO Auto-generated method stub
		try {
			// Object of interface implementation class
			Receive stub = new RemoteReceive();
			//rmi registry within the server with port 1900
			LocateRegistry.createRegistry(1900);
			// Bind remote object with the name
			Naming.rebind(address,stub);
			// Create a client thread and start
			Client1 client = new Client1(name,clock);
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

