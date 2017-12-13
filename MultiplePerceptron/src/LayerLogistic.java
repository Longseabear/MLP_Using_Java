
public class LayerLogistic extends LossFunction {
	public LayerLogistic(int in, int out) {
		super(in, out);
		// TODO Auto-generated constructor stub
	}
	public Matrix gt;
	// MSE
	// -1 -y(log(x))-
	//
	public Matrix feedForward(Matrix _in){
		_input = _in;
		Matrix res = new Matrix(_in._row,1);
		for(int i=0;i!=_in._row;i++){
			res.matrix[i][0] = 0;
			for(int j=0;j!=_in._column;j++){
				 res.matrix[i][j] += (-1*gt.matrix[i][0]* (float)Math.log((double)_in.matrix[i][j]) - (1-gt.matrix[i][0]) *(float)Math.log((double)(1-_in.matrix[i][j])));
			}
		}
		_output = res;
		System.out.println("ERROR RATE\n" + _output);
		return _output;
	}
	private Matrix getLossFunGrad(Matrix target){
		Matrix res = new Matrix(target._row, target._column);
		for(int i=0;i!=target._row;i++){
			res.matrix[i][0] = (-1*gt.matrix[i][0]* (1 /target.matrix[i][0]) + (1-gt.matrix[i][0]) * (1 /(1-target.matrix[i][0])));
		}
		return res;
	}
	@Override
	public Matrix backForward(Matrix _in,float lr){
		return getLossFunGrad(_input);
	}
	public void setGT(Matrix _gt){
		gt = _gt;
	}
}
