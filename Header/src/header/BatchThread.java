package header;

import java.io.File;
import java.io.PrintWriter;
import console.OSConsole;

public class BatchThread extends Thread {
	// instantiate a batch output stream
		static Thread me;
		MaxTime max;
		BatchThread b;
		private String      batchName;  // The name of this batch.
		private String      fileName;   // The file where to direct output.
		private int	        doze;      // Time limit.
		private ProcessList pL;    // The processes queued to the batch.
		Process p;
		PrintWriter out;
		StringBuilder builder;
		
		
		public BatchThread(PrintWriter out,String batchName, String fileName, ProcessList pL, int doze){
			this.batchName = batchName;
			this.fileName = fileName;
			this.doze = doze;
			this.pL = pL;
			this.out = out;
		}
		
		public void run(){
			BatchThread btThread = (BatchThread) Thread.currentThread();
			btThread.setPriority(Thread.MAX_PRIORITY); // runs at Thread.MAX_PRIORITY
			MaxTime timer = new MaxTime(batchName, btThread, 1000000);
			

			try{
				String ran = null;
				p = pL.peek();
				b = (BatchThread) Thread.currentThread();
				this.setPriority(NORM_PRIORITY);
				max = new MaxTime(p.getName(), b, doze);
				File file = new File(fileName); 
				
				while(p != null){
					  try{   
						  	
						  	p.setStatus(0);
						  	OSConsole.writeLine("Batch " + batchName + ".bat " + " Process " + p.getId() + " started" );
							ran = p.run(out, file);
							OSConsole.writeLine(ran);
							if(p.getStatus() != "ABORTED") pL.deQueue();
						
				       } catch (Throwable t){ 
				    	   OSConsole.writeLine("Error occured");  
			      }
				  
				  p = p.next;
			}
			}catch(Throwable t){OSConsole.writeLine("Error with run operation");}
			out.close();
			try{
			timer.interrupt();
			max.interrupt();
			}catch(Throwable t) {OSConsole.writeLine("Thread failed");}
		}
}
