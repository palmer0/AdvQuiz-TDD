package es.ulpgc.eite.da.advquiz.question;

public class QuestionModel implements QuestionContract.Model {

  public static String TAG = "AdvQuiz.QuestionModel";

  private final String[] quizArray;
  private int quizIndex;

  private String correctResultText, incorrectResultText, emptyResultText;

  public QuestionModel(String[] quizArray) {
    this.quizArray=quizArray;
  }

  @Override
  public String getQuestion() {
    return quizArray[quizIndex];
  }

  @Override
  public String getOption1() {
    return quizArray[quizIndex+1];
  }

  @Override
  public String getOption2() {
    return quizArray[quizIndex+2];
  }

  @Override
  public String getOption3() {
    return quizArray[quizIndex+3];
  }

  @Override
  public boolean isCorrectOption(int option) {
    int answer = Integer.valueOf(quizArray[quizIndex+4]);

    if(answer == option) {
      return true;
    }

    return false;
  }

  @Override
  public int getQuizIndex() {
    return quizIndex;
  }

  @Override
  public void setQuizIndex(int index) {
    quizIndex=index;
  }

  @Override
  public boolean hasQuizFinished(){
    if(quizIndex+4 < quizArray.length-1) {
      return false;
    }

    return true;
  }

  @Override
  public void incrQuizIndex() {
    quizIndex=quizIndex+5;

  }

  @Override
  public String getAnswer() {
    int index= Integer.valueOf(quizArray[quizIndex+4]);
    return quizArray[quizIndex+index];
  }

  @Override
  public String getCorrectResultText() {
    return correctResultText;
  }

  @Override
  public void setCorrectResultText(String text) {
    correctResultText = text;
  }

  @Override
  public String getIncorrectResultText() {
    return incorrectResultText;
  }

  @Override
  public void setIncorrectResultText(String text) {
    incorrectResultText = text;
  }

  @Override
  public String getEmptyResultText() {
    return emptyResultText;
  }

  @Override
  public void setEmptyResultText(String text) {
    emptyResultText = text;
  }
}
