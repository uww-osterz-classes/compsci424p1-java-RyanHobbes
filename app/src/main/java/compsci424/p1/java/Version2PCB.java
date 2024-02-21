/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;


/**
 * The process control block structure that is used to track a process's parent,
 * first child, younger sibling, and older sibling (if they exist) in Version 2.
 */
public class Version2PCB {
	private int parentId;
	private int firstChild;
	private int youngerSibling;
	private int olderSibling;

    // Constructor
    public Version2PCB(int parentId) {
        this.parentId = parentId;
    }
    
    //setters and getters
    public void setParentId(int parentId) { // parent id
        this.parentId = parentId;
    }
    
    public int getParentId() {
        return parentId;
    }
    
    public void setFirstChild(int firstChild) {// first child id
        this.firstChild = firstChild;
    }
    
    public int getFirstChild() {
        return firstChild;
    }
    
    public void setYoungerSibling(int youngerSibling) {// younger sibling id
        this.youngerSibling = youngerSibling;
    }
    
    public int getYoungerSibling() {
        return youngerSibling;
    }
    
    public void setOlderSibling(int olderSibling) {// older sibling id
        this.olderSibling = olderSibling;
    }
    
    public int getOlderSibling() {
        return olderSibling;
    }
    
}

