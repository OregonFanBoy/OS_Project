package header;

public class BatchList {
	private ProcessList head, tail;
	
	public BatchList(){
		head = tail = null;
	}
	public boolean isEmpty(){
		return (head==null);
	}
	public ProcessList peek(){
		return head;
	}
	public void enQueue(ProcessList process){
		if(isEmpty()) head = process;
		else{
			ProcessList current = head;
			while(current.next != null){
				current = current.next;
			}
			tail = process;
			tail.prev = current;
			current.next = process;
		}
	}
	
	public ProcessList deQueue(){
		ProcessList p = head;
		if(p.next == null) head = tail = null;
		else{
			head = p.next;
			head.prev = null;
		}
		return p;
	}
	
	public ProcessList find(String id){
		ProcessList current = head;
		while(current != null){
			if(current.getName().equals(id)) return current;
			current = current.next;
		}
		return null;
	}
	
	public ProcessList remove(String id){
		ProcessList p = find(id);
		if(p == null) return null;
		else if(p == head) p = deQueue();
		else if(p == tail){
			tail = p.prev;
			tail.next = null;
		}
		else{
			p.prev.next = p.next;
			p.next.prev = p.prev;
		}
		return p;
	}
	
	public String traverse(){
		ProcessList p = head;
		StringBuilder build = new StringBuilder();
		while(p != null){
			build.append(p.getName());
			build.append("\n");
			p = p.next;
		}
		return build.toString();
	}
}
