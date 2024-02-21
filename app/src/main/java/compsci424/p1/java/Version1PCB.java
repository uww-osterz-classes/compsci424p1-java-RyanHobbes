/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.LinkedList;

/**
 * The process control block structure that is used to track a process's parent
 * and children (if any) in Version 1.
 */
public class Version1PCB {

		private int parentId;
	    private int processId;
	    private LinkedList<Integer> PCBchildrenIds;

	    // Constructor
	    public Version1PCB(int parentId) {
	        this.parentId = parentId;
	        this.PCBchildrenIds = new LinkedList<>();
	    }

	    // Setters and getters
	    public int getProcessId() {
	        return processId;
	    }

	    public void setProcessId(int processId) {
	        this.processId = processId;
	    }

	    public LinkedList<Integer> getChildren() {
	        return PCBchildrenIds;
	    }

	    public void setChildren(LinkedList<Integer> children) {
	        this.PCBchildrenIds = children;
	    }
	    
	    public int getParentId() {
	    	return this.parentId;
	    }
	    
	    public void setParentId(int parentId) {
	    	this.parentId = parentId;
	    }
	    //add child
	    public void addChild(int cIndex) {
	    	PCBchildrenIds.add(cIndex);
	    }
	    //remove child
	    public void removeChild(int childIndex) {
	    	PCBchildrenIds.remove(childIndex);
	    }
	    public static Version1PCB findParent(int parentId, LinkedList<Version1PCB> list) { //ask how to access the global linked list
	        for (Version1PCB pcb : list) {
	            if (pcb.getProcessId() == parentId) {
	                return pcb;
	            }
	        }
	        return null;
	    }
	    public static Version1PCB findProcess(int processId, LinkedList<Version1PCB> list ) { //ask how to access the global linked list
	    	for (Version1PCB pcb : list) {
	    		if (pcb.getProcessId() == processId) {
	    	
	    		return pcb;
	    	}
	    }
			return null;
	  }
	 
}