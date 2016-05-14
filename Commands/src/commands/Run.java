package commands;
import header.BatchList;
import header.BatchThread;
import header.Command;
import header.ProcessList;
import header.Program;
import header.Process;

import java.io.*;
public class Run extends Command{
	PrintWriter out;
	@SuppressWarnings("rawtypes")
	Class className;
	Program classInstance;
	//execute method
	@Override
	public String execute(String[] args, ProcessList list, BatchList batch) {
		
		try{
			if(list == null) return "No batch has been initalized"; //check if list initialized
			File file = new File(args[0] + ".bat"); 
			out = new PrintWriter(file); // Overwrite previous file
			out.close();
			int i = Integer.parseInt(args[1]);
			BatchThread thread = new BatchThread(out,args[0], file.toString(), list, i);
			thread.start();
	
		}catch(Throwable t){t.printStackTrace();}
		return list.getName() + " is running";
	}
}
