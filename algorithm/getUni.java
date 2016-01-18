package algorithm;
import java.util.*;

import org.junit.Test;

public class getUni {
public static boolean fronthassame(int k,int[] a)//在k处向前遍历，看是否有重复的
{
	for(int i=k-1;i>0;i--)
	{
		if(a[i]==a[k])
			return true;
	}
	return false;
}
@Test
public static void getUni(int[] a){
	int p1=0,p2=1,i=2,k=2;//p1,p2表示当前认为的独一无二的两个数，i进行遍历，k表示最靠近p1,p2的可能独一无二数
	int count=0;
	if(a[p1]==a[p2]){//仅有在开始的时候两数可能相等
		p1+=2;
		p2+=2;
		i=4;
	}
	if(a[p1]!=a[i])//进行特殊处理，简化循环体代码
		k=2;
	else k=3;
	boolean flag=true;//只有前一次遍历失败，这一次遍历成功时更新k
	while(i<10){
		if(a[p1]==a[i]){
			p1=k;
			i=k+1;//从k+1处重新遍历，造成T(n)>O(n)
			flag=false;
		}
		else if(a[p2]==a[i]){
			p2=k;
			i=k+1;
			flag=false;
		}
		else{
			if(!flag){
				if(!fronthassame(i,a)){//更新k还需要满足前面没有i的重复
					k=i;
					flag=true;
				}
			}
			i++;
		}
		count++;
	}
	System.out.println("Count="+count);
	System.out.println("Uniq1= "+a[p1]+" in "+p1+"  Uniq2="+a[p2]+" in "+p2);
}

//public static void main(String args[]){
//	Scanner in=new Scanner(System.in);
//	System.out.println("Input Numbers");
//	int[] a=new int[10];
//	for(int i=0;i<10;i++)
//		a[i]=in.nextInt();
//	System.out.println("Get Unique 2 in 10 numbers");
//	getUni(a);
//}
}
