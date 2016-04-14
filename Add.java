
public class Add {
	@Override
	public String execute(String args[], ProccessList list){
		Proccess p;
		long pid = System.currentTimeMillis() %100000;
		int priority = Thread.NORM_PRIORITY;
		try{
			p = new Proccess(args,priority,pid);
			list.enQueue(p);
		}
		catch(Exception ex){
			return "Segemintation fault";
		}
		return "Process " + args[0]+ "was added to the batach";
		
	}
	
}
