package commands;
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
	public String execute(String[] args, ProcessList list) {
		
		Process p = list.peek();
		File file = new File(args[0] + ".bat"); 
		String str = null;
		StringBuilder strBuild = new StringBuilder();
		try {
			out = new PrintWriter(file);
			out.close();
		} catch (FileNotFoundException e) {
			return "error occured";
		}
		while(p != null){
			  try{     
					str = p.run(out, file);
					strBuild.append(str);
					list.deQueue();
		       } catch (Throwable t){ 
		    	   return "Program error";
		       }
			  p = p.next;
		}
		return strBuild.toString();
	}
}
