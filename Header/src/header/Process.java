package header;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class Process implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int RUNNING		= 0;
	public static final int WAIT		= 1;
	public static final int IDLE		= 2;
	public static final int TERMINATED	= 3;
	public static final int ABORTED		= 4;
	
	private String[] args;		// calling arguments
	private long id;			// pid
	private int priority;		// priority
	private long startTime;		// process start time
	private long endTime;		// end time aft process completes
	private int status;			// status
	
	@SuppressWarnings("rawtypes")
	Class className;			// bytecode name of class
	Program classInstance;		// class instance that can be called
	
	public Process next;
	public Process previous;
	// constructor
	public Process(String[] args, int priority, long id)throws ClassNotFoundException, IOException{
		this.args = args;
		this.id = id;
		this.priority = priority;
		this.status = 2;
		this.startTime = 0;
		this.endTime = 0;
		next = null;
		previous = null;
		//className = Class.forName("programs."+getName());
	}
	public String run(PrintWriter out, File file) throws InstantiationException, IllegalAccessException, InterruptedException{	
		startTime = System.currentTimeMillis() % 100000;
		StringBuilder strBuild = new StringBuilder();
		try{
			FileWriter writer = new FileWriter(file, true);
			out = new PrintWriter(writer);
			className = Class.forName("programs." + getName());
			classInstance = (Program)className.newInstance();
			int r = classInstance.run(out, args);
			if(r > 4){
				this.status = ABORTED;
				strBuild.append(getName() + " did not run successfully \n");
			} else{
				this.status = RUNNING;
				strBuild.append(getName() + " ran successfully time = "+getRunTime()+ "\n");
			}		
		} catch(Throwable t){
			out.close();
			return "Program not found";
		}
		out.close();
		return strBuild.toString();
	}

	public void setPriority(int priority){
		this.priority = priority;
	}
	
	public long getId(){
		return id;
	}
	
	public int getPriority(){
		return priority;
	}
	
	public String getName(){
		return args[0];
	}
	
	public String[] getArgs(){
		return args;
	}
	
	public long getRunTime(){
		long time;
		if(status>2){
			time = startTime-endTime;
		}else if (status == 2 || status == 4){
			time = 0;
		} else {
			long ttime = System.currentTimeMillis() % 100000;
			time = ttime - startTime;
		}
		return time;
	}
	   public String getStatus() {
		   switch(this.status){
		   case RUNNING:
			   return "Running";
		   case WAIT:
			   return "Wait";
		   case IDLE:
			   return "Idle";
		   case TERMINATED:
			   return "Terminated";
		   case ABORTED:
			   return "Aborted";
		   default:
			   return "ERROR: No Status.";
		   }
	   }
	   
	   public void setStatus(int status){
		   this.status = status;
	   }
	   public String toString(String[] args, int priority, long id ){
			String strin = "";
			for(int i=0;i<args.length;i++){
				strin+=args[i];
				strin+=" ";
			}
			strin+=priority + " " + id + " ";
			return strin;
		}
}
