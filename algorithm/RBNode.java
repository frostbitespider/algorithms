package algorithm;

import java.io.IOException;
import java.io.OutputStreamWriter;

import com.frost.io.Print;

public class RBNode{
public enum RB{RED,BLACK;}
public int key;
public Object data;
public RBNode parent;
public RBNode lc; 
public RBNode rc;
private RB color ;
// 构造函数，枚举类型只能为私有
RBNode(int v,RB c) {
	this.key=v;
    this.color = c;
    lc=null;
    rc=null;
}
RBNode(int v,RB c,Object o) {
	this.key=v;
    this.color = c;
    lc=null;
    rc=null;
    this.data=o;
}
public RB getColor(){
	return this.color;
}
public void setColor(RB c){
	this.color=c;
}
public void visit(){
	String s=new String();
	if(this.getColor()==RB.BLACK)
		s="B";
	else s="R";
	Print.d(this.key+s+" ");
}
//public void printTree(OutputStreamWriter out)throws IOException {
//    if (rc != null) {
//        rc.printTree(out, true, "");
//    }
//    printNodekey(out);
//    if (lc != null) {
//        lc.printTree(out, false, "");
//    }
//}
//private void printNodekey(OutputStreamWriter out) throws IOException {
//    if (key==-1) {
//        out.write("<null>");
//    } else {
//        out.write(key);
//    }
//    out.write('\n');
//}
//// use string and not stringbuffer on purpose as we need to change the indent at each recursion
//private void printTree(OutputStreamWriter out, boolean isRight, String indent) throws IOException {
//    if (rc != null) {
//        rc.printTree(out, true, indent + (isRight ? "        " : " |      "));
//    }
//    out.write(indent);
//    if (isRight) {
//        out.write(" /");
//    } else {
//        out.write(" \\");
//    }
//    out.write("----- ");
//    printNodekey(out);
//    if (lc != null) {
//        lc.printTree(out, false, indent + (isRight ? " |      " : "        "));
//    }
//}
//@Override
//
//public String toString() {
//    return String.keyOf ( this.nCode );
//}
}
