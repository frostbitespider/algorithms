package algorithm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import com.frost.io.Print;
import com.frost.io.FileIO;
import algorithm.RBNode.RB;

public class testcase {
//	@Test
	public void test(){
		int testTimes=5;
		long t1,t2,sum = 0,during = 0;
		for(int k=1;k<50;k++){
			Print.dl("Test with k="+k);
			for(int i=0;i<testTimes;i++){
				//Print.dl("Round"+(i+1));
				quicksort.k=k;
				int[] array=RandominN.SimpleGenerate(100000, 500000);//generate k random number in range of 0 to n  (k,n)
				t1=System.currentTimeMillis();
				quicksort.quickSort(array,0,array.length-1);
				t2=System.currentTimeMillis();
				during=t2-t1;
				//quicksort.insertSort(array, 3, 10);
				//-2,-1,2,5,7,8
				//Print.dl(array);
				sum+=during;
				//Print.dl("Execution time is "+during+"ms");
			}
			sum/=10;
			Print.dl("avarage time is "+sum);
		}
	}
//	@Test
	public void t() {
		ItvTree t = new ItvTree();
		Random r = new Random();
		for (int i = 0; i < 20; i++) {
			int r1=RandominN.randint(0, 30);
			int r2=RandominN.randint(r1, 40);
			Itv itv=new Itv(r1,r2);
			ItvNode x = new ItvNode(0, RB.RED, itv);
			Print.dl("Insert node with key = "+r1);
			t.insert(x);
		}
		Print.dl("Success");
		t.inOrder((ItvNode)t.root);
		Print.dl();
		int r1=RandominN.randint(0, 10);
		int r2=RandominN.randint(r1+1, 15);
		Itv i=new Itv(r1,r2);
		Print.dl("Search itv "+i.toString());
		ItvNode n=t.search(i);
		if(n!=t.nil){
			Print.dl("Search Success");
			n.visit();
		}
		else Print.dl("No Itv found");
	}
//	@Test
	public void tPoint(){
//		Random r = new Random();
		pairDistance test=new pairDistance();
		Point ps[]=new Point[30];
//		int s[]=new int[30];
		for (int i = 0; i < 30; i++) {
			int r1=RandominN.randint(0, 50);
			int r2=RandominN.randint(0, 50);
			ps[i]=new Point(r1,r2);
		}
		Print.dl("Ps");
		for (int i = 0; i < 30; i++) {
			ps[i].visit();
		}
		Print.dl();
		Point px[]=test.preSortX(ps);
		Point py[]=test.preSortY(ps);
		ArrayList<Point> pxx=new ArrayList<Point>();
		for(int i=0;i<px.length;i++)
			pxx.add(px[i]);
		ArrayList<Point> pyy=new ArrayList<Point>();
		for(int i=0;i<py.length;i++)
			pyy.add(px[i]);
		PairPoint minPair=test.gen(ps, pxx, pyy);
		Print.dl();
		Print.d("min dist is "+ minPair.getDist()+" Points is ");
		minPair.visit();
	}
//@Test
public void tED(){
	char[] x={'a','l','g','o','r','i','t','h','m'};
//	for (int i = 0; i < 20; i++) 
//		x[i]=(char) RandominN.randint('a','z');
	Print.dl(Arrays.toString(x));
//	char[] y=new char[25];
	char[] y={'a','l','t','r','u','i','s','t','i','c'};
//	for (int j = 0; j < 25; j++) 
//		y[j]=(char) RandominN.randint('a','z');
	Print.dl(Arrays.toString(y));
	int[][] table=new int[20][25];
	int[][] act=new int[20][25];
	editDistance.get(x, y, table,act);
	
}
//@Test
public void tLCS(){
	 String file="D:\\test.txt";
	 String l1 = null,l2=null;
		try {
			FileIO f = new FileIO(file, false);
			l1 = f.ReadbyLine();
			l2 = f.ReadbyLine();
			f.release();
			Print.dl("L1 is " + l1);
			Print.dl("L2 is " + l2);
			int m = l1.length();
			int n = l2.length();
			short[][] b = new short[m][n];
			int[][] c = null;
			LCS l = new LCS();
			l.getLength2(l1.toCharArray(), l2.toCharArray());
			l.print(l1.toCharArray(), l1.length(), l2.length());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
@Test
public void Huffman(){
	String in="Assets/test.txt";
	String out="Assets/zipfile.txt";
	String unc="Assets/unzip.txt";
	Huffman f=new Huffman();
	f.compress(in, out,null);
	f.uncompress(out, unc, null);
}
}
