package header;
public class ProcessList {
	private Process head;
	private Process tail;
	
	public ProcessList(){
		head = tail = null;
		
	}
	
	public Process setPriority(long id, int priority){
		Process p = find(id);
		/*
		 if(p==null){
			   return null;
		   }
		   else{
			   p.setPriority(priority);
		   }
		 */
		p.setPriority(priority);
		return p;
	}

	public String toString(){
		String strin = "";
		Process curr = head;
		while(curr.next!=null){
			strin += curr.getName() + " ";
		}
		return strin;
	}
	
	public boolean isEmpty(){
		return (head==null);
	}
	
	public Process peek(){
		return head;
	}
	
	public void enQueue(Process p){
		if(isEmpty()){
			head = p;
		} else {
			Process current_p = head;
			while(current_p.next != null){
				current_p = current_p.next;
			}
			tail = p;
			tail.previous = current_p;
			current_p.next = p;
		}
	}
	
	public Process deQueue(){
		Process p = head;
		if(p.next == null)	head = tail = null;
		 else {
			head = p.next;
			head.previous = null;
		}
		return p;	
	}
	
	public Process remove(long id){
		Process p;
		p = find(id);
		if(p == null){ 
		} else if (p == head) {
			p = deQueue();
		} else if(p == tail){
			tail = p.previous; 
			tail.next = null; 
		} else {
			p.previous.next = p.next;
			p.next.previous = p.previous;
		}
		return p;
	}
	
	public Process find(long id){
		Process current_P = head;
		
		while(current_P != null){
			if(current_P.getId() == id)return current_P;
			current_P = current_P.next;
			
		}
		 return null;
	}
	
	public String getName(){
		return null;
		
	}
}
