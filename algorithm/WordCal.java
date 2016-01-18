package algorithm;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import org.junit.Test;

import com.frost.io.Print;


public class WordCal {
@Test
public void word(){
	Map<String,Integer> map=new HashMap<String,Integer>();
	
	String s=new String();
	char[] c = null;
	String FILE_IN="D:\\crebas.sql";
	StringBuffer str=new StringBuffer("");
    File file=new File(FILE_IN);
    try {
        FileReader fr=new FileReader(file);
        int ch = 0;
        while((ch = fr.read())!=-1 )
        {
        	
        	if((char)ch!=' '&&(char)ch!='\r'&&(char)ch!='\n'&&(char)ch!='\f'&&(char)ch!='\b')
        		s+=(char)ch;
            //System.out.print((char)ch); 
        	else{
        		//s=null;
        		Integer value = map.get(s);
        		int count=1;
        		if(value!=null){
        		count = value+1;
        		}
        		map.put(s, count);
        		s=new String();
        	}
        }
        fr.close();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        System.out.println("File reader error");
    }
    Iterator<Entry<String, Integer>> it=map.entrySet().iterator();
    while(it.hasNext()){
    	Entry en = it.next();
    	String st= (String) en.getKey();
    	//st.replace(" ", "FUCKYOU,BUGGGGGGGGGGGGGGGGGG");
			//Print.dl("FUCKYOU,BUGGGGGGGGGGGGGGGGGG");
    	int value = (int) en.getValue();
    	Print.d(st+" "+value);
    	Print.dl();
    	//sb.append(st+"{"+value+"}"+" ");
    }
    //return str.toString();
}
public static int mostNumber(int a[]){
//	int most=a[0];
//	int count=1;
//	for(int i=1;i<a.length;i++){
//		if(a[i]!=most){
//			count--;
//			if(count<)
//				most=a[i];
//		}
//		else
//			count++;
//	}
//	Print.dl(most);
//	
	return 0;
}
//public void chorus{
//	char
//}
}
