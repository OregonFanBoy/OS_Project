package header;
import java.io.*;

public abstract class Program extends Thread implements Serializable {
	public final static long serialVersionUID =1;
		public final static int SUCCESS =0;
		public final static int ILLEGAL_PARAMETER = 1;
		static Thread me;
		
		public Program(String name){
			this.setPriority(NORM_PRIORITY);
			this.start();
		}
		
		
		//Abstract run mehtod
		public abstract int run(PrintWriter out, String[] args)throws InterruptedException;
		
		// Fair share scheduling and check time limit interrupts
		public void schedule() throws InterruptedException{
		
			me = Thread.currentThread();
			if(me.isInterrupted())
				throw new InterruptedException();
			yield();
		}
		
		//OS System call interface
		public String system(){
			return "This method will be completed in a later lab";
		}
		
		//Method for orderly shutdown (overrrideen in applications in a later lab)
		public void terminate(){
			
		};
}
