package os;
import java.io.PrintWriter;

import console.CommandListener;
import console.OSConsole;
import header.BatchList;
import header.Command;
import header.ProcessList;
import header.Program;

public class CLIKernal implements CommandListener{
	OSConsole console;
	@SuppressWarnings("rawtypes")
	Class className;
	Program classInstance;
	Command com;
	ProcessList list;
	PrintWriter out;
	String args[];
	String name;
	BatchList batch;
	
	public static void main(String[] args) {
		new CLIKernal();
	}
	
	public CLIKernal(){
		console = new OSConsole("The Frank and Neal Show");
		console.setCommandListener(this);
		batch = new BatchList();
		OSConsole.write("Prompt--->  ");
	}
	
	@Override
	public void commandReceived(String input){
		// copy input into command
		String command = input;
		String[] argData = command.split(" ");
		// either set up the program or assume OS command
		
		// try/catch
	       try{  
	    	   command = argData[0].substring(0, 1).toUpperCase() + argData[0].substring(1).toLowerCase();
	           className = Class.forName("commands." + command);  // Call the class loader to load and compile the command
	           com = (Command) className.newInstance();
	           
	           if(argData.length > 1){ 
		   			args = new String[argData.length - 1];
		   			System.arraycopy(argData, 1, args, 0, args.length);
		   			args[0] = args[0].substring(0, 1).toUpperCase() + args[0].substring(1).toLowerCase();
		   			name = args[0] + " initialized";
	   			}
	         
	            String call = com.execute(args, list, batch); //find command
	   			//console.writeLine(call);
	   			if(call.equals(name)) {
	   				list = new ProcessList(args[0]); // new batch created
	   				batch.enQueue(list);
	   				OSConsole.writeLine(call);
	   			}
	   			else if(argData.length > 1 && call.equals(args[0])){
	   				list = batch.find(call);
	   				OSConsole.writeLine(call + " is now active");
	   			}
	   			else if(argData.length > 1 && call.equals(args[0]+ " removed")){
	   				OSConsole.writeLine(call);
	   				list = null;
	   			}
	   			else OSConsole.writeLine(call);
	   			/*if(args[0].equals("Kill")){
	   				className = Class.forName("commands." + "Switch");  // Call the class loader to load and compile the command
	 	            com = (Command) className.newInstance();
	   				com.execute(args,list, batch);
	   			}
	   			*/	
	   			args = null; 
	   			
	       } catch (Throwable t){ 
	    	   if(list == null) OSConsole.writeLine("No batch has been initalized" ); //no batch exists
	    	   else OSConsole.writeLine("Command not found" );// The class file did not exist
	       } //end try/catch
	       OSConsole.write("Prompt--->  ");
	 }  // End CommandReceived method

}
