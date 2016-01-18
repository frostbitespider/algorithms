package algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.frost.io.Print;
public class Banner {
private static char[] c1;
private static char[] c2;
private static char[] c3;
private static char[] c4;
private static char[] c5;
private static char[][] sc=new char[5][];

public void init()
{
	char[] c = null;
	String FILE_IN="D:\\a.txt";
    File file=new File(FILE_IN);
    try {
    	BufferedReader reader = null;
    	reader = new BufferedReader(new FileReader(file));

//        int ch = 0;
//        while((ch = fr.read())!=-1 )
//        {
//        	if(ch)
//        }
        int i=0;
        String temp=new String();
        while((temp = reader.readLine()) != null)
        {
        	sc[i]=temp.toCharArray();
        }
    }catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        System.out.println("File reader error");
    }
}
@Test
public static void Banner(char s){
int posC=s-'a';
int reader=0;
int line=0;
for(int j=0;j<posC;j++){
	while(true){
		char temp=sc[line][reader];
	if(true){}
}
}
}
}
