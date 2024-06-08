package es.ulpgc.eite.da.advquiz.cheat;

import java.lang.ref.WeakReference;

public interface CheatContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayAnswerData(CheatViewModel viewModel);
    //void resetAnswer();
    void finishView();
  }

  interface Presenter {
    void injectView(WeakReference<View> view);
    void injectModel(Model model);

    void onResumeCalled();
    void onCreateCalled();
    void onRecreateCalled();

      void onPauseCalled();

      void onDestroyCalled();
    void onBackButtonPressed();
    void onWarningButtonClicked(int option);
  }

  interface Model {
    String getCorrectAnswer();
    void setCorrectAnswer(String answer);

    void setAnswerEmptyText(String text);

    String getAnswerEmptyText();
  }

}
