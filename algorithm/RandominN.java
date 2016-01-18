package algorithm;
import java.util.*;

import org.junit.Test;

import com.frost.io.Print;
public class RandominN {
public static Random r=new Random();
/**generate total k nature numbers range from 0 to n，有重复
 * 
 * @param k total number to be generated
 * @param n	max range
 */
public static int[] SimpleGenerate(int k,int n){
	int[] a=new int[k];
	for(int i=0;i<k;i++){
		a[i]=r.nextInt(n);
	}
	return a;
}
public static int bigrand(){
	return r.nextInt(65535);
}
public static int randint(int l,int u){
	return bigrand()%(u-l)+l;
}
public static int bigerrand(){
	return bigrand()>>16+bigrand();
}
public static int[] genKnuth(int k,int n){
	int[] a=new int[k];
	for(int i=0,j=0;i<n;i++){
		if((bigrand()%(n-i))<k){
			//Print.dl(i);
			a[j++]=i;
			k--;
		}
	}
	return a;
}
public void genshuf(int m,int n){
	int i,j;
	int[] x=new int[n];
	for(i=0;i<n;i++)
		x[i]=i;
	for(i=0;i<m;i++){
		j=randint(i,n-1);
		int t=x[i];x[i]=x[j];x[j]=t;
	}
	//sort(x,x+m);
	for(i=0;i<m;i++){
		Print.d(x[i]+" ");
	}
}

}
