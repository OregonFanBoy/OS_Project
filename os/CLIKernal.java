import console.CommandListener;
import console.OSConsole;

public class CLIKernal implements CommandListener{
	public CLIKernal(){
		list = new ProcessList();
		OSConsole console = new OSConsole("Batch Sequencer");
		console.setCommandListener(this);
		console.write("Prompt");
	}
	@Override
	public void commandReceived(String commandString){
		//add code to process each command.
		String command = input;
		String[]argData = command.split(" ");
		command = argData[0].substring(0,1).toUpperCase());//+ other args 
		
	}

}
