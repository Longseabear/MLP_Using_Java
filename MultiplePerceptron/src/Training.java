import java.util.ArrayList;

public class Training {
	public static void main(String argv[]){
		LayerManager container = new LayerManager();
		container.module.add(new LayerNN(6,6,new ActivationSigmoid()));
		container.module.add(new LayerNN(6,6,new ActivationSigmoid()));
		container.module.add(new LayerNN(6,1,new ActivationSigmoid()));
		container.setLossFunction(new LayerMSE(1,1));
		container.setAlpha(0.1f); //learning rate
//		container.netLoad("netTest.net");
		
		ArrayList<float[]> test = ReadDataSet.readData("training.txt");
		for(int i=0;i!=10000;i++){
			System.out.println(i + " iter");
			try {
				container
				.training(test);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Training Fail");
			}			
		}
		container.printAllNodeWeight();
		System.out.println("true -> \n " + container.test(new Matrix(new float[]{0.023f,0.016f,0.048f,0.014f,0.2f,0.002f})));

		container.netSave("netTest.net");
	}
}
