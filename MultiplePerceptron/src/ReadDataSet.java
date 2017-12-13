import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author LEaps
 *
 *	Read Data set Class
 *	
 *	- protocol
 *	dataset file must to start with dataset information.
 *	[ data Set column Num ] 
 */
public class ReadDataSet {

	static FileInputStream pis;
	public static ArrayList<float[]> readData(String filepath){
		try {
			pis = new FileInputStream(new File(filepath));

			Scanner s = new Scanner(pis);
			ArrayList<float[]> res = new ArrayList<float[]>();
			int eleNum = Integer.parseInt(s.nextLine());
			while(s.hasNext()){
				String[] temp = s.nextLine().split(" ");
				float[] list = new float[eleNum];
				for(int i=0; i!=list.length;i++){
					list[i] = Float.valueOf(temp[i]);
				}
				res.add(list);
			}
			pis.close();
			return res;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				pis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;		
	}
}
