package commands;
import header.BatchList;
import header.Command;
import header.Process;
import header.ProcessList;
import header.Program;

//import java.io.IOException;


public class Add extends Command{
	Program classInstance;
	
	public Add(){}
	//@SuppressWarnings("static-access")
	
	@Override
	public String execute(String args[],ProcessList list, BatchList batch){
		Process p;
		long pid = System.currentTimeMillis() % 100000;
		int priority = Thread.NORM_PRIORITY;	
		try{
			@SuppressWarnings("rawtypes")
			 Class className = Class.forName("programs." + args[0]);
	         classInstance = (Program)className.newInstance();
	         p = new Process(args, priority, pid);
	         list.enQueue(p);
	         
		}catch(Exception e){
			if(list == null) return "Batch was not initalized";
			else return "Class not found";
			
		 }
		
		return "process " + args[0] + " was added to batch"; // success
	}
}
