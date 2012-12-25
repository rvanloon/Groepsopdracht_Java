package model;

public class AfgewerktStatus extends QuizStatus {
	private Quiz quiz;
	
	public AfgewerktStatus(Quiz quiz){
		this.quiz = quiz;
	}
	
	@Override
	public String toString(){
		return "Afgewerkt";
	}

}
