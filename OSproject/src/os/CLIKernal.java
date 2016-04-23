package os;
import java.io.IOException;
import java.io.PrintWriter;

import console.*;
import header.Command;
import header.ProcessList;
import header.Program;
import header.Process;
public class CLIKernal implements CommandListener{
	OSConsole console;
	@SuppressWarnings("rawtypes")
	Class className;
	Program classInstance;
	Command com;
	ProcessList list;
	PrintWriter out;
	String args[];

	public static void main(String[] args) {
		new CLIKernal();
	}
	
	public CLIKernal(){
		console = new OSConsole("BTLinux : Batch Sequencer");
		console.setCommandListener(this);
		console.write("Prompt--->  ");
	}
	
	@Override
	public void commandReceived(String input){
		// copy input into command
		String command = input;
		String[] argData = command.split(" ");
		// either set up the program or assume OS command
		command = argData[0].substring(0, 1).toUpperCase() + argData[0].substring(1).toLowerCase();
		// try/catch
	       try{  
	           className = Class.forName("commands." + command);  // Call the class loader to load and compile the command
	           com = (Command) className.newInstance();
	           
	           if(argData.length > 1){ 
		   			args = new String[argData.length - 1];
		   			System.arraycopy(argData, 1, args, 0, args.length);
		   			args[0] = args[0].substring(0, 1).toUpperCase() + args[0].substring(1).toLowerCase();    
	   			}
	         
	            String call = com.execute(args, list); //find command
	   			console.writeLine(call);
	   			if(call == "New batch initialized") list = new ProcessList(); // new batch created
	   			args = null; 
	   			
	       } catch (Throwable t){ 
	    	   if(list == null) console.writeLine("No batch has been initalized" ); //no batch exists
	    	   else console.writeLine("Command not found" );// The class file did not exist
	       } //end try/catch
	       console.write("Prompt--->  ");
	 }  // End CommandReceived method

}
