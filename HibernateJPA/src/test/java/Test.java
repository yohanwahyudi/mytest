import java.util.function.Function;

public class Test {
	
	public static void main(String args[]) {	
		
		String a = "1234567890";		
		System.out.println(a.substring(0,7));
		
		A source = new A();
		source.setA("a");
		source.setB(1);
		
		B destination = testFunction.apply(source);
		
		System.out.println(destination.getA()+"-"+destination.getB());
		
		
	}
	
	static Function<A,B> testFunction = new Function<A,B>(){
		@Override
		public B apply(A source) {
			B dest = new B();
			dest.setA(source.getA());
			dest.setB(source.getB());
			
			return dest;
		}
	};

}




