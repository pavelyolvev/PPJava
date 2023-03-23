import FirstPackage.*;

class FirstClass {
	public static void main(String[] s) {
		FirstPackage o = new FirstPackage();
		int i, j;
		for (i=1; i<=8; i++){
			for (j=1; j<=8; j++){
			o.getNum1(i);
			o.getNum2(j);
			
			System.out.print(o.numSum());
			System.out.print(" ");
			}
			System.out.println();
		}
			
	}
}
