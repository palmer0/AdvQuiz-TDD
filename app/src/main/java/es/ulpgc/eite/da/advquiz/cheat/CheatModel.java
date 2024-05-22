package es.ulpgc.eite.da.advquiz.cheat;

public class CheatModel implements CheatContract.Model {

  public static String TAG = "AdvQuiz.CheatModel";

  private String answer;

  public CheatModel() {

  }

  @Override
  public String getAnswer() {
    return answer;
  }

  @Override
  public void setAnswer(String answer) {
    this.answer=answer;
  }

}
