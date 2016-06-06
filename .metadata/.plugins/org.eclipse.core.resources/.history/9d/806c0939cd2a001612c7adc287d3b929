package programs;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Random;
import header.DataBase;
import header.Program;

public class Readerwriter extends Program implements Serializable {

	private static final long serialVersionUID = 1L;
	private DataBase[] db;
	private Thread[] readerArray;
	private Thread[] writerArray;
	private int rd, wr, iterations, record;
	private PrintWriter out;
	
	public Readerwriter(){
		super("Readerwriter");
	}
	
	public int run(PrintWriter out, String[] args) throws InterruptedException{
		try{
			db = new DataBase[Integer.parseInt(system("size"))];
			try{
				rd = Integer.parseInt(args[1]);
				wr = Integer.parseInt(args[2]);
				iterations = Integer.parseInt(args[3]);
				record = Integer.parseInt(args[4]);
			}
			catch(Exception e){return ILLEGAL_PARAMETER;
			}
			readerArray = new Thread[rd];
			writerArray = new Thread[wr];
			this.out = out;
			Random rands = new Random();
			int rand, count = 1, base = 1;
			for(int i = 0; i < db.length; i++){
				db[i] = new DataBase();
			}
			rand = rands.nextInt(db.length);
			double value = Math.random()*1;
			for(int j = 0; j< iterations; j++){
				for(int i = 0; i < rd; i++){
					readerArray[i] = new Thread(new Reader(i, db, rand, this.out, value));
					readerArray[i].start();
					rand = (rand + base);
					count ++;
					if(rand >= db.length)
						rand = 0;
					if(count > record){
						rand = rands.nextInt(db.length);
						count = 1;
					}
				}
				for(int i = 0; i < wr; i++){
					value = Math.random()*1;
					writerArray[i] = new Thread(new Writer(i, db, rand, this.out, value));
					writerArray[i].start();
					rand = (rand + base);
					count ++;
					if(rand >= db.length)
						rand = 0;
					if(count > record){
						rand = rands.nextInt(db.length);
						count = 1;
					}
				}
				schedule();
				if(isInterrupted()) terminate();
			}
		}
		catch(Exception e){
			terminate();
			return ILLEGAL_PARAMETER;
		}
		return SUCCESS;
 }
	
	public void terminate(){
		try{
			for(int i = 0; i < rd; i++){
				readerArray[i].interrupt();
				readerArray[i].join();
				out.println("Thread Reader " + i + " interrupted");
			}
			for(int i = 0; i < wr; i++){
				writerArray[i].interrupt();
				writerArray[i].join();
				out.println("Thread Writer " + i + " interrupted");
			}
		}
		catch(InterruptedException e){}
		out.println("ReaderWriter: interrupted");
	}
}
