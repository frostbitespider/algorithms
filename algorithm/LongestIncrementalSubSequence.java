package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.frost.io.Print;

public class LongestIncrementalSubSequence {
	public static void main(String[] args) {
		int array[] = new int[10];
		System.out.print("Raw Input: ");
		for(int i = 0;i < 10;i++){
			Random random = new Random();
			array[i] = random.nextInt(10);
			System.out.print(array[i] + ", " );
		}
//		System.out.println("\nLongest Distance of O(n2): " 
//				+ GetLongestIncrementalSubSequence__O_N2_(array));
		Print.dl("Longest Distance of O(nlogn):");
		GetLongestIncrementalSubSequence__O_NLogN_(array);
		//Print.dl(GetLongestIncrementalSubSequence__O_NLogN_(array));
	}
	public static void GetLongestIncrementalSubSequence__O_NLogN_(int array[]){
		int n=8;
		int nums[]={0,1,7,8,9,2,3,4,5};
		//数组b存储在递增子序列中当前元素的前一个元素序号
		int b[]=new int[12];
		//vector容器last中存储各不同长度的递增子序列中尾元素最小的序列中最后一个元素的序号
		ArrayList<Integer> last=new ArrayList<Integer>();
		last.add(1);
		for (int i=2;i<=n;i++)
		{
			//使用二分法在last中查找到大于等于当前元素nums[i]的最小元素
			int low=0,high=last.size()-1;
			int middle=(low+high)/2;
			int pos;
			if(nums[i]>nums[last.get(high)])
			{//如果当前元素比last中所有元素都大则最长递增子序列的长度加一，其尾元素为当前元素nums[i]
				last.add(i);
				b[i]=last.get(high);
			}else{
				while(low<high)
				{	
					middle=(high+low)/2;

					if(nums[i]>nums[last.get(middle)])
						low=middle+1;
					else
						high=middle-1;
				}
				//更新last中的元素值
				if(nums[i]>nums[last.get(low)])
					pos=low+1;
				else
					pos=low;
				last.set(pos, i);
				b[i]=last.get(pos-1<0 ? 0 : pos-1);
			}
		}
		int len=last.size();
		Print.dl("原序列长度为");
//		cout<<"原序列长度为"<<n<<"，如下："<<endl;
		for (int i=1;i<=n;i++)
		{
			Print.d(nums[i]);//cout<<nums[i]<<" ";
		}
		Print.dl("最长递增子序列长度为");
		//cout<<endl<<"最长递增子序列长度为"<<len<<"，如下："<<endl;
		//printSequence(b,nums,last[len-1]);
		//cout<<endl;
	}
}
