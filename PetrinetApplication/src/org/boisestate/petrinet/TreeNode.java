package org.boisestate.petrinet;


import java.util.ArrayList;
import java.util.List;

public class TreeNode<Marking>{
	
	public static final int MARK_OLD = 200;  //old node
	public static final int MARK_DEAD = 300;  //dead node
	public static final int MARK_NEW = 100;   // new node
	public static final int MARK_VISITED = 99;   // new node
	
	private int status = 0;
    private Marking data = null;
    private ArrayList<TreeNode> children = new ArrayList<TreeNode>();
    private TreeNode parent = null;

    public TreeNode(Marking data) {
        this.setData(data);
        this.setStatus(MARK_NEW);
    }

    public void addChild(TreeNode child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(Marking data) {
        TreeNode<Marking> newChild = new TreeNode<Marking>(data);
        newChild.setParent(this);
        children.add(newChild);
    }

    public void addChildren(List<TreeNode<Marking>> list) {
        for(TreeNode t : list) {
            t.setParent(this);
        }
        this.children.addAll(list);
    }

    public ArrayList<TreeNode> getChildren() {
        return children;
    }

    public Marking getData() {
        return data;
    }

    public void setData(Marking data) {
        this.data = data;
    }
    
    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	private void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent() {
        return parent;
    }
}