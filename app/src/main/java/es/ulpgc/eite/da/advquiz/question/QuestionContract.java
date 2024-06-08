package es.ulpgc.eite.da.advquiz.question;

import java.lang.ref.WeakReference;

public interface QuestionContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void navigateToCheatScreen();

    void displayQuestionData(QuestionViewModel viewModel);
    //void resetResult();
    //void updateResult(boolean isCorrect);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);
    void injectModel(Model model);

    void onResumeCalled();
    void onCreatedCalled();
    void onRecreatedCalled();
    void onOptionButtonClicked(int option);
    void onNextButtonClicked();
    void onCheatButtonClicked();

      void onPauseCalled();

      void onDestroyCalled();
  }

  interface Model {
    String getQuestion();
    String getOption1();
    String getOption2();
    String getOption3();
    boolean isCorrectOption(int option);
    boolean hasQuizFinished();
    int getQuizIndex();
    void setQuizIndex(int index);
    String getAnswer();
    void incrQuizIndex();

    String getCorrectResultText();

    void setCorrectResultText(String text);

    String getIncorrectResultText();

    void setIncorrectResultText(String text);

    String getEmptyResultText();

    void setEmptyResultText(String text);
  }

}
