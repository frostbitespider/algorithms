package algorithm;
import com.frost.io.Print;
public class editDistance {

/**
 * 
 * @param x
 * @param y
 * @param table
 * cpy	rep	del	ins	twi	kil
 * 1	2	3	4	5	6	
 */
public static void get(char[] x,char[] y,int[][] table,int [][]act){
	for(int i=0;i<x.length-1;i++){
		for(int j=0;j<y.length-1;j++){
			if(x[i]==y[j]){//cpy
				table[i][j]=1;
				act[i][j]=1;
			}
			else{
				if(x[i]==y[j+1]&&x[i+1]==y[j]){//twi
					table[i][j]=5;
					act[i][j]=4;
				}
				else if(x[i+1]==y[j]){//del
					table[i][j]=3;
					act[i][j]=2;
				}
				else if(x[i]==y[j+1]){//ins
					table[i][j]=4;
					act[i][j]=3;
				}
				else //rep
				{
					table[i][j]=2;
					act[i][j]=2;
				}
			}	
		}
	}
	//column	when j=y.len-1
	for(int i=0;i<x.length;i++){
		int j=y.length-1;
		if(x[i]==y[j]){//cpy
			table[i][j]=1;
			act[i][j]=1;
		}
		else{//ins
			table[i][j]=4;
			act[i][j]=3;
		}
	}
	for(int i=0;i<x.length;i++){//y is complete kil
		table[i][y.length]=6;
		act[i][y.length]=5;
	}
	//row	when i=x.len-1
	for(int j=0;j<y.length;j++){
		int i=x.length-1;
		if(x[i]==y[j]){//cpy
			table[i][j]=1;
			act[i][j]=1;
		}
		else{//ins
			table[i][j]=4;
			act[i][j]=3;
		}
	}
	for(int i=0;i<20;i++){
		Print.dl(table[i]);
	}
	Print.dl();
	for(int i=0;i<20;i++){
		Print.dl(act[i]);
	}
	int a=0,b=0;
	Print.dl();
	while(a<act.length&&b<act.length){
		int v=act[a][b];
		Print.d(table[a][b]);
		switch(v){
		case 1:
			a++;
			b++;
			break;
		case 2:
			a++;
			break;
		case 3:
			b++;
			break;
		case 4:
			a+=2;
			b+=2;
			break;
		case 5:
			a=x.length;
			break;
		default:
			return;
		}

	}
}

}
