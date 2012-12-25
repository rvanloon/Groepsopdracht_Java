package model;

public class OpengesteldStatus extends QuizStatus{
	private Quiz quiz;
	
	public OpengesteldStatus(Quiz quiz){
		this.quiz = quiz;
	}
	
	@Override
	public String toString(){
		return "Opengesteld";
	}

}
