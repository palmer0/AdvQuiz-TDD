package es.ulpgc.eite.da.advquiz.cheat;

public class CheatModel implements CheatContract.Model {

  public static String TAG = "AdvQuiz.CheatModel";

  private String correctAnswer;
  private String answerEmptyText;

  public CheatModel() {

  }

  @Override
  public String getCorrectAnswer() {
    return correctAnswer;
  }

  @Override
  public void setCorrectAnswer(String answer) {
    this.correctAnswer =answer;
  }

  @Override
  public void setAnswerEmptyText(String text) {
    answerEmptyText = text;
  }

  @Override
  public String getAnswerEmptyText() {
    return answerEmptyText;
  }
}
