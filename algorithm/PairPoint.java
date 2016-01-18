package algorithm;

public class PairPoint {
public Point i,j;
public PairPoint(Point a,Point b){
	i=a;
	j=b;
}
public PairPoint(){
	i=new Point(999,999);
	j=new Point(-999,-999);
}
public static double dist(Point a,Point b){
	return Math.sqrt(Math.pow(b.x-a.x,2)+Math.pow(b.y-a.y,2));
}
public double getDist(){
	return dist(i,j);
}
public void visit(){
	i.visit();
	j.visit();
}
}
