/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

/**
 * Implements the process creation hierarchy for Version 2, which does not use
 * linked lists.
 * 
 * This is a template. Program1.java *must* contain the main class for this
 * program. Otherwise, feel free to edit these files, even these pre-written
 * comments or my provided code. You can add any classes, methods, and data
 * structures that you need to solve the problem and display your solution in
 * the correct format.
 */
public class Version2 {
	// Declare any class/instance variables that you need here.
	private static int lastAssignedPIndex = -1;
		Version2PCB[] pcbArray;
	/**
	 * Default constructor. Use this to allocate (if needed) and initialize the PCB
	 * array, create the PCB for process 0, and do any other initialization that is
	 * needed.
	 */
	public Version2() {
		pcbArray = new  Version2PCB[20];
	}

	/**
	 * Creates a new child process of the process with ID parentPid.
	 * 
	 * @param parentPid
	 *            the PID of the new process's parent
	 * @return 0 if successful, not 0 if unsuccessful
	 */
	int create(int parentPid) {
		// If parentPid is not in the process hierarchy, do nothing;
		// your code may return an error code or message in this case,
		// but it should not halt
		boolean flag = false;
		for (Version2PCB pcb : pcbArray) {
			if (pcb != null && pcb.getParentId() == parentPid) {
				flag = true;
				break;
			}
		}
		if (flag == false) {
			System.out.println("ERROR - Parent ID not found");
			return -1;  
			}
		//check for open spots******************

		   int openSpotIndex = -1;
		    for (int i = 0; i < pcbArray.length; i++) {
		        if (pcbArray[i] == null) {
		            openSpotIndex = i;
		            break;
		        }
		    }
		    if (openSpotIndex == -1) {
		        System.out.println("ERROR - PCB array is full");
		        return -2; // PCB array is full
		    }
		// Assuming you've found the PCB for parentPid in the PCB array:
		// 1. Allocate and initialize a free PCB object from the array
		// of PCB objects
		    Version2PCB newPCB = new Version2PCB(parentPid);
		    pcbArray[openSpotIndex] = newPCB;
		// 2. Connect the new PCB object to its parent, its older
		// sibling (if any), and its younger sibling (if any)
		    Version2PCB parentPCB = pcbArray[parentPid];
		    if (parentPCB.getFirstChild() == -1) {
		    	parentPCB.setFirstChild(openSpotIndex);
		    }
		    else {
		        int youngestSiblingIndex = parentPCB.getFirstChild();
		        Version2PCB youngestSibling = pcbArray[youngestSiblingIndex];
		        while (youngestSibling.getYoungerSibling() != -1) {
		            youngestSiblingIndex = youngestSibling.getYoungerSibling();
		            youngestSibling = pcbArray[youngestSiblingIndex];
		        }
		        youngestSibling.setYoungerSibling(openSpotIndex);
		    }
		// You can decide what the return value(s), if any, should be.
		// If you change the return type/value(s), update the Javadoc.
		return 0; // often means "success" or "terminated normally"
	}

	/**
	 * Recursively destroys the process with ID parentPid and all of its descendant
	 * processes (child, grandchild, etc.).
	 * 
	 * @param targetPid
	 *            the PID of the process to be destroyed
	 * @return 0 if successful, not 0 if unsuccessful
	 */
	int destroy(int targetPid) {
		// If targetPid is not in the process hierarchy, do nothing;
		// your code may return an error code or message in this case,
		// but it should not halt
		if (pcbArray[targetPid].equals(null)) {
	        System.out.println("ERROR - PCB not in array");
	        return -3;
			}

		// Assuming you've found the PCB for targetPid in the PCB array:
		// 1. Recursively destroy all descendants of targetPid, if it
		// has any, and mark their PCBs as "free" in the PCB array
		// (i.e., deallocate them)
		destroyDescendantsRecur(targetPid);
		// 2. Adjust connections within the hierarchy graph as needed to
		// re-connect the graph
		int parentPid = pcbArray[targetPid].getParentId();
	    int firstChild = pcbArray[parentPid].getFirstChild();
	    int prevSibling = -1;
	    int currentPid = firstChild;
	    
	    while (currentPid != targetPid) {
	        prevSibling = currentPid;
	        currentPid = pcbArray[currentPid].getYoungerSibling();
	    }

	    if (prevSibling != -1) {
	        pcbArray[prevSibling].setYoungerSibling(pcbArray[targetPid].getYoungerSibling());
	    } else {
	        pcbArray[parentPid].setFirstChild(pcbArray[targetPid].getYoungerSibling());
	    }

		// 3. Deallocate targetPid's PCB and mark its PCB array entry
		// as "free"
	    pcbArray[targetPid] = null;
		// You can decide what the return value(s), if any, should be.
		// If you change the return type/value(s), update the Javadoc.
		return 0; // often means "success" or "terminated normally"
	}
	void destroyDescendantsRecur(int pid) {
	    if (pid == -1) {//base case
	        return;
	    }
	    //recursive step
	    Version2PCB pcb = pcbArray[pid];
	    destroyDescendantsRecur(pcb.getFirstChild());
	    pcbArray[pid] = null; //mark free
	    destroyDescendantsRecur(pcb.getYoungerSibling());// clear all younger of first child
	}

	/**
	 * Traverse the process creation hierarchy graph, printing information about
	 * each process as you go. See Canvas for the *required* output format.
	 * 
	 * You can directly use "System.out.println" statements (or similar) to send the
	 * required output to stdout, or you can change the return type of this function
	 * to return the text to the main program for printing. It's your choice.
	 */
	void showProcessInfo() {
	    for (int i = 0; i < pcbArray.length; i++) {
	        Version2PCB pcb = pcbArray[i];
	        if (pcb != null) {
	            int parentId = pcb.getParentId();
	            int firstChild = pcb.getFirstChild();
	            System.out.print("Process " + i + ": parent is " + parentId + " and children are ");
	            //check all children
	            int currentChild = firstChild;
	            while (currentChild != 0) {
	                System.out.print(currentChild + " ");
	                currentChild = pcbArray[currentChild].getYoungerSibling();
	            }
	            System.out.println();
	        }
	    }
	    System.out.println();
	}

	/* If you need or want more methods, feel free to add them. */

}
