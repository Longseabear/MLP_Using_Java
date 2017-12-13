import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class LayerManager implements Serializable{
	public ArrayList<Layer> module = new ArrayList<Layer>();
	public LossFunction costFun;
	private float lr;
	Matrix _output;
	public Matrix test(Matrix input, Matrix _gt) {		
		costFun.setGT(_gt);
		for (Layer l : module) {
			input = l.feedForward(input);
		}
		_output = input;
		return _output;
	}
	public Matrix test(Matrix input) {
		//System.out.println("input : " + input);	
		for (Layer l : module) {
			input = l.feedForward(input);
		}		
//		System.out.println("input mat : " + input);
		
		_output = input;
		return _output;
	}
	public void setLossFunction(LossFunction l){
		costFun = l;
	}
	public Matrix backward(Matrix input) {
		System.out.println("BACKWARD START");
		for(int i=module.size()-1;i>=0;i--){
			Layer l = module.get(i);
			try {
				input = l.backForward(input,lr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return input;
	}
	public void training(ArrayList<float[]> dataSet) throws Exception{
		for(int i=0;i!=dataSet.size();i++){			
			Matrix data = new Matrix(module.get(0)._inPort,1);
			Matrix gt = new Matrix(dataSet.get(0).length-module.get(0)._inPort,1);
			for(int j=0;j!=dataSet.get(0).length;j++){
				if(j<data._row)
				{	
					data.matrix[j][0] = dataSet.get(i)[j];
					if(j<2)
						data.matrix[j][0] = data.matrix[j][0] / 1000f;	
				}
				else{
					gt.matrix[j-data._row][0] = dataSet.get(i)[j];
				}
			}
			costFun.setGT(gt);
			Matrix o = test(data);
			costFun.feedForward(o);

			o = costFun.backForward(new Matrix(1,1),lr);
			backward(o);
		}
	}
	public void visualize(ArrayList<float[]> dataSet) throws Exception{
		for(int i=0;i!=dataSet.size();i++){			
			Matrix data = new Matrix(module.get(0)._inPort,1);
			Matrix gt = new Matrix(dataSet.get(0).length-module.get(0)._inPort,1);
			for(int j=0;j!=dataSet.get(0).length;j++){
				if(j<data._row)
				{	
					data.matrix[j][0] = dataSet.get(i)[j];
					if(j<2)
						data.matrix[j][0] = data.matrix[j][0] / 1000f;	
				}
				else{
					gt.matrix[j-data._row][0] = dataSet.get(i)[j];
				}
			}
			costFun.setGT(gt);
			Matrix o = test(data);
			costFun.feedForward(o);

			o = costFun.backForward(new Matrix(1,1),lr);
			backward(o);
		}
	}
	public void printAllNodeWeight(){
		for(Layer l : module){
			if(l.weight != null)
			{
				System.out.println("weight\n " + l.weight);
				System.out.println("bias\n " + l.bias);
			}
		}
	}
	public void setAlpha(float a){
		lr = a;
	}
	public void netSave(String filepath){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filepath)));
			oos.writeObject(this.module);
			System.out.println("Save Success");
		} catch (IOException e) {
			System.out.println(filepath +" save fail -> IO EXCEPTION");
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public void netLoad(String filepath){
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(new File(filepath)));
			this.module = (ArrayList<Layer>) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ois!=null){
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
