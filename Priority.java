
public class Priority extends Command {
	Process p;
	long id = Long.parseLong(args[0]);
	int priority = Integer.parseInt(args[1]);
	p = list.find(id);
	if(p==null)return "id not found";
	else if(p < Thread.MIN_PRIORITY || p > Thread.MAX_PRIORITY-1){
		return "You must enter between values 1 and 9";
	}
	else{
		list.setPriority(id,priority);
	}
	return "Success";
}
