package algorithm;

public class MaxHeap {
private int heapSize;
private int MAXL;
private int[] array;
public int right(int i){
	return 2*i+1;
}
public int left(int i){
	return 2*i;
}
public int parent(int i){
	return i/2;
}
public void siftup(int n){
	int i=n;
	while(true){
		if(i==1)
			break;
		int p=parent(i);
		if(array[p]<=array[i])
			break;
		int t=array[p];
		array[p]=array[i];
		array[i]=t;
		i=p;
	}
}
public void siftdown(int n){
	
}
}
