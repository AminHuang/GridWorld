import java.util.*;

public class HelloWorld {
	String str;
	public void setStr() {
		str = "hello world!";
	}
	
	public String getStr() {
		return str;
	}
	
	public static void main(String[] args) {
		HelloWorld hello = new HelloWorld();
		hello.setStr();
		System.out.print(hello.getStr());
	}
}
