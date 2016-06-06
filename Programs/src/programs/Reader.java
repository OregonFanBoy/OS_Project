package programs;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import header.Program;
import header.DataBase;

public class Reader extends Thread{
	private DataBase[] db;
	private int readerNum, record, tempRec, iterations;
	private PrintWriter out;
	private String result;
	
	public Reader(int num, DataBase[] db, PrintWriter out, int iterations){
		this.readerNum = num;
		this.db = db;
		this.out = out;
		this.iterations = iterations;
	}
	
	public void run(){
		Random rand = new Random();
		try{
			//For each itteration of record threads.
			for(int j = 0; j < iterations; j++){ // loop the itterations only.
				tempRec = record = rand.nextInt(db.length);
				//loop for locking
				for(int i = 0; i < db.length; i++ ){
					if(record > db.length - 1) record = 0;
					db[record].acquiredRead();
					out.println("Thread Reader "+ readerNum + " locked record " + record + " for read");
					record++;
					
				}
				record = tempRec;
				//loop for reading the record.
				for(int i = 0; i < db.length; i++){
					if(record > db.length-1) record = 0;
					result = Program.system(("read " + record).toString());
					out.println("Thread Reader " + readerNum + " read " + record + " value = " + result);
					record++;
				
				}
				record = tempRec;
				//loop for releasing the lock.
				for(int i = 0; i < db.length; i++){
					if(record > db.length-1) record = 0;
					db[record].releaseRead();
					out.println("Thread Reader " + readerNum + " released record " + record + " for read");
					record++;
					
				}
				Program.schedule();
			}	
		}
		catch(InterruptedException | IOException e){}
	}
}
