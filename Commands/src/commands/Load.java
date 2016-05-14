package commands;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import header.BatchList;
import header.ProcessList;
import header.Command;
import header.Program;

public class Load extends Command{
	Program classInstance;
	@Override
	public String execute(String[] args, ProcessList list, BatchList batch){
		try {
            FileInputStream in = new FileInputStream(args[0] + ".sav");
            ObjectInputStream object_in = new ObjectInputStream(in);
            list =  (ProcessList) object_in.readObject();
            if(batch.find(list.getName()) != null) {object_in.close(); return "Batch already exists";} 
            batch.enQueue(list);
            object_in.close();
        }
        catch (Exception e) { 
            return "Batch not found";
        }
		
		return list.getName();
	}
}
