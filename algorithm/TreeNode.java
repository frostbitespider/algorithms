package algorithm;
import com.frost.io.Print;
public class TreeNode {
public int key;
public Object data;
public boolean flag;
public TreeNode lc;
public TreeNode rc;
public TreeNode par;
public void visit(){
	Print.d(key+"  ");
}
public TreeNode(){
	key=0;
	flag=false;
	data=null;
	lc=null;
	rc=null;
	par=null;
}
public TreeNode(int k){
	key=k;
	flag=false;
	data=null;
	lc=null;
	rc=null;
	par=null;
}
public TreeNode(int k,Object o){
	key=k;
	flag=false;
	data=o;
	lc=null;
	rc=null;
	par=null;
}
@Override
public String toString(){
	return ""+this.key+" "+this.data;
}
}
