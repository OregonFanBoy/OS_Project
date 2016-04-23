package commands;
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
	public String execute(String args[],ProcessList list){
		Process p;
		long pid = System.currentTimeMillis() % 100000;
		int priority = Thread.NORM_PRIORITY;	
		try{
			@SuppressWarnings("rawtypes")
			 Class className = Class.forName("programs." + args[0]);
	         classInstance = (Program)className.newInstance();
			if(list != null){
	         p = new Process(args, priority, pid);
			 list.enQueue(p); // add process
			}
			else{
				return "ERROR: Batch not created!\n";
			}
		}catch(Exception e){
			return "ERROR: Batch not created!\n";
		 }
		
		return "process " + args[0] + " was added to batch"; // success
	}
}
