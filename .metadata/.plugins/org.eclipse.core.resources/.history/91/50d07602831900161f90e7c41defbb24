package commands;
import header.BatchList;
import header.Command;
import header.ProcessList;
import header.Program;

public class Switch extends Command{
	Program classInstance;
	@Override
	public String execute(String[] args, ProcessList list, BatchList batch) {
		
		try{
		list = batch.find(args[0]);
		String name = list.getName();
		return name;
		}catch(Throwable t){return "Batch not found";}
	}
}
