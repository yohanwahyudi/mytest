
public class C {
	
	public static final String strStatic1;
	public static String strStatic2;
	
	static {
		
		strStatic1=setStrStatic1("name");
	}
	
	public C() {
		strStatic2 = "name2";
	}
	
	private static String setStrStatic1(String name) {
		return name;
	}

}
