package console;

/** Class to test the OS Console class */
public class Cli implements CommandListener
{
	OSConsole console;
	
	public static void main(String[] args)
	{
		new Cli();
	}
	
	public Cli()
	{
		console = new OSConsole("Batch Sequencer");
		console.setCommandListener(this);
		console.login();
	}

	@Override
	public void commandReceived(String command)
	{
		console.writeLine("The command is " + command);
	}
	
}