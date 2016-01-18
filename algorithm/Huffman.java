package algorithm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.frost.io.*;
/**
 * 对TreeNode的使用：
 * compress中
 * key指出现次数
 * data指ASCII
 * flag指是否为ASCII
 * uncompress中
 * key指ASCII
 * data指code
 * flag指是否为ASCII
 * @author frostbitespider
 *
 */
public class Huffman {
	/**
	 * 
	 * @param s
	 * @return
	 */
	public int convert(String s){
		int b = 0;
		char[] arr=s.toCharArray();
		int k=arr.length-1;
		for(int i=0;i<arr.length;i++,k--){
			b|=(arr[i]-48)<<k;
		}
		return b;
	}
	/**
	 * 对初始节点按权重排序，倒序
	 * @param line
	 * @param occurs
	 */
	public void mysort(TreeNode[] line){	
		for(int j=1;j<line.length;j++){
			int weight=line[j].key;
			TreeNode temp=line[j];
			int i=j-1;
			while(i>=0&&line[i].key<weight){
				line[i+1]=line[i];
				i--;
			}
			line[i+1]=temp;
		}
	}
	/**
	 * 寻找n应插入的位置
	 * @param list
	 * @param n
	 * @param occurs
	 * @return
	 */
	public void insert(List<TreeNode> list,TreeNode n){
		int mid=0;
		int high=list.size()-1;
		int low=0;
		while(low<=high){
			mid=(high+low)/2;
			if(low==high||list.get(mid).key==n.key){
				list.add(mid, n);
				return;
			}
			else if(list.get(mid).key<n.key){
				high=mid-1;
			}
			else{
				low=mid+1;
			}
		}
		list.add(mid, n);
		return;
	}
	public int compress(String in,String out,String args[]){
		try {
		FileIO fin=new FileIO(in,false);
		//FileIO fout=new FileIO(out,true);
		if(!fin.isExist()){
			Print.dl("Filein not exist");
			return -1;
		}
		int[] occurs=new int[65536];//对应ASCII 
		int total=0;
		while(true){
			String str=fin.ReadbyLine();
			if(str!=null){
				str+="\n";
				char[] arr=str.toCharArray();
				total+=arr.length;
				Sort.getBitMap(65536, arr, occurs);//occurs
			}
			else
				break;
		}
		Print.dl("total="+total);
		Print.dl(occurs);
		total=0;
		for(int i=0;i<65536;i++){
			total+=occurs[i];
		}
		Print.dl("total="+total);
		TreeNode[] t=new TreeNode[65536];
		for(int i=0;i<65536;i++){
			t[i]=new TreeNode(occurs[i],i);
			t[i].flag=true;
		}
		mysort(t);
		//此时t数组按weight有序
		int count=0;
		while(t[count++].key!=0);
		count--;//出现的ASCII数
		total=0;
		for(int i=0;i<count;i++){
			total+=t[i].key;
		}
		Print.dl("total="+total);
		int k=count;
		List<TreeNode> list=new ArrayList<TreeNode>();
		for(int i=0;i<k;i++){
			list.add(t[i]);
		}
		System.out.print(list);
		while(list.size()>1){
			TreeNode temp=new TreeNode();
			temp.lc=list.get(list.size()-1);
			temp.rc=list.get(list.size()-2);
			temp.key=temp.lc.key+temp.rc.key;
			list.remove(list.size()-1);
			list.remove(list.size()-1);
			insert(list,temp);
		}
		Print.dl();
		Print.dl("heheda");
		//TreeNode root=list.get(0);
		Map<Integer, String> map=new HashMap<Integer, String>();
		//map必须为对象
		visit(list.get(0),map,"");
		count=0;
		for(Entry<Integer, String> e : map.entrySet()){
			if(e!=null){
				count++;
				char c=(char) e.getKey().intValue();
				Print.dl(c+":"+" "+occurs[c]+"  "+e.getKey()+"	"+e.getValue());
			}
		}
		//begin compress
		/**
		 *           我们压缩后的文件应该分为两部分：码表和压缩体。 
          码表是一个由字节作为key，字节对应的huffman编码作为value的map对象。
          个人认为如果将码表压缩在文件前端，对解压缩应该要方便一些。
          因为解压缩一开始就可以把码表读出来保存在map对象中。码表压缩时第一位应该保存一个int值，
          是map的size()。而且压缩码表的时候可以用一个小技巧，压缩时的码表是map<Byte , String>
		 */
		fin=new FileIO(in,false);
		FileIO fout=new FileIO(out,true);
		//FileIO fout=new FileIO(out,true);
		if(!fin.isExist()){
			Print.dl("Filein not exist");
			return -1;
		}
		/**
		 * first write map to out file
		 * include map length and byte length
		 */
		String line=new String();
		for(Entry<Integer, String> e : map.entrySet()){
			if(e!=null){
				char c=(char) e.getKey().intValue();
				line+=(e.getKey()+"	"+e.getValue()+"\n");
			}
		}
		fout.WritebyLine(count+"");
		fout.WritebyLine(line);
		byte[] buf=new byte[2048];
		int offset=0;//4+2+line.length()*4+2;
		int start=0;//bit to be write
		int curr=0;
		String str=new String();
		while(true){
			str=fin.ReadbyLine();
			if(str!=null){
				str+="\n";
				char[] arr=str.toCharArray();
				String s="";
				for(int i=0;i<arr.length;i++){
					if(start==8){
						start=0;
						curr++;
						if(curr==2048){
							fout.Write(buf,offset,2048);
							//offset+=2048;
							buf=new byte[2048];
							curr=0;
							start=0;
							continue;
						}
					}
					int a=arr[i];
					s=map.get(a);
					int len=s.length();
					if(start+len<=8){
						int b=convert(s);
						//Print.d(b);
						buf[curr]|=b<<(8-start-len);
						start+=len;
					}
					else if(curr==2047){
						fout.Write(buf,offset,2048);
						//offset+=2048;
						buf=new byte[2048];
						curr=0;
						start=0;
					}

					else{//s.length may >8
						//start=6 len=12
						//------?? **??**?? **??
						String sub1=s.substring(0, 8-start);
						int b=convert(sub1);
						buf[curr]|=b;
						curr++;
						sub1=s.substring(8-start);
						//??**??** ??  sub1
						//p=1	y=2
						int p=sub1.length()/8;
						String sub2="";
						while(p>0){
							sub2=sub1.substring(8*(p-1), 8*p);
							b=convert(sub2);
							buf[curr++]|=b;
							p--;
							if(curr==2048){
								fout.Write(buf,offset,2048);
								//offset+=2048;
								buf=new byte[2048];
								curr=0;
								start=0;
							}
						}
						sub2=sub1.substring(8*(sub1.length()/8));
						b=convert(sub2);
						buf[curr]|=b<<(8-sub2.length());
						start=sub2.length();
					}
				}
			}
			else{
				fout.Write(buf,offset,curr+1);
				break;
			}
		}
		Print.dl();
		Print.dl("encode complete");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	private void visit(TreeNode treeNode, Map<Integer, String> map,String code) {
		// TODO Auto-generated method stub
		if(treeNode==null)
			return;
		else{
			if(treeNode.flag==true){
				map.put((Integer) treeNode.data, code);
			}
			visit(treeNode.lc,map,code+"0");
			visit(treeNode.rc,map,code+"1");
		}
	}
	private void visit2(TreeNode treeNode, Map<Integer, String> map,String code) {
		// TODO Auto-generated method stub
		if(treeNode==null)
			return;
		else{
			if(treeNode.flag==true){
				map.put(treeNode.key, code);
			}
			visit2(treeNode.lc,map,code+"0");
			visit2(treeNode.rc,map,code+"1");
		}
	}
	public int uncompress(String in,String out,String[] args){
		try {
			FileIO fin=new FileIO(in,false);
			FileIO fout=new FileIO(out,true);
			if(!fin.isExist()){
				Print.dl("Filein not exist");
				return -1;
			}
			int fileoffset=0;
			Map<Integer,String> map=new HashMap<Integer,String>();
			String[] line=fin.ReadbyLine().split(" ");
			int mapLength=Integer.parseInt(line[0]);
			fileoffset+=3;
			int key=-1;
			String code="";
			int i=0;
			String str=new String();
			while(i++<mapLength){
				str=fin.ReadbyLine();
				line=str.split("\t");
				key=Integer.parseInt(line[0]);
				code=line[1];
				map.put(key, code);
				fileoffset+=(str.length()+1);
			}
			String temp=fin.ReadbyLine();
			fileoffset+=3;
//			temp=fin.ReadbyLine();
			TreeNode root = buildTree(map);
			//test
//			Map<Integer,String> testmap=new HashMap<Integer,String>();
//			visit2(root,testmap,"");
//			String testL=new String();
//			for(Entry<Integer, String> e : testmap.entrySet()){
//				if(e!=null){
//					char c=(char) e.getKey().intValue();
//					testL+=(c+":"+e.getKey()+"	"+e.getValue()+"\n");
//				}
//			}
//			Print.dl(testL);
			//buildTree is right
			//map is right
			//endtest
			byte[] buf = new byte[2048];
			int num = fin.ReadtoBuf(buf,fileoffset)-1;
			String outS = new String();
			int g=0;
			while (num >=0) {// repeat read to buf
				TreeNode p = root;
				i = 0;
				short len = 1;
				int bit = 0;
				byte currbyte = buf[i];
				while (true) {//process the buf
					bit = (byte)((currbyte >> (8-len++)) & 0x1);
					if (bit == 1) {
						p = p.rc;
						if (p.flag == true) {
							char c=(char)p.key;
							outS += c;
							if(p.key=='m'){
								g++;
								if(g==12969){
									Print.d("fuck");
								}
							}
							p = root;
						}
					} else {
							p = p.lc;
							if (p.flag == true) {
								char c=(char)p.key;
								outS += c;
								if(p.key=='m'){
									g++;
									if(g==12969){
										Print.d("fuck");
									}
								}
								p = root;
							}
					}
					if (len == 9) {
						if(i!=2047){
							currbyte = buf[++i];
							len=1;
						}else{
							break;
						}
					}
					if (i == num) {
						break;
					}
					if(outS.length()==Math.pow(2, 16)-1){
						fout.WritebyLine(outS);
						outS="";
					}
				}
				//fileoffset=num;	no longer need it anymore 
				num = fin.ReadtoBuf(buf,0);
			}
			//Print.dl(outS);
			fout.WritebyLine(outS);
			//seems in the end there may be some letters that don't exist in the source file
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public TreeNode buildTree(Map<Integer,String> map){
		TreeNode root=new TreeNode();
		for(Entry<Integer, String> e : map.entrySet()){
			if(e!=null){
				int key=e.getKey();
				char[] code=e.getValue().toCharArray();
				TreeNode p=root;
				for(int i=0;i<code.length-1;i++){
					if(code[i]=='0'){
						if(p.lc==null){
							TreeNode node=new TreeNode();
							p.lc=node;
						}
						p=p.lc;
					}else{
						if(p.rc==null){
							TreeNode node=new TreeNode();
							p.rc=node;
						}
						p=p.rc;
					}
				}
				if(code[code.length-1]=='0'){
					String data=String.copyValueOf(code);
					TreeNode node=new TreeNode(key,data);
					node.flag=true;
					p.lc=node;
				}
				else{
					String data=String.copyValueOf(code);
					TreeNode node=new TreeNode(key,data);
					node.flag=true;
					p.rc=node;
				}
			}
		}
		return root;
	}
}
