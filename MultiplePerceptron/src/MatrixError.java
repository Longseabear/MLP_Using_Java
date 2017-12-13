
public class MatrixError extends Exception{
	private final int ERR_CODE;// �����ڸ� ���� �ʱ�ȭ �Ѵ�.

	MatrixError(String msg, int errcode){ //������
		super(msg);
		ERR_CODE = errcode;
	}
	MatrixError(String msg){// ������
		this(msg, 100);// ERR_CODE�� 100(�⺻��)���� �ʱ�ȭ�Ѵ�.
	}
	public int getErrCode(){// ���� �ڵ带 ���� �� �ִ� �޼��嵵 �߰��Ѵ�.
		return ERR_CODE;// �� �޼���� �ַ� getMessage()�� �Բ� ���� ���̴�.
	}
}
