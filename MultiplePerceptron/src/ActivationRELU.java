
public class ActivationRELU extends Activation {
	@Override
	public Matrix getAct(Matrix in) {
		_input = in;
		Matrix res = new Matrix(in._row, in._column);

		for (int i = 0; i != in._row; i++) {
			for (int j = 0; j != in._column; j++) {
				res.matrix[i][j] = getRELU(in.matrix[i][j]);					
			}
		}
		_output = res;
		return _output;
	}
	public float getRELU(float _in){
		return Math.max(_in, 0);
	}


	public Matrix backward(Matrix lo) {
		Matrix grad = getActGrad(_input);
		return grad;
	}
	@Override
	public Matrix getActGrad(Matrix _in) {
		// TODO Auto-generated method stub
		if (_in._column != 1) {
			System.out.println("input not only 1!");
			return null;
		}

		Matrix out = new Matrix(_in._row,1);
		for (int i = 0; i != _in._row; i++) {
			out.matrix[i][0] = _in.matrix[i][0] > 0 ? 1 : 0;
		}
		return out;
	}
}
