package algorithm;
import org.junit.Test;

import com.frost.io.Print;
public class Sort {
/**
 * use this if bitmap already exists
 * @param n bound of number
 * @param array	source array
 * @param bitm	bitmap
 */
 
// 你的对象要实现Compare接口
//1
//2
//3
//4
//5
//6
//7
//8
//9
//10
//11
//12
//13
//14
//class MyBean implements Comparable<MyBean> {
//    private Date sortKey;
//     
//    public Date getSortKey() {
//        return sortKey;
//    }
//    public void setSortKey(Date sortKey) {
//        this.sortKey = sortKey;
//    }
//    @Override
//    public int compareTo(MyBean o) {
//        return this.sortKey.compareTo(o.getSortKey());
//    }
//}
//然后直接调用Collections.sort(list);
//1
//2
//3
//4
//public static void main(String[] args) throws ParseException {
//    List<MyBean> list = new ArrayList<MyBean>();
//    Collections.sort(list);
//}
public static void bitMapSort1(int n,int[] array,int[] bitm){
	int len=array.length;
	int k=0;
	for(int i=0;i<n;i++){
		if(bitm[i]>0){
			for(int j=0;j<bitm[i];j++)
				//Print.d(i+"	");
				array[k++]=i;
		}
	}
}
public static int[] getBitMap(int n,int[] array){
	int[] bitmap=new int[n];
	int len=array.length;
	int i=0;
	for(;i<len;i++){
		bitmap[array[i]]+=1;
	}
	return bitmap;
}
public static void getBitMap(int n,int[] array,int[] bitmap){
	int len=array.length;
	int i=0;
	for(;i<len;i++){
		int t=array[i];
		bitmap[array[i]]+=1;
	}
}
public static void getBitMap(int n,char[] array,int[] bitmap){
	int len=array.length;
	int i=0;
	for(;i<len;i++){
		int t=array[i];
		bitmap[array[i]]+=1;
	}
}
public static void insertSort(int[] array,int p,int q){
	for(int j=p+1;j<=q;j++){
		int key=array[j];
		int i=j-1;
		while(i>=p&&array[i]>key){
			array[i+1]=array[i];
			i--;
		}
		array[i+1]=key;
	}
}
/**
 * sort by bitmap,T(n)=O(n),no error checking
 * 
 * @param n	is the bound of number
 * @param array stores source numbers range from 0 to n
 * @return bitmap
 */
public static int[] bitMapSort(int n,int[] array){
	int len=array.length;
	int[] bitmap =new int[n];
	int i=0;
	
	for(;i<len;i++){
		bitmap[array[i]]+=1;
	}
	int k=0;
	for(i=0;i<n;i++){
		if(bitmap[i]>0){
			for(int j=0;j<bitmap[i];j++)
				//Print.d(i+"	");
				array[k++]=i;
		}
	}
	return bitmap;
}
//public static Object[] kvSort(Object[] vaules,final int[]  keys){
//	return vaules;
//}
}

