package algorithm;
import com.frost.io.Print;
public class Point {
public double x,y;
public Point(double i,double j){
	x=i;y=j;
}
public void visit(){
	Print.d("["+x+","+y+"]");
}
}
