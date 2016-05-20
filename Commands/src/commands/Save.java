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
		ProcessList temp, save = batch.find(args[0]);
		try{
			if(save == null) return "Batch file not found and could not be saved";
			temp = save.next;
			save.next = null;
			FileOutputStream out = new FileOutputStream(args[0]+ ".sav");
			ObjectOutputStream obj_out = new ObjectOutputStream(out);
			
			obj_out.writeObject(save);
			obj_out.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
			return "Batch file not saved";
		}
		save.next = temp;
		return "Batch " + save.getName() +" was saved";
	}
}
