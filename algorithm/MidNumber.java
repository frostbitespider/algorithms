package algorithm;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

import org.junit.Test;

import com.frost.io.Print;

import algorithm.RBNode.RB;
public class MidNumber {
public int getMid(int i,int j,int[] a){
	return a[(i+j)/2];
}
public void getMidFromTwoArray(int[] a,int[] b){
	
}

//@Test
//public void t(){
//	RandominN r=new RandominN();
//	Sort s=new Sort();
//	int n=10;
//	int[] a=r.genKnuth(n, 20);
//	int[] b=r.genKnuth(n, 30);
//	Print.dl(a);
//	Print.dl(b);
//	int[] c=new int[20];
//	for(int k=0;k<10;k++){
//		c[k]=a[k];
//	}
//	for(int k=0;k<10;k++){
//		c[k+10]=b[k];
//	}
//	c=s.bitMapSort(30, c);
//	Print.dl(c);
//	int i=0,j=0;
//	int am=a[0],bm=b[0];
//
//}
}
