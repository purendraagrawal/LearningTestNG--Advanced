package LearningTestNG;

import java.io.UnsupportedEncodingException;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="-";
		String s1="–";
		System.out.println(isUTF8(s));
		System.out.println(isUTF8(s1));
		
	}
	public static boolean isUTF8(String s){
	    try{
	        byte[]bytes = s.getBytes("UTF-8");
	    }catch(UnsupportedEncodingException e){
	        e.printStackTrace();
	        System.exit(-1);
	    }
	    return true;
	}

}

