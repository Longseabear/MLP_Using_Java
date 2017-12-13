import java.io.Serializable;

public abstract class LossFunction implements Serializable{
	public Matrix _input;
	public Matrix _output;
	public int _inPort;
	public int _outPort;
	public Matrix weight;
	public Matrix bias;
	public LossFunction(int in, int out){
		_inPort = in;
		_outPort = out;
	}
	public abstract Matrix feedForward(Matrix b);
	public abstract Matrix backForward(Matrix c, float lr);
	public abstract void setGT(Matrix gt);
		// TODO Auto-generated method stub
}
