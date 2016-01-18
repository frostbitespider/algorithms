package algorithm;

public class quicksort {
public static int k;
public static void insertSort(int[] a,int p,int q){
	for(int j=p+1;j<=q;j++){
		int key=a[j];
		int i=j-1;
		while(i>=p&&a[i]>key){
			a[i+1]=a[i];
			i--;
		}
		a[i+1]=key;
	}
}
public static int randomPartition(int[] A, int p, int r) {
	// TODO Auto-generated method stub
	int i=RandominN.randint(p, r);
	int temp=A[r];
	A[r]=A[i];
	A[i]=temp;
	return partition(A,p,r);
}
public static int partition(int[] a,int p,int r){
	int x=a[r];
	int i=p-1,temp=0;
	for(int j=p;j<r;j++){
		if(a[j]<=x){
			i++;
			temp=a[i];
			a[i]=a[j];
			a[j]=temp;
		}
	}
	temp=a[i+1];
	a[i+1]=a[r];
	a[r]=temp;
	return i+1;
}
public static void quickSort(int[] a,int p,int r){
	if(r-p>=k){
		int q=partition(a,p,r);
		quickSort(a,p,q-1);
		quickSort(a,q+1,r);
	}
	else
		insertSort(a,p,r);
}
}
