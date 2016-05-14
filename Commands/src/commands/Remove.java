package commands;
import header.BatchList;
import header.Command;
import header.ProcessList;
import header.Process;


public class Remove extends Command{
	public Remove(){}
	@Override
	public String execute(String[] args,ProcessList list, BatchList batch){
		Process p = list.remove(Long.parseLong(args[0]));
		if(p == null) return "ID not found or does not exist";
		
	return "Process " + args[0] + " was removed ";
//return runList.remove(Long.parseLong(args[0])).toString() + "\n--- removed from queue.";
	}
}
