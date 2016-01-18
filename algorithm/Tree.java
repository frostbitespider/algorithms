package algorithm;

public class Tree {
public TreeNode root;
public void preOrder(TreeNode t) {
	if(t!=null){
		t.visit();
		preOrder(t.lc);
		preOrder(t.rc);
	}
}
public void inOrder(TreeNode t) {
	if(t!=null){
		preOrder(t.lc);
		t.visit();
		preOrder(t.rc);
	}
}
public void postOrder(TreeNode t) {
	if(t!=null){
		preOrder(t.lc);
		preOrder(t.rc);
		t.visit();
	}
}

}
