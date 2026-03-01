package quad_tree;

import counters.DepthCounter;
import tests.Testings;

public class QuadTree {

	QdNode root;

	public QuadTree() {
		this.root = null;
	}

	public QdNode insertToTree(QdNode root, int x, int y, int level, int x1, int x2, int y1, int y2) {

		if (root == null) 
			return new QdNode(x, y); 
		
		int midX = (x1 + x2) / 2;

		int midY = (y1 + y2) / 2;

		// check to which "square" the point must be inserted

		if (x <= midX) {
			if (y <= midY) {
				root.nw = insertToTree(root.nw, x, y, level + 1, x1, midX, y1, midY);
			} else {
				root.sw = insertToTree(root.sw, x, y, level + 1, x1, midX, midY + 1, y2);
			}
		} else {
			if (y <= midY) {
				root.ne = insertToTree(root.ne, x, y, level + 1, midX + 1, x2, y1, midY);
			} else {
				root.se = insertToTree(root.se, x, y, level + 1, midX + 1, x2, midY + 1, y2);
			}
		}
		return root;
	}
	
	/* always start from level 0 */
	public QdNode insert(QdNode root, int x, int y) {
		return insertToTree(root, x, y, 0, 0, Testings.N, 0, Testings.N);
	}

	public boolean searchPoint(QdNode root, int x, int y, int level, int x1, int x2, int y1, int y2,int counterIndex) {

	    DepthCounter.increaseCounter(counterIndex);
	    
	    if (root == null)
	        return false;

	    if (foundPair(root.x, root.y, x, y))
	        return true;

	    // find the middle Point
	    int midX = (x1 + x2) / 2;
	    int midY = (y1 + y2) / 2;
	    
	    // check to which "square" we must go each time
	    
	    if (x <= midX && y <= midY) 
	        return searchPoint(root.nw, x, y, level+1, x1, midX, y1, midY, counterIndex);
	    
	    else if (x <= midX && y > midY) 
	        return searchPoint(root.sw, x, y, level+1, x1, midX, midY+1, y2, counterIndex);
	    
	    else if (x > midX && y <= midY) 
	        return searchPoint(root.ne, x, y, level+1, midX+1, x2, y1, midY, counterIndex);
	    
	    else 
	        return searchPoint(root.se, x, y, level+1, midX+1, x2, midY+1, y2, counterIndex);
	    
	}

	/* always start from level 0 */
	public boolean search(QdNode root, int x, int y, int counterIndex) {

		return searchPoint(root, x, y, 0, 0, Testings.N, 0, Testings.N,counterIndex);
	}
	
	/* check if two points are equal */
	public boolean foundPair(int x1, int y1, int x2, int y2) {

		if (x1 == x2 && y1 == y2)
			return true;
		return false;
	}

	public QdNode getRoot() {
		return root;
	}

	public void setRoot(QdNode root) {
		this.root = root;
	}

}
