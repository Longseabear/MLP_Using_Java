
public class Test {
	public static void main(String[] argv){
		LayerManager container = new LayerManager();
		container.netLoad("color.net");
		System.out.println("255 0 0 -> \n " + container.test(new Matrix(new float[]{255,0,0})));
		System.out.println("40 238 40 -> \n " + container.test(new Matrix(new float[]{40,238,40})));
		System.out.println("40 60 100 -> \n " + container.test(new Matrix(new float[]{40,60,100})));
		System.out.println("90 160 200 -> \n " + container.test(new Matrix(new float[]{90,160,200})));
	}
}
