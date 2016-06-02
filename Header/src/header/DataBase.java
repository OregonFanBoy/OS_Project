package header;

import java.util.concurrent.Semaphore;

public class DataBase {
	private int reader;
	private Semaphore mutex, db;
	
	public DataBase(){
		reader = 0;
		mutex = new Semaphore(1);
		db = new Semaphore(1);
		
	}
	// First reader acquired db
	public void acquiredRead() throws InterruptedException {
		mutex.acquire();
		if(++reader==1)
			db.acquire();
		mutex.release();
	}
	//Last reader released db
	public void releaseRead() throws InterruptedException{
		mutex.acquire();
		if(--reader==0)
			db.release();
		mutex.release();
	}
	
	public void aquireWrite() throws InterruptedException{
		db.acquire();
	}
	
	public void releaseWrite(){
		db.release();
	}
}
