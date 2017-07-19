public class AlreadyValidatedException extends Exception {

	Step step; // Etape déjà validée
	
	public AlreadyValidatedException(Step step){
		this.step = step;
	}
}
