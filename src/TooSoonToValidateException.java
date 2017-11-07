public class TooSoonToValidateException extends Exception {

	Step step;
	
	public TooSoonToValidateException(Step s){
		this.step = s;
	}
}
