package commands;
import header.BatchList;
import header.Command;
import header.ProcessList;

import java.io.*;
public class Cat extends Command {
	public Cat(){}
	
	@Override
	public String execute(String[] args,ProcessList list, BatchList batch){
		String str;
		  BufferedReader br = null,in;
		  StringBuilder strBuild = new StringBuilder();
			      try{		        
			    	 File file = new File(args[0]);
					 in = new BufferedReader(new FileReader(file));
					 br  = new BufferedReader(in);
			         while ((str = br.readLine()) != null) {
			           strBuild.append(str);
			           strBuild.append("\n");
			         }       
			           br.close();
			      }catch(Throwable t){
			    	  t.printStackTrace();
			       return "File not found or does not exist";
			      }
		return strBuild.toString();
	}
}
