package commands;
import header.BatchList;
import header.Command;
import header.ProcessList;
import header.Process;

public class Queue extends Command{
	//public Queue(){}
	@Override
	public String execute(String args[],ProcessList list, BatchList batch){
		StringBuilder builder = new StringBuilder();
		Process p = list.peek();
		String header;
		builder.append(String.format("Current batch queue = " +list.getName() + "\n"));
		builder.append(String.format("%-16s","Name"));
		builder.append(String.format("%-14s","ID"));
		builder.append(String.format("%-14s","Status"));
		builder.append(String.format("%-14s","Runtime"));
		builder.append(String.format("%-10s","Priority"));
		builder.append(String.format("%-10s","Arguments"));
		builder.append("\n");
		
		while(p != null){
			args = p.getArgs();
			StringBuffer result = new StringBuffer();
			
			for (int i = 1; i < args.length; i++) {
			   result.append( args[i] );
			   result.append( " " );
			}
			String values = result.toString();
			builder.append(String.format("%-16s",p.getName()));
			builder.append(String.format("%-14s",p.getId()));
			builder.append(String.format("%-14s",p.getStatus()));
			builder.append(String.format("%-16s",p.getRunTime()));
			builder.append(String.format("%-12s",p.getPriority()));
			builder.append(values);
			builder.append("\n");
			p = p.next;
		}
		header = builder.toString();
		return header;
		//return runList.toString();
	}
}
