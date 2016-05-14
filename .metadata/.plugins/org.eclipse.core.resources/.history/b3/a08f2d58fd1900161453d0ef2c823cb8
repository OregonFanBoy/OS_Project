//@author Franklin Chappell
//Purpose: To save a batch file
package commands;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import header.BatchList;
import header.Command;
import header.ProcessList;


public class Save extends Command{
	@Override
	public String execute(String[] args,ProcessList list, BatchList batch){
		try{
			FileOutputStream out = new FileOutputStream(args[0]+ ".sav");
			ObjectOutputStream obj_out = new ObjectOutputStream(out);
			
			obj_out.writeObject(list);
			obj_out.close();
		}
		catch(Exception ex){
			return "Batch file not saved";
		}
		return "Batch " + list.getName() +" was saved";
	}
}
