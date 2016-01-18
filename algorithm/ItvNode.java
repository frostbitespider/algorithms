package algorithm;

import com.frost.io.Print;

import algorithm.RBNode.RB;

/**
 * 区间树的节点
 * @author frostbitespider
 *
 */
public class ItvNode extends RBNode{
	public Itv itv;
	/**
	 * 属性为x.itv，关键字key为x.itv.low，红黑节点，data域存储max
	 * @param v
	 * @param c
	 * @param i
	 */
	ItvNode(int v, RB c,Itv i) {
		super(i.low, c);
		this.itv=i;
		this.data=i.high;
		// TODO Auto-generated constructor stub
	}
	/**
	 * 尽在节点相对位置发生变化时调用
	 */

	public int getMax(){
		return (int)this.data;
	}

	@Override
	public void visit(){
		String s=new String();
		if(this.getColor()==RB.BLACK)
			s="B";
		else s="R";
		Print.d(this.itv.toString()+s+" max="+this.data+"  ");
	}
}
