
public class Process {
	public static final int RUNNING    = 0;
	   public static final int WAIT       = 1;
	   public static final int IDLE       = 2;
	   public static final int TERMINATED = 3;
	   public static final int ABORTED    = 4;

	   private String[] args;       // The calling arguments.
	   private long id;             // Process Id number (pid).
	   private int  priority;       // Priority.
	   private long startTime;      // Process start time.
	   Private long endTime;        // End time after process completes.
	   private int  status;         // Process status.

	   Class   className;           // Bytecode name of class.
	   Program classInstance;       // Class instance that can be called.

	   public Process next;         // Links for next and previous list entries.
	   public Process previous;

	   // Constructors and methods.
	   public Process(String[] args, int priority, long id)  
	                               throws ClassNotFoundException, IOException
	   public String run(PrintWriter out) 
	                                throws InstantiationException, IllegalAccessException
	   public void setPriority(int priority)
	   public long getId(){
		   return id;
	   }
	   public int getPriority(){
		   return priority;
	   }
	   public String getName(){
		   return className;
	   }
	   public String[] getArgs(){
		   return args;
	   }
	   public long getRunTime(){
		   return endTime;
	   }
	   public String getStatus() {
		   return status;
	   }
	   public String toString()
}
