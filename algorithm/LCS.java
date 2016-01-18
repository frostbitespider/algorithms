package algorithm;

import java.util.Arrays;

import com.frost.io.Print;

public class LCS {
private short[][] b;
public void getLength2(char[] X,char[] Y){
	int m=X.length;
	int n=Y.length;
	b=new short[m][n];
	int[] c1=new int[n+1];
	int[] c2=new int[n+1];
//	for(int i=0;i<m+1;i++)
//		c1[i][0]=0;
	int r=0;
	//c\r	0	1
	//c1	上	下
	//c2	下	上
	for(int i=0;i<m;i++,r++){
		if(r%2==0){
			c2=Arrays.copyOf(c1, n+1);//c[i+1]为c2		c[i]为c1
			for(int j=0;j<n;j++){
				if(X[i]==Y[j]){
					c2[j+1]=c1[j]+1;
					b[i][j]=1;
				}
				else if(c1[j+1]>=c2[j]){
					c2[j+1]=c1[j+1];
					b[i][j]=2;
				}else{
					c2[j+1]=c2[j];
					b[i][j]=3;
				}
			}
		}else{
			c1=Arrays.copyOf(c2, n+1);//c[i+1]为c1		c[i]为c2
			for(int j=0;j<n;j++){
				if(X[i]==Y[j]){
					c1[j+1]=c2[j]+1;
					b[i][j]=1;
				}
				else if(c2[j+1]>=c1[j]){
					c1[j+1]=c2[j+1];
					b[i][j]=2;
				}else{
					c1[j+1]=c1[j];
					b[i][j]=3;
				}
			}
		}
	}
}
public int[][] getLength(char[] X,char[] Y){
	int m=X.length;
	int n=Y.length;
	b=new short[m][n];
	int[][] c=new int[m+1][n+1];
	for(int i=0;i<m;i++)
		c[i][0]=0;
	for(int j=0;j<n;j++)
		c[0][j]=0;
	for(int i=0;i<m;i++){
		for(int j=0;j<n;j++){
			if(X[i]==Y[j]){
				c[i+1][j+1]=c[i][j]+1;
				b[i][j]=1;
			}
			else if(c[i][j+1]>=c[i+1][j]){
				c[i+1][j+1]=c[i][j+1];
				b[i][j]=2;
			}
			else{
				c[i+1][j+1]=c[i+1][j];
				b[i][j]=3;
			}
		}
	}
	return c;
}
public void print(char[] X,int m,int n){
	Print.dl("B is");
	for(int i=0;i<b.length;i++){
		System.out.println(Arrays.toString(b[i]));
	}
	char[] rtemp=new char[m<n?m:n];//倒序结果
	int count=0;
	for(int i=m-1,j=n-1;i>=0&&j>=0;){
		if(b[i][j]==1){
			Print.d(X[i]);
			rtemp[count++]=X[i];
			i--;j--;
		}
		else if(b[i][j]==2){
			i--;
		}
		else j--;
	}
	//reverse
	char[] arr=Arrays.copyOf(rtemp, count);
	char t=0;
	int k=0;
	do{
		t=arr[k];
		arr[k]=arr[count-1-k];
		arr[count-1-k]=t;
		k++;
	}while(k<count-1-k);
	Print.dl("one of LCS is");
	System.out.println(Arrays.toString(arr));
}

}
