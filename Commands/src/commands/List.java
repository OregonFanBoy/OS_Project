package commands;

import header.BatchList;
import header.Command;
import header.ProcessList;

public class List extends Command{
	@Override
	public String execute(String[] args, ProcessList list, BatchList batch) {
		//checks if the list is initialized
		if(list == null) return "No batch has been initalized"; 
		String bat = batch.traverse();
		return bat;
	}
}
