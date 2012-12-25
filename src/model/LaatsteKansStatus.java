package model;

public class LaatsteKansStatus extends QuizStatus {
	private Quiz quiz;
	
	public LaatsteKansStatus(Quiz quiz){
		this.quiz = quiz;
	}
	
	@Override
	public String toString(){
		return "LaatsteKans";
	}
}
