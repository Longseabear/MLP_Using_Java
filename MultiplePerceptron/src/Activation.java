import java.io.Serializable;

public abstract class Activation implements Serializable{
	public Matrix _input;
	public Matrix _output;
	public abstract Matrix getActGrad(Matrix a);
	public abstract Matrix getAct(Matrix a);
}