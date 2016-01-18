package algorithm;
import com.frost.io.Print;
public class listnode {
public Object data;
public listnode next;
public listnode pre;
public listnode(){
	data=-1;
	next=null;
	pre=null;
}
public listnode(Object i){
	data=i;
	next=null;
	pre=null;
}
public void visitAll(){
	listnode p=this;
	while(p!=null){
		Print.d(p.data+" ");
		p=p.next;
	}
}
public void visit(){
	if(this!=null){
		Print.d(data+" ");
	}
}
}
