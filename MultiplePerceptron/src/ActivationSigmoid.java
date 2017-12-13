
public class ActivationSigmoid extends Activation {
	@Override
	public Matrix getAct(Matrix in) {
		_input = in;
		Matrix res = new Matrix(in._row, in._column);

		for (int i = 0; i != in._row; i++) {
			for (int j = 0; j != in._column; j++) {
				res.matrix[i][j] = getSigmoid(in.matrix[i][j]);
			}
		}
		_output = res;
		return _output;
	}
	public float getSigmoid(float _in){
		return 1.0f / (1.0f + (float)Math.exp(-_in));
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
			out.matrix[i][0] = getSigmoid(_in.matrix[i][0]) * ( 1 - getSigmoid(_in.matrix[i][0]));
		}
		return out;
	}
}
