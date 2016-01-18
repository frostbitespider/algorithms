package algorithm;

public class Reverse {
/**
 * reverse 0...k and k...n to k...n and 0...k
 * @param k
 * @param a int[] array
 */
public static void ReverseK(int k,int[] a){
	int n=a.length;
	reverse(0,k-1,a);
	reverse(k,n-1,a);
	reverse(0,n-1,a);
}
public static void reverse(int i,int j,int[] a){
	int temp=0,k=0;
	do{
		temp=a[i+k];
		a[i+k]=a[j-k];
		a[j-k]=temp;
		k++;
	}while(i+k<j-k);
}

}
