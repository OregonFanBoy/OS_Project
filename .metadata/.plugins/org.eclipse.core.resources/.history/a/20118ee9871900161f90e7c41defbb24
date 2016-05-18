package commands;
import header.Command;
import header.ProcessList;

import java.io.*;
public class Cat extends Command {
	public Cat(){}
	
	@Override
	public String execute(String[] args,ProcessList list){
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
			       return "File not found or does not exist";
			      }
		return strBuild.toString();
	}
}
