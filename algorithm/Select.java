package algorithm;

import java.util.Arrays;
import java.util.Random;
public class Select {
public static int minmum(int array[]){
	int min=array[0];
	for(int i=1;i<array.length;i++){
		if(min>array[i]){
			min=array[i];
		}
	}
	return min;
}

public static double minmum(double array[]){
	double min=array[0];
	for(int i=1;i<array.length;i++){
		if(min>array[i]){
			min=array[i];
		}
	}
	return min;
}
public static int minmumIndex(double array[]){
	double min=array[0];
	int a=0;
	for(int i=1;i<array.length;i++){
		if(min>array[i]){
			min=array[i];
			a=i;
		}
	}
	return a;
}
/**
 * 选择数组中第i小的元素
 * @param A
 * @param p
 * @param r
 * @param i
 * @return
 */
public static int randomSelect(int[] A,int p,int r,int i){
//	int[] A=Arrays.copyOf(Array, Array.length);
//	int[] b=Array.clone();
	if(p==r)
		return A[p];
	int q=quicksort.partition(A,p,r);
	int k=q-p+1;
	if(i==k){
		return A[q];
	}
	else if(i<k){
		return randomSelect(A,p,q-1,i);
	}
	else return randomSelect(A,q+1,r,i-k);	
}
//public int randomPartition(int[] A, int p, int r) {
//	// TODO Auto-generated method stub
//	int i=RandominN.randint(p, r);
//	int temp=A[r];
//	A[r]=A[i];
//	A[i]=temp;
//	return quicksort.partition(A,p,r);
//}
}
