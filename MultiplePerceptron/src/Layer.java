import java.io.Serializable;

public abstract class Layer implements Serializable{
	public Matrix _input;
	public Matrix _output;
	public int _inPort;
	public int _outPort;
	public Matrix weight;
	public Matrix bias;
	public Layer(int in, int out){
		_inPort = in;
		_outPort = out;
	}
	public abstract Matrix feedForward(Matrix b);
	public abstract Matrix backForward(Matrix c, float lr);
}
