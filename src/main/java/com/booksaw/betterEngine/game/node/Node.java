package com.booksaw.betterEngine.game.node;

import java.util.ArrayList;
import java.util.List;

import com.booksaw.betterEngine.Updatable;
import com.booksaw.betterEngine.game.Location;

public abstract class Node implements Updatable {

	private Node parent;
	private final List<Node> children;
	private Location location;

	protected Node(Node parent) {
		this.parent = parent;
		children = new ArrayList<>();
	}

	public List<Node> getChildrenClone() {
		return new ArrayList<>(children);
	}

	public Node getParent() {
		return parent;
	}

	/**
	 * Used to set the parent of this node
	 * 
	 * @param parent The new parent of this node
	 */
	public void setParent(Node parent) {

		// checking if this was called by Node.addChild()
		if (parent.isChild(this)) {
			this.parent = parent;
		} else {
			// the changing of child is meant to be done from the parent
			parent.addChild(this);
		}
	}

	/**
	 * Used to add a child to this node
	 * 
	 * @param child the child to add
	 */
	public void addChild(Node child) {
		child.getParent().removeChild(child);
		children.add(child);
		child.setParent(this);
	}

	/**
	 * Used to remove a child from the list, private as it should only be used in
	 * addChild
	 * 
	 * @param child The child to remove
	 */
	private void removeChild(Node child) {
		children.remove(child);
	}

	public boolean isChild(Node node) {
		return children.contains(node);
	}

	/**
	 * Used to update this node and all children nodes
	 * 
	 * @param delta           The number of updates that need to occur (ie 1 =
	 *                        1/100th of a second, 2 = 2/100ths of a second)
	 * @param parentComponent The location of the parent component to this component
	 */
	public final void updateAll(int delta) {

		update(delta);

		for (Node child : getChildrenClone()) {
			child.updateAll(delta);
		}

	}

}
