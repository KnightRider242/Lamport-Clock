/**
Implement a Remote Interface
 * 
 */
package lamportClock.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 * @author adharsh
 *
 */
@SuppressWarnings("serial")
public class RemoteReceive extends UnicastRemoteObject implements Receive{

	public RemoteReceive() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void receive(String process, int time) throws RemoteException {
		
		// TODO Auto-generated method stub
		Clock clock = new Clock();
		if (clock.time > time)
		{
			clock.time  += 1;
		}
		else
		{
			time = clock.time + 1;
		}
		
	}


}
