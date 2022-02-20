package lamportClock.client;

import java.rmi.Naming;
import java.util.Random;

import lamportClock.rmi.Clock;
import lamportClock.rmi.Receive;

public class Client1 extends Thread{
	String name;
	Clock clock;
	String[] address = new String[3];
	
	public Client1(String name,Clock clock)
	{
		this.name = name;
		this.clock = clock;
		this.address[0] = "rmi://localhost:1901/server2";
		this.address[1] = "rmi://localhost:1902/server3";
		this.address[2] = "rmi://localhost:1903/server4";
	}
	public void internal()

	{
		clock.time +=1;
	}
	public void send()
	{
		clock.time +=1;
		Random random  = new Random();
		int n = random.nextInt(3);
		try {
			Receive recv = (Receive)Naming.lookup(address[n]);
			recv.receive(this.name, this.clock.time);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	}
	public void run()
	{
		for(int i = 0;i<15;i++)
		{
			Random rand = new Random();
			int n = rand.nextInt(2);
			if(n == 0) {
				internal();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(n == 1) {
				send();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
