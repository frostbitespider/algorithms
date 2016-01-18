package algorithm;

import com.frost.io.Print;

import algorithm.RBNode.RB;

//区间树上的重叠区间查找算法；
public class ItvTree extends RBTree{
public ItvTree(){
	nil=new ItvNode(-1,RB.BLACK,new Itv(-1,-1));
	nil.parent=nil;
	nil.lc=nil;
	nil.rc=nil;
	root=nil;
}
public boolean overlap(Itv x,Itv y){
	if(x.low<y.high&&y.low<x.high)
		return true;
	else return false;
}
@Override
public void insert(RBNode x){
	super.insert(x);
}
@Override
public void delete(RBNode x){
	super.delete(x);
}
public void updateMax(ItvNode x){
	x.data=max(x.itv.high,x.lc.data,x.rc.data);
}
public int max(int o1,Object o2,Object o3){
	if((int)o2<(int)o3)return o1>(int)o3?o1:(int)o3;
	else return o1>(int)o2?o1:(int)o2;
}
@Override
public void leftRotate(RBNode x){
	Print.dl("ItvTree LeftRotate"+x.key);
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
		updateMax((ItvNode)x);
		updateMax((ItvNode)y);
	}
}
@Override
public void rightRotate(RBNode y){
	Print.dl("ItvTree RightRotate"+y.key);
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
		updateMax((ItvNode)x);
		updateMax((ItvNode)y);
	}
}
public ItvNode search(Itv i){
	ItvNode x=(ItvNode) this.root;
	
	while(x!=(ItvNode)this.nil&&!overlap(x.itv,i)){
		if(x.lc!=nil&&((ItvNode)x.lc).getMax()>=i.low)
			x=(ItvNode)x.lc;
		else x=(ItvNode)x.rc;
	}
	return x;
}
}
