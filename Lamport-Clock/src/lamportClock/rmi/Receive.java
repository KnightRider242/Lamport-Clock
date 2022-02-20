/**
 Create a Remote Interface
 * 
 */
package lamportClock.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * @author adharsh
 *
 */
public interface Receive extends Remote {
	
	public void receive(String process,int time)throws RemoteException;
}
