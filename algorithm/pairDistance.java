package algorithm;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.frost.io.Print;
public class pairDistance {
public static double dist(Point a,Point b){
	return Math.sqrt(Math.pow(b.x-a.x,2)+Math.pow(b.y-a.y,2));
}
public boolean isin(Object o,Object a[]){
	boolean flag=false;
	for(int i=0;i<a.length;i++){
		if(o==a[i]){
			flag=true;
			break;
		}
	}
	return flag;
}
public Point[] preSortX(final Point[] ps){
	Point[] t=new Point[ps.length];
	for(int j=0;j<ps.length;j++){
		t[j]=ps[j];
	}
	for(int j=1;j<t.length;j++){
		double key=t[j].x;
		Point temp=t[j];
		int i=j-1;
		while(i>=0&&t[i].x>key){
			t[i+1]=t[i];
			i--;
		}
		t[i+1]=temp;
	}
	return t;
}
public Point[] preSortY(final Point[] ps){
	Point[] t=new Point[ps.length];
	for(int j=0;j<ps.length;j++){
		t[j]=ps[j];
	}
	for(int j=1;j<t.length;j++){
		double key=t[j].y;
		Point temp=t[j];
		int i=j-1;
		while(i>=0&&t[i].y>key){
			t[i+1]=t[i];
			i--;
		}
		t[i+1]=temp;
	}
	return t;
	
}

public PairPoint gen(Point[] ps,ArrayList<Point> X,ArrayList<Point> Y){
	if(ps.length<=3){
		if(ps.length==3){
			double t[]={dist(ps[0],ps[1]),dist(ps[0],ps[2]),dist(ps[2],ps[1])};
			int i=Select.minmumIndex(t);
			if(i==0){
				return new PairPoint(ps[0],ps[1]);
			}
			else if(i==1){
				return new PairPoint(ps[0],ps[2]);
			}
			else{
				return new PairPoint(ps[2],ps[1]);
			}
		}
		else if(ps.length==2) return new PairPoint(ps[0],ps[1]);
		else return new PairPoint();
	}
	else{
		Iterator<Point> itx=X.iterator();
		int count=0;
		while(count++!=X.size()/2){
			itx.next();
		}
		double mid=itx.next().x;
		Point plt[]=new Point[ps.length];
		count=0;
		for(int i=0;i<ps.length;i++){
			if(ps[i].x<mid)//不能是<=
				plt[count++]=ps[i];
		}
		Point pl[]=Arrays.copyOf(plt, count);
		count=0;
		Point prt[]=new Point[ps.length];
		for(int i=0;i<ps.length;i++){
			if(ps[i].x>mid)
				prt[count++]=ps[i];
		}
		Point pr[]=Arrays.copyOf(prt, count);
		ArrayList<Point> yl = new ArrayList<Point>();;
		ArrayList<Point> yr = new ArrayList<Point>();;
		for(Iterator<Point> i=Y.iterator();i.hasNext();){
			Point p=i.next();
			if(isin(p,pl)){
				yl.add(p);
			}
			else
				yr.add(p);
		}
		ArrayList<Point> xl = new ArrayList<Point>();;
		ArrayList<Point> xr = new ArrayList<Point>();;
		for(Iterator<Point> i=X.iterator();i.hasNext();){
			Point p=i.next();
			if(isin(p,pl)){
				xl.add(p);
			}
			else
				xr.add(p);
		}

		Point[] ypt=new Point[X.size()];
		int ypcount=0;
		double mint=0;//最小点对的距离
		PairPoint pp=new PairPoint();
		PairPoint ppl=gen(pl,xl,yl);//左边的结果
		PairPoint ppr=gen(pr,xr,yr);//右边的结果
		if(ppl.getDist()<ppr.getDist()){
			mint=ppl.getDist();
			pp=ppl;
		}else{
			mint=ppr.getDist();
			pp=ppr;
		}
		for(Iterator<Point> i=X.iterator();i.hasNext();){
			Point p=i.next();
			if(Math.abs(p.x-mid)<=mint){
				ypt[ypcount++]=p;
			}
		}
		Point[] yp=Arrays.copyOf(ypt, ypcount);
		for(int i=0;i<yp.length-8;i++){
			double temp=0;
				for(int j=i+1;j<i+8;j++){
				temp=dist(yp[i],yp[j]);
				if(temp<mint){
					mint=temp;
					pp.i=yp[i];
					pp.j=yp[j];
				}
			}
		}
		return pp;
	}


}
}
