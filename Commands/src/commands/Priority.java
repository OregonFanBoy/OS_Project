package commands;
import header.BatchList;
import header.Command;
import header.ProcessList;
import header.Process;

public class Priority extends Command {
	public Priority(){
		
	}
	@Override
	public String execute(String[] args,ProcessList list, BatchList batch){
		Process process;
		long id = Long.parseLong(args[0]);
		int priority = Integer.parseInt(args[1]);
		process = list.find(id);
		if(priority <Thread.MIN_PRIORITY || priority > Thread.MAX_PRIORITY - 1) return "You must enter between valures 1 and 9";
		else if(process == null) return "Id not found or does not exist";
		else{
			list.setPriority(id, priority);
			list.reQueue(process);
		}
	/*
			if(process == null)
				return "Id not found or does not exist";
			else if(priority < Thread.MIN_PRIORITY || priority > Thread.MAX_PRIORITY - 1)
				return "Priority must be between 1 and 9";
			else 
				list.setPriority(id, priority);
				*/
		return "Process " + process.getName() + " " + process.getId() + " " + " priority set to " + process.getPriority();
	}
}

