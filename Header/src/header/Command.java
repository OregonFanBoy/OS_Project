package header;

public abstract class Command {
	public abstract String execute(String[] args, ProcessList list, BatchList batch) throws InterruptedException;
	
}
