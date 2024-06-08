package es.ulpgc.eite.da.advquiz.cheat;

import java.lang.ref.WeakReference;

public interface CheatContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayAnswer(CheatViewModel viewModel);
    void resetAnswer();
    void finishView();
  }

  interface Presenter {
    void injectView(WeakReference<View> view);
    void injectModel(Model model);

    void onResumeCalled();
    void onCreateCalled();
    void onRecreateCalled();
    void onDestroyCalled();
    void onBackButtonPressed();
    void onWarningButtonClicked(int option);
  }

  interface Model {
    String getAnswer();
    void setAnswer(String answer);
  }

}
