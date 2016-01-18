package algorithm;

import com.frost.io.Print;

public class DList {
public listnode top;
public listnode tail;
private int num=0;
public DList(){
	top=new listnode(-1);//哨兵，代表为空
	tail=new listnode(-1);
	top.next=tail;
	//tail.pre=top;
}
public void add(int i){//新建值为i的节点插入链表顶
	if(top.data.equals(-1)){
		top.data=i;
		tail.pre=top;
	}else{
//		if((i==154||i==681)&&num==1023){
//			Print.dl("154 CAUTION!!!!!!!!!!!");
//		}
		listnode temp=new listnode(i);
		temp.next=top;
		top.pre=temp;
		top=temp;
	}
	num++;
//	Print.dl("   num is "+num);

}
private listnode find(int i){
	listnode t=top;
	while(t!=tail){
		if(t.data.equals(i)){
			return t;
		}
		t=t.next;
	}
	return tail;
}
public void select(int i){//将值为i的节点放入表顶
	listnode t=find(i);
//	if(t.data.equals(154)){
//		throw new NullPointerException("node.pre = null");
//	}
	if(t==tail){
		Print.dl("no element with value of "+i);
	}else{
		if(t!=top){
			t.next.pre=t.pre;//t后边的指针
			t.pre.next=t.next;//t前边的指针
			//t=null;
			num--;
			add(i);
		}
	}
}
/***
 * 
 * @return 已删除节点的数据
 */
	public int delete() {// ohyes，不进行回收，删除尾节点
//		Print.dl("Delete from list");
		int i = (int) tail.pre.data;
		if(tail.pre.pre==null){			
			Print.dl("tail.pre.pre is NULLLLLLLLLLLLLLLLLLLLLLLL");
		}
		tail.pre.pre.next = tail;
		tail.pre = tail.pre.pre;
		num--;
//		if (num ==1023) {
//			check();
//		}
		return i;
	}
public void check(){
	listnode t=top;
	Print.dl("RUN NULL POINTER CHECK!!!!!!!!!!!!!");
	int i=0;
	while(t!=tail){
		if(t.pre==null&&t!=top){
			Print.d(t.data+"   with times "+i+"   top.data is "+top.data);
		}
		i++;
		t=t.next;
	}
}
public void delete(listnode n) {
	// TODO Auto-generated method stub
//	Print.dl("Delete from list");
	if(n!=top){
		n.next.pre=n.pre;//t后边的指针
		n.pre.next=n.next;//t前边的指针
		//n=null;
		num--;
	}
}
}
