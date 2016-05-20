package commands;

import header.BatchList;
import header.Command;
import header.ProcessList;
import header.Program;


public class Kill extends Command {
	Program classInstance;

	@Override
	public String execute(String[] args, ProcessList list, BatchList batch){
		try{
			list = batch.remove(args[0]);
			if(list == null) return "Batch not found";
		}
		catch(Throwable t){ return "Batch not found";}
		return args[0]+ " removed";
	}
}
