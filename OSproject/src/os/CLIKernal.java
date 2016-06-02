package os;
import java.io.IOException;
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
	SystemCall server;
	
	public static void main(String[] args) {
		new CLIKernal();
	}
	
	public CLIKernal(){
		try{
			server = new SystemCall(6013,20);
			
		}catch(IOException e){
			
		}
		server.start();
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
	   			//Switch the batch list
	   			else if(argData.length > 1 && call.equals(args[0])){
	   				list = batch.find(call);
	   				OSConsole.writeLine(call + " is now active");
	   			}
	   			// kills the batch
	   			else if(argData.length > 1 && call.equals(args[0]+ " removed")){
	   				OSConsole.writeLine(call);
	   				if(list.getName().equals(args[0])){
	   					if(list.next == null)list = list.prev;
	   					else list = list.next;
	   				} 
	   			}
	   			else if(call =="Loggin out"){
	   				server.close();
	   				System.exit(0);
	   			}
	   			else OSConsole.writeLine(call);
	   			args = null; 
	   			
	       } catch (Throwable t){ 
	    	   if(list == null) OSConsole.writeLine("Batch does not exsist or no batch was initalized" ); //no batch exists
	    	   else OSConsole.writeLine("Command not found" );// The class file did not exist
	       } //end try/catch
	       OSConsole.write("Prompt--->  ");
	 }  // End CommandReceived method

}
