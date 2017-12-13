
public class LayerMSE extends LossFunction {
	public LayerMSE(int in, int out) {
		super(in, out);
		// TODO Auto-generated constructor stub
	}
	public Matrix gt;
	// MSE
	// 1/2(y-y`)^2
	//
	public Matrix feedForward(Matrix _in){
		_input = _in;
		Matrix res = new Matrix(_in._row,1);
		for(int i=0;i!=_in._row;i++){
			res.matrix[i][0] = 0;
			for(int j=0;j!=_in._column;j++){
				 res.matrix[i][j] += (_in.matrix[i][j]-gt.matrix[i][0]) * (_in.matrix[i][j]-gt.matrix[i][0]);
			}
			res.matrix[i][0] = res.matrix[i][0] * 0.5f;
		}
		_output = res;
		System.out.println("ERROR RATE\n" + _output);
		return _output;
	}
	private Matrix getLossFunGrad(Matrix target){
		Matrix res = new Matrix(target._row, target._column);
		for(int i=0;i!=target._row;i++){
			res.matrix[i][0] = (target.matrix[i][0]-gt.matrix[i][0]);
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
