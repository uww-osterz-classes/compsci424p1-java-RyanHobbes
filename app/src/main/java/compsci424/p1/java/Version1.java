/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.LinkedList;

/** 
 * Implements the process creation hierarchy for Version 1, which uses
 * linked lists.
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
public class Version1 {
    // Declare any class/instance variables that you need here.
		private static int lastAssignedPIndex = -1;
	 	private int pIndex;
	    private LinkedList<Version1PCB> children; //list of all pcb - different name
    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed. 
     */
	   public Version1() {
		   lastAssignedPIndex++;
		   pIndex = lastAssignedPIndex;
		   children = new LinkedList<>();
	        Version1PCB pcb0 = new Version1PCB(0);
	        this.children.addFirst(pcb0);
	    }
	   // Setters and getters
	    public int getPIndex() {
	        return pIndex;
	    }

	    public void setPIndex(int pIndex) {
	        this.pIndex = pIndex;
	    }

	    public LinkedList<Version1PCB> getChildren() {
	        return children;
	    }

	    public void setChildren(LinkedList<Version1PCB> children) {
	        this.children = children;
	    }
    /**
     * Creates a new child process of the process with ID parentPid. 
     * @param parentPid the PID of the new process's parent
     * @return 0 if successful, not 0 if unsuccessful
     */
    int create(int parentPid) {
        // If parentPid is not in the process hierarchy, do nothing; 
        // your code may return an error code or message in this case,
        // but it should not halt
    	boolean flag = false;
    	 for (Version1PCB pcb : children) {
    	        if (pcb.getProcessId() == parentPid) {
    	            flag = true;
    	            break;
    	        }
    	    }
    	if (flag == false) {
    			System.out.println("ERROR - Parent ID not found");
    			return -1;    		
    	}
    	


        // Assuming you've found the PCB for parentPid in the PCB array:
        // 1. Allocate and initialize a free PCB object from the array
        //    of PCB objects
    	Version1PCB newPCB = new Version1PCB(parentPid);
    	newPCB.setProcessId(++lastAssignedPIndex);
        // 2. Insert the newly allocated PCB object into parentPid's
        //    list of children
    	
    	Version1PCB parentPCB = Version1PCB.findParent(parentPid,children);
    	
    	if (parentPCB != null) {
    		parentPCB.addChild(newPCB.getProcessId());
    		children.add(newPCB);
    	}
    	else {
    		System.out.println("ERROR - Parent PCB not found");
    		return-1;
    	}
    	
        // You can decide what the return value(s), if any, should be.
        // If you change the return type/value(s), update the Javadoc.
        return 0; // often means "success" or "terminated normally"
    }

    /**
     * Recursively destroys the process with ID parentPid and all of
     * its descendant processes (child, grandchild, etc.).
     * @param targetPid the PID of the process to be destroyed
     * @return 0 if successful, not 0 if unsuccessful
     */
    int destroy (int targetPid) {
         // If targetPid is not in the process hierarchy, do nothing; 
         // your code may return an error code or message in this case,
         // but it should not halt
    	boolean flag = false;
    	for (Version1PCB pcb : children) {
    		if (pcb.getProcessId() == targetPid) {
    			flag = true;
    			break;
    		}
    	}
    	if (!flag) {
    		System.out.println("ERROR - Target PCB not found");
    		return -2;
    	}
    		
         // Assuming you've found the PCB for targetPid in the PCB array:
         // 1. Recursively destroy all descendants of targetPid, if it
         //    has any, and mark their PCBs as "free" in the PCB array 
         //    (i.e., deallocate them)
    	destroyDescendants(targetPid);
         // 2. Remove targetPid from its parent's list of children
    	removeChildFromParent(targetPid);
         // 3. Deallocate targetPid's PCB and mark its PCB array entry
         //    as "free"

         // You can decide what the return value(s), if any, should be.
         // If you change the return type/value(s), update the Javadoc.
        return 0; // often means "success" or "terminated normally"
    }
    private  void destroyDescendants(int targetPid) {
    	Version1PCB targetPCB = Version1PCB.findProcess(targetPid, children);
    	if (targetPCB != null) {
    		LinkedList<Integer> childrenIDs = targetPCB.getChildren();
    		for (Integer childPid : childrenIDs) {
    			destroyDescendants(childPid);
    		}
    	}
    }
    private void removeChildFromParent(int targetPid) {
    	for (Version1PCB pcb : children) {
    		LinkedList<Integer> childrenIDs = pcb.getChildren();    	
    		if (childrenIDs.contains(targetPid)) {
    			childrenIDs.remove((Integer)targetPid);
    			break;
    		}
    	}
    }

    /**
     * Traverse the process creation hierarchy graph, printing
     * information about each process as you go. See Canvas for the
     * *required* output format. 
     *         
     * You can directly use "System.out.println" statements (or
     * similar) to send the required output to stdout, or you can
     * change the return type of this function to return the text to
     * the main program for printing. It's your choice. 
     */
    void showProcessInfo() {
    	for (Version1PCB pcb : children) {
    		LinkedList <Integer> childrenIds = pcb.getChildren();
    		System.out.println("Process " + pcb.getProcessId() + ": parent is " + pcb.getParentId() + " and children are " );
    		for (Integer childID : childrenIds) {
    			System.out.println(childID + " ");
    		}
    		System.out.println();
    	}
    }

    /* If you need or want more methods, feel free to add them. */
    
}
