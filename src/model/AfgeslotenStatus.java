package model;

public class AfgeslotenStatus extends QuizStatus {
	private Quiz quiz;
	
	public AfgeslotenStatus(Quiz quiz){
		this.quiz = quiz;
	}
	
	@Override
	public String toString(){
		return "Afgesloten";
	}
}
