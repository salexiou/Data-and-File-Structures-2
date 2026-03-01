package quad_tree;
 

public class QdNode {
	
	int x ;
	int y ;
	
	QdNode nw;
	QdNode ne;
	QdNode sw;
	QdNode se;		
		
	public QdNode(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.nw = this.ne = this.se = this.sw = null;
	}
}
