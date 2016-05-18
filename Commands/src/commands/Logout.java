package commands;
import header.BatchList;
import header.Command;
import header.ProcessList;
public class Logout extends Command{
	
	@Override
	public String execute(String[] args,ProcessList list, BatchList batch){
		System.exit(0);
		return null;
	}
}
