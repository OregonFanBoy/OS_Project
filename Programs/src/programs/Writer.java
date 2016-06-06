package programs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import header.DataBase;
import header.Program;

public class Writer extends Thread {

	private DataBase[] db;
	private int writeNum, record,tempRec, iterations;
	private double value;
	private String result;
	private PrintWriter out;
	
	public Writer(int num, DataBase[] db, PrintWriter out, int iterations){
		this.writeNum = num;
		this.db = db;
		this.out = out;
		this.iterations = iterations;
	}
	
	public void run(){
		Random rand = new Random();
		
		try{
			for(int j = 0; j < iterations; j++){
				tempRec = record = rand.nextInt(db.length);
				for(int i =0; i < db.length; i ++){
					if(record > db.length-1) record = 0;
					db[record].aquireWrite();
					out.println("Thread Writer " + writeNum + " locked recored " + record + " for write");
					record++;
				}
				record = tempRec;
				for(int i = 0; i < db.length; i ++){
					value = Math.random()*1;
					if(record > db.length-1) record = 0;
					result = Program.system(("write " + record + " " + value).toString());
					out.println("Thread Writer " + writeNum + " wrote " + record + " value = " + result);
					record++;
					
				}
				record = tempRec;
				for(int i = 0; i < db.length; i++){
					if(record > db.length-1) record = 0;
					db[record].releaseWrite();
					out.println("Thread Writer " + writeNum + " released record " + record + " for write");
					record++;
					
				}
				Program.schedule();
			
			}
		}
		catch(InterruptedException | IOException e){}
	}
}
