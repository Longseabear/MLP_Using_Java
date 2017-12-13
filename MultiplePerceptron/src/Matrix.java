import java.io.Serializable;
import java.util.Random;

/**
 * 
 * @author LEaps
 *
 *         This is Matrix Class this is made for neuron network. support
 *         operation. all method return new object.
 */
public class Matrix implements Serializable{
	float matrix[][];
	int _row, _column;

	public Matrix(float[][] m) {
		_row = m.length;
		_column = m[0].length;
		matrix = m;
	}
	public Matrix(float[] m) {
		_row = m.length;
		_column = 1;
		matrix = new float[_row][_column];
		for(int i=0;i!=m.length;i++)
		{
			matrix[i][0] = m[i];
		}
	}
	public Matrix(int row, int column) {
		_row = row;
		_column = column;
		matrix = new float[_row][_column];
		Random random = new Random();
		for (int i = 0; i != _row; i++) {
			for (int j = 0; j != _column; j++) {
				//System.out.println(random.nextFloat()*2 - 1f);
				matrix[i][j] = (random.nextFloat()*2 - 1f)/(float)Math.sqrt(row);
			}
		}
	}

	public static Matrix createSameValueMatrix(int row, int column, float value) {
		Matrix res = new Matrix(row, column);
		for (int i = 0; i != row; i++) {
			for (int j = 0; j != column; j++) {
				res.matrix[i][j] = value;
			}
		}
		return res;
	}

	@Override
	public String toString() {
		String res = _row + "x" + _column + " Matrix :\n";
		for (int i = 0; i != _row; i++) {
			for (int j = 0; j != _column; j++) {
				res += matrix[i][j] + " ";
			}
			res += "\n";
		}
		return res;
	}

	// Operation overloading
	public Matrix ADD(Matrix target) throws MatrixError {
		if (_row != target._row || _column != target._column) {
			String err = "ADD OPERATION ERROR ::\n" + _row + "x" + _column + " Matrix // Target = " + target._row + "x"
					+ target._column + " Matrix";
			throw new MatrixError(err);
		}
		Matrix res = new Matrix(_row, _column);
		for (int i = 0; i != _row; i++) {
			for (int j = 0; j != _column; j++) {
				res.matrix[i][j] = matrix[i][j] + target.matrix[i][j];
			}
		}
		return res;
	}

	// Operation overloading
	public Matrix MINUS(Matrix target) throws MatrixError {
		if (_row != target._row || _column != target._column) {
			String err = "MINUS OPERATION ERROR ::\n" + _row + "x" + _column + " Matrix // Target = " + target._row
					+ "x" + target._column + " Matrix";
			throw new MatrixError(err);
		}
		Matrix res = new Matrix(_row, _column);
		for (int i = 0; i != _row; i++) {
			for (int j = 0; j != _column; j++) {
				res.matrix[i][j] = matrix[i][j] - target.matrix[i][j];
			}
		}
		return res;
	}

	// Hadamard product
	public Matrix HADAMARD(Matrix target) throws MatrixError {
		if (_row != target._row || _column != target._column) {
			String err = "HADAMARD OPERATION ERROR ::\n" + _row + "x" + _column + " Matrix // Target = " + target._row
					+ "x" + target._column + " Matrix";
			throw new MatrixError(err);
		}
		Matrix res = new Matrix(_row, _column);
		for (int i = 0; i != _row; i++) {
			for (int j = 0; j != _column; j++) {
				res.matrix[i][j] = matrix[i][j] * target.matrix[i][j];
			}
		}
		return res;
	}

	// constant times
	public Matrix HADAMARD(float target) throws MatrixError {
		Matrix res = new Matrix(_row, _column);
		for (int i = 0; i != _row; i++) {
			for (int j = 0; j != _column; j++) {
				res.matrix[i][j] = matrix[i][j] * target;
			}
		}
		return res;
	}
	//Matrix Multiply
	public Matrix MUL(Matrix target) throws MatrixError {
		if (_column != target._row) {
			String err = "MUL OPERATION ERROR ::\n" + _row + "x" + _column + " Matrix // Target = " + target._row
					+ "x" + target._column + " Matrix";
			throw new MatrixError(err);
		}
		Matrix res = new Matrix(_row, target._column);
		for (int i = 0; i < _row; i++) {
			for (int j = 0; j < target._column; j++) {
				float t = 0;
				for (int k = 0; k < _column; k++) {
					t += matrix[i][k] * target.matrix[k][j];
				}
				res.matrix[i][j] = t;
			}
		}
		return res;
	}
	// Transpose
	public Matrix transpos(){
		Matrix res = new Matrix(_column, _row);
		for(int i=0; i!=_row; i++){
			for(int j=0; j!=_column; j++){
				res.matrix[j][i] = matrix[i][j];
			}
		}
		return res;
	}
	public static void main(String argv[]) {
		Matrix a = new Matrix(new float[][] { { 1, 2, 3 }, { 4, 5, 6 } });
	}
}
