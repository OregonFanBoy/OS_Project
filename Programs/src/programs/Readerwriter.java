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
			
			
			for(int i = 0; i < db.length; i++){
				db[i] = new DataBase();
			}
				
				for(int j = 0; j < rd; j++){
					readerArray[j] = new Thread(new Reader(j, db, this.out, iterations));//pass in iterations vs rd
					readerArray[j].start();
				}
				for(int j = 0; j < wr; j++){
					writerArray[j] = new Thread(new Writer(j, db, this.out, iterations));//pass in itterations vs wr.
					writerArray[j].start();
				}
				//place start in the array.
					
					
				//loop the joins.
				for(int i = 0; i < rd; i++){
					readerArray[i].join();
					
				}
				for(int i = 0; i < wr; i++){
					writerArray[i].join();
				}
			schedule();
			if(isInterrupted()) terminate();
		}
		catch(Exception e){
			terminate();
			return ILLEGAL_PARAMETER;
		}
		return SUCCESS;
 }
	
	/*solve deadlock:
	 * pick the record number, place it into array. 4th argument will be the lenght of the array.
	 * sort array.
	 * lock the reader
	 * release.
	 * 
	 * (non-Javadoc)
	 * @see header.Program#terminate()
	 */

	public void terminate(){
		try{
			for(int i =0; i < rd; i++){
				readerArray[i].interrupt();
			}
			for(int i = 0; i < wr; i++){
				writerArray[i].interrupt();
			}
			for(int i =0; i < rd; i++){
				readerArray[i].join();
				out.println("Thread Reader " + i + " interrupted");
			}
			for(int i = 0; i < wr; i++){
				writerArray[i].join();
				out.println("Thread Writer " + i + " interrupted");
			}
		}
		catch(InterruptedException e){}
		out.println("ReaderWriter: interrupted");
	}
}
