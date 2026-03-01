package kd_tree;

public class KdNode {
	
	int x;
	int y;
	KdNode left;
	KdNode right;

	
	public KdNode(int x, int y) {
		this.x = x;
		this.y = y;
		this.left = this.right = null;
	}


	public int getX() {
		return x;
	}

	
	public int getY() {
		return y;
	}

	
}
