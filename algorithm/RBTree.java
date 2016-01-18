package algorithm;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

import org.junit.Test;

import com.frost.io.Print;

import algorithm.RBNode.RB;
public class RBTree{
public RBNode root; 
public RBNode nil;
public RBTree(){
	nil=new RBNode(-1,RB.BLACK);
	nil.parent=nil;
	nil.lc=nil;
	nil.rc=nil;
	root=nil;
}
public void insert(RBNode n){
	RBNode x=root,y=nil;
	while(x!=nil){
		y=x;
		if((int)n.key<(int)x.key)
			x=x.lc;
		else x=x.rc;
	}
	n.parent=y;
	if(y==nil)
		root=n;
	else if((int)n.key<(int)y.key)
		y.lc=n;
	else y.rc=n;
	n.lc=nil;
	n.rc=nil;
	n.setColor(RB.RED);
	insertFixup(n);
}
public void inOrder(RBNode t) {
	if(t!=nil){
		inOrder(t.lc);
		t.visit();
		inOrder(t.rc);
	}
}

public void insertFixup(RBNode n){
	while(n.parent.getColor()==RB.RED){
		if(n.parent==n.parent.parent.lc){
			RBNode y=n.parent.parent.rc;
			if(y.getColor()==RB.RED){//叔节点和父节点皆为红色，可直接将两者着黑，将祖父着红，上溯2层
				n.parent.setColor(RB.BLACK);
				y.setColor(RB.BLACK);
				n.parent.parent.setColor(RB.RED);
				n=n.parent.parent;
			}
			else if(n==n.parent.rc){
				n=n.parent;
				leftRotate(n);
			}
			n.parent.setColor(RB.BLACK);
			if(n.parent.parent!=nil){
				n.parent.parent.setColor(RB.RED);
				rightRotate(n.parent.parent);
			}
		}
		else{
			RBNode y=n.parent.parent.lc;
			if(y.getColor()==RB.RED){
				n.parent.setColor(RB.BLACK);
				y.setColor(RB.BLACK);
				n.parent.parent.setColor(RB.RED);
				n=n.parent.parent;
			}
			else if(n==n.parent.lc){
				n=n.parent;
				rightRotate(n);
			}
			n.parent.setColor(RB.BLACK);
			if(n.parent.parent!=nil){
				n.parent.parent.setColor(RB.RED);
				leftRotate(n.parent.parent);
			}
		}
	}
	root.setColor(RB.BLACK);
}
public void leftRotate(RBNode x){
	RBNode y= x.rc;
	if(y!=nil){
		x.rc=y.lc;
		if(y.lc!=nil)
			y.lc.parent=x;
		y.parent=x.parent;
		if(x.parent==nil)
			root=y;
		else if(x.parent.lc==x)
			x.parent.lc=y;
		else x.parent.rc=y;
		y.lc=x;
		x.parent=y;
	}
}
public void rightRotate(RBNode y){
	RBNode x=y.lc;
	if(x!=nil){
		y.lc=x.rc;
		if(x.rc!=nil)
			x.rc.parent=y;
		x.parent=y.parent;
		if(y.parent==nil)
			root=x;
		else if(y.parent.lc==y)
			y.parent.lc=x;
		else y.parent.rc=x;
		x.rc=y;
		y.parent=x;
	}
}
public void transplant(RBNode u,RBNode v){
	if(u.parent==nil)
		root=v;
	else if(u==u.parent.lc)
		u.parent.lc=v;
	else u.parent.rc=v;
	v.parent=u.parent;
}
public RBNode getMin(RBNode n){
	while(n.lc!=nil)
		n=n.lc;
	return n;
}
public void deleteFixup(RBNode x){
	while(x!=root&&x.getColor()==RB.BLACK){
		if(x==x.parent.lc){
			RBNode w=x.parent.rc;
			if(w.getColor()==RB.RED){
				w.setColor(RB.BLACK);
				x.parent.setColor(RB.RED);
				leftRotate(x.parent);
				w=x.parent.rc;
			}
			if(w.lc.getColor()==RB.BLACK&&w.rc.getColor()==RB.BLACK){
				w.setColor(RB.RED);
				x=x.parent;
			}
			else if(w.rc.getColor()==RB.BLACK){
				w.lc.setColor(RB.BLACK);
				w.setColor(RB.RED);
				rightRotate(w);
				w=x.parent.rc;
			}
			w.setColor(x.parent.getColor());
			x.parent.setColor(RB.BLACK);
			w.rc.setColor(RB.BLACK);
			leftRotate(x.parent);
			x=root;
		}
		else{
			RBNode w=x.parent.lc;
			if(w.getColor()==RB.RED){
				w.setColor(RB.BLACK);
				x.parent.setColor(RB.RED);
				leftRotate(x.parent);
				w=x.parent.lc;
			}
			if(w.rc.getColor()==RB.BLACK&&w.lc.getColor()==RB.BLACK){
				w.setColor(RB.RED);
				x=x.parent;
			}
			else if(w.lc.getColor()==RB.BLACK){
				w.rc.setColor(RB.BLACK);
				w.setColor(RB.RED);
				rightRotate(w);
				w=x.parent.lc;
			}
			w.setColor(x.parent.getColor());
			x.parent.setColor(RB.BLACK);
			w.lc.setColor(RB.BLACK);
			leftRotate(x.parent);
			x=root;
		}
	}
	x.setColor(RB.BLACK);
}
public void delete(RBNode n){
	RBNode y=n;
	RB originColor=y.getColor();
	if(n.lc==nil){
		RBNode x=n.rc;
		transplant(n,n.rc);
	}
	else if(n.rc==nil){
		RBNode x=n.lc;
		transplant(n,n.lc);
	}
	else{
		y=getMin(n.rc);
		originColor=y.getColor();
		RBNode x=y.rc;
		if(y.parent==n)
			x.parent=y;
		else{
			transplant(y,y.rc);
			y.rc=n.rc;
			y.rc.parent=y;
		}
		transplant(n,y);
		y.lc=n.lc;
		y.lc.parent=y;
		y.setColor(n.getColor());
		if(originColor==RB.BLACK)
			deleteFixup(x);
	}
	
}

//public void show(RBNode node, int level, char disp_tab[]) {
//		int i;
//		if (level < 127) {
//			for (i = 1; i < level; ++i) {
//				Print.d(disp_tab[i] != ' ' ? "| " : "  ");
//			}
//			if (level != 0) {
//				Print.d("+-");
//			}
//			if (root == null) {
//				Print.dl();
//			} else {
//				Print.dl((int) node.key);
//				if (node.lc != null || node.rc != null) {
//					disp_tab[level + 1] = 1;
//					show(node.rc, level + 1, disp_tab);
//					disp_tab[level + 1] = 0;
//					show(node.lc, level + 1, disp_tab);
//				}
//			}
//		}
//	}

}