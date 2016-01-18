package algorithm;
/**
 * 区间
 * @author frostbitespider
 *
 */
public class Itv {
public int low;
public int high;
public Itv(int l,int h){
	low=l;
	high=h;
}
@Override
public String toString(){
	return new String("["+low+","+high+"]");
}
}
