package commands;
import header.BatchList;
import header.Command;
import header.ProcessList;
public class New extends Command {
	
	@Override
	public String execute(String[] args,ProcessList list, BatchList batch){
		//list = new ProcessList();
		String name = args[0];
		if(batch.find(name) != null) return "Batch already exists";
		
		return name + " initialized";
	}
}
