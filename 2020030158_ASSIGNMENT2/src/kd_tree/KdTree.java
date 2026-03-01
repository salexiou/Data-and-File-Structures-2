package kd_tree;

import counters.DepthCounter;

public class KdTree {

	private KdNode root;

	public KdTree() {
		this.root = null;
	}

	public KdNode insertToTree(KdNode root, int x, int y, int level) {
		
		if (root == null) {
			return new KdNode(x, y);
		}

		int compare = level % 2; // check if level % 2 == 0, if so then we compare by x , else by y.
		// check to which side the point must be inserted
		if (compare == 0) {

			if (x < root.getX()) {
				root.left = insertToTree(root.left, x, y, level + 1);
			} else {
				root.right = insertToTree(root.right, x, y, level + 1);
			}
		} else {

			if (y < root.getY()) {
				root.left = insertToTree(root.left, x, y, level + 1);
			} else {
				root.right = insertToTree(root.right, x, y, level + 1);
			}
		}
		return root;
	}

	/* always start from level 0 */
	public KdNode insert(KdNode root, int x, int y) {
		return insertToTree(root, x, y, 0);
	}

	public boolean searchPoint(KdNode root, int x, int y, int level, int counterIndex) {

		DepthCounter.increaseCounter(counterIndex);

		if (root == null)
			return false;

		if (foundPair(root.getX(), root.getY(), x, y))
			return true;

		// check if level % 2 == 0, if so then we compare by x , else by y.
		// check to which side we must go (left/right)
		if (level % 2 == 0) {
			if (x < root.getX())
				return searchPoint(root.left, x, y, level + 1, counterIndex);
			else
				return searchPoint(root.right, x, y, level + 1, counterIndex);
		}

		if (y < root.getY())
			return searchPoint(root.left, x, y, level + 1, counterIndex);

		return searchPoint(root.right, x, y, level + 1, counterIndex);

	}

	/* always start from level 0 */
	public boolean search(KdNode root, int x, int y, int counterIndex) {

		return searchPoint(root, x, y, 0, counterIndex);
	}
	
	/*checking if two points are equal*/
	public boolean foundPair(int x1, int y1, int x2, int y2) {

		if (x1 == x2 && y1 == y2)
			return true;
		return false;
	}

	public KdNode getRoot() {
		return root;
	}

	public void setRoot(KdNode root) {
		this.root = root;
	}
}
