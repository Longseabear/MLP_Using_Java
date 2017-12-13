
public class LayerNN extends Layer {
	public Activation _act;

	public LayerNN(int in, int out, Activation act) {
		super(in, out);
		_act = act;
		weight = new Matrix(_outPort,_inPort);
		bias = new Matrix(_outPort,1);
	}

	public void setWeight(float[][] f) {
		if (f == null) {
			System.out.println("SetWeight -> fail\n");
			return;
		}
		int row = f.length;
		int column = f[0].length;
		if (weight._row != row || weight._column != column) {
			System.out.println("SetWeight -> fail Because not match size\n");
			return;
		}
		for (int i = 0; i != row; i++) {
			for (int j = 0; j != column; j++) {
				weight.matrix[i][j] = f[i][j];
			}
		}
	}

	public void setBias(float[][] f) {
		if (f == null) {
			System.out.println("SetBias -> fail\n");
			return;
		}
		int row = f.length;
		int column = f[0].length;
		if (bias._row != row || bias._column != column) {
			System.out.println("SetBias -> fail Because not match size\n");
			return;
		}
		for (int i = 0; i != row; i++) {
			for (int j = 0; j != column; j++) {
				bias.matrix[i][j] = f[i][j];
			}
		}
	}

	@Override
	public Matrix feedForward(Matrix in) {
		_input = in;
		try {
			Matrix sigma = weight.MUL(in).ADD(bias);
			Matrix res = _act.getAct(sigma);
			_output = sigma;
			return res;
		} catch (MatrixError e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("NN feedForward Fail");
		return null;
	}

	@Override
	public Matrix backForward(Matrix target, float lr) {
		try {
			Matrix grad = _act.getActGrad(_output);
			Matrix et = target.HADAMARD(grad);
					
		//	System.out.println("¿Ã¿¸ : " + target);
			weight = weight.MINUS(et.MUL(_input.transpos()).HADAMARD(lr));	
			bias = bias.MINUS(et.HADAMARD(lr));
			
			Matrix newEt;
			newEt = weight.transpos().MUL(et);
			return newEt;
		} catch (MatrixError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("BackWard Fail");
		return null;		
	}
}
