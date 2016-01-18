package algorithm;
import org.junit.Test;

import com.frost.io.Print;
public class indexequalvalue {
public static int[] a={-10,-2,1,2,3,4,5,7,9,10,11};
public static int size=10;
public void preorder(int i){
	if(i>10)
		return;
	else if(a[i]==i){
		Print.dl(a[i]);
		return;
	}
	else if(a[i]>i)
		return;
	else{
		preorder(2*i);
		preorder(2*i+1);
	}
}
@Test
public void test(){
	//preorder(1);
	int[]t={2,2,4,5,6,8,2,1,1,3,1,5,1,7,1,5,7};
	WordCal.mostNumber(t);
}
}
