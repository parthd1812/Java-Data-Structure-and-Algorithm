import java.util.Arrays;


public class StrArray{
	private final String[] strArray;
	public StrArray(String[] input){
		this.strArray = Arrays.copyOf(input,input.length+1);
	}
	public String[] getStrArray(){
		return Arrays.copyOf(strArray,strArray.length);
	}
}