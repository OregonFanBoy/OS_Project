package header;

import java.io.Serializable;

public class ProcessList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Process head, tail;
	private String name;
	public ProcessList prev, next;
	
	public ProcessList(String name){
		head = tail = null;
		prev = next = null;
		this.name = name;
	}
	
	public Process setPriority(long id, int priority){
		Process p = find(id);
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
		Process current_p = head;
		if(isEmpty()){
			head = p;
		} else {
			
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
		return name;
		
	}
	
	
}
