package header;

import console.OSConsole;

public class MaxTime extends Thread implements Runnable{
	BatchThread bThread;
	OSConsole console;
	int doze;
	String batchName;
	public MaxTime(String batchName, BatchThread bThread, int doze){
		this.bThread = bThread;
		this.doze = doze;
		this.batchName = batchName;
		if(doze==0){
			bThread.setPriority(MIN_PRIORITY);
		} else if(doze <0) {
			BatchThread.yield();
		}else {
			
			this.start();
			
		}
	}

	@Override
	public void run() {
		try {
			sleep(doze); // sleeps for batch time run limit (if non-zero)
			OSConsole.writeLine(batchName + " failed: check the maximum time limit");
			bThread.interrupt();
		} catch (InterruptedException e) {
			//just exits	
		}
		
		
	}
}
