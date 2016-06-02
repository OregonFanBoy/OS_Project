package programs;
import java.io.IOException;
import java.io.PrintWriter;
import header.Program;
import header.DataBase;

public class Reader extends Thread{
	private DataBase[] db;
	private int readerNum, record;
	private PrintWriter out;
	private double value;
	private String result;
	
	public Reader(int num, DataBase[] db, int record, PrintWriter out, double value){
		this.readerNum = num;
		this.db = db;
		this.record = record;
		this.out = out;
		this.value = value;
	}
	
	public void run(){
		try{
			while(true){
				out.println("Thread Reader "+ readerNum + " locked record " + record + " for read");
				db[record].acquiredRead();
				result = Program.system(("read " + record).toString());
				out.println("Thread Reader " + readerNum + " read " + record + " value = " + result);
				db[record].releaseRead();
				out.println("Thread Reader " + readerNum + " released record " + record + " for read");
				break;
			}
		}
		catch(InterruptedException | IOException e){}
	}
}
