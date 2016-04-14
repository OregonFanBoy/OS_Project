
public class Queue extends Command{
	public String execute(String args[],ProccessList list){
		Process p = list.peak();
		String header = "something"; //Format the header string
		StringBuffer r = new StringBuffer();
		for(int i=1;i<args.length;i++){
			r.append(args[1]);
			r.append( " " );
		}
		String values = r.toString();
		return header+ p.getName()+ "  " +p.getID()+ "  "+p.getStatus()+"  "+p.getRunTime()+"  "+p.getPriority()+
				"  "+values;
	}
}
