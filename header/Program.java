package header;
import java.io*;

public abstract class Program implements Serializable {
	public final static long serialVersionUID =1;
		public final static int SUCCESS =0;
		public final static int ILLEGAL_PARAMETER = 1;
		private String name;
		
		public Program(String name){
			this.name = name;
		}
		
		//Retrun process name
		public String getName(){
			return name;
		}
		
		//Abstract run mehtod
		public abstract int run(PrintWriter out, Sting[] args);
		
		// Fair share scheduling and check time limit interrupts
		public void schedule() throws InterruptedException{
			throw new InterruptedException("This method will be completed in a later lab");
		}
		
		//OS System call interface
		public String system(){
			return "This method will be completed in a later lab";
		}
		
		//Method for orderly shutdown (overrrideen in applications in a later lab)
		public void terminate(){
			
		};
}
