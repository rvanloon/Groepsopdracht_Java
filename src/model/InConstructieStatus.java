package model;

public class InConstructieStatus extends QuizStatus {
	private Quiz quiz;
	
	public InConstructieStatus(Quiz quiz){
		this.quiz = quiz;
	}
	
	@Override
	public String toString(){
		return "InConstructie";
	}
}
