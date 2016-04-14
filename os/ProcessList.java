
public class ProcessList {
	 private Process     head;
	   private Process     tail;

	   public ProcessList()            // Constructor.
	   public Process setPriority(long id, int priority) // Change priority.
	   public String toString()
	   public boolean isEmpty()        // Check if queue is Empty.
	   public Process peek()           // Return the process at the head.
	   public void enQueue(Process p)  // Add process to the tail.
	   public Process deQueue()        // Remove from the head.
	   public Process remove(long id); // Remove process from the list.
	   public Process find(long id);   // Find process in the queue.
}
