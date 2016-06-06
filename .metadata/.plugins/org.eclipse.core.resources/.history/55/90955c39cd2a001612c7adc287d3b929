package programs;

import java.io.IOException;
import java.io.PrintWriter;
import header.DataBase;
import header.Program;

public class Writer extends Thread {

	private DataBase[] db;
	private int writeNum, record;
	private double value;
	private String result;
	private PrintWriter out;
	
	public Writer(int num, DataBase[] db, int record, PrintWriter out, double value){
		this.writeNum = num;
		this.db = db;
		this.record = record;
		this.out = out;
		this.value = value;
	}
	
	public void run(){
		try{
			while(true){
				out.println("Thread Writer " + writeNum + " locked recored " + record + " for write");
				db[record].aquireWrite();
				result = Program.system(("write " + record + " " + value).toString());
				
				out.println("Thread Writer " + writeNum + " wrote " + record + " value = " + result);
				db[record].releaseWrite();
				out.println("Thread Writer " + writeNum + " released record " + record + " for write");
				break;
			}
		}
		catch(InterruptedException | IOException e){}
	}
}
