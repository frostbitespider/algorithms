package com.frost.io;

import java.io.File;
import java.util.Arrays;

public final class Print {
private Print(){}
public static int dtoFile(String s,File f){
	return 0;
}
public static void d(String s)
{
	System.out.print(s);
}
public static void d(char[] s)
{
	System.out.print(Arrays.toString(s));
}
public static void d(Object o) {
	System.out.print(o+"  ");
}
public static void dl(String s)
{
	System.out.println(s);
}
public static void dl(int s)
{
	System.out.println(s);
}
public static void dl(int[] s)
{
	System.out.println(Arrays.toString(s));
}
public static void dl(char[] s)
{
	System.out.println(Arrays.toString(s));
}
public static void dl(Object[] s)
{
	System.out.println(Arrays.toString(s));
}
public static void dl()
{
	System.out.println();
}
public static void dl(String tag,String s)
{
	System.out.println(tag+":"+s);
}
public static void e(String s)
{
	System.err.print(s);
}
public static void el(String s)
{
	System.err.println(s);
}
public static void el(int s)
{
	System.err.println(s);
}
public static void el(String tag,String s)
{
	System.err.println(tag+":"+s);
}

}
