package es.ulpgc.eite.da.advquiz.cheat;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advquiz.app.AppMediator;
import es.ulpgc.eite.da.advquiz.app.CheatToQuestionState;
import es.ulpgc.eite.da.advquiz.app.QuestionToCheatState;


public class CheatPresenter implements CheatContract.Presenter {

  public static String TAG = "AdvQuiz.CheatPresenter";

  private AppMediator mediator;
  private WeakReference<CheatContract.View> view;
  private CheatState state;
  private CheatContract.Model model;

  public CheatPresenter(AppMediator mediator) {
    this.mediator = mediator;
  }


  @Override
  public void onCreateCalled() {
    Log.e(TAG, "onCreateCalled");

    // init the state
    state = new CheatState();
    state.answerEnabled=true;
    mediator.setCheatState(state);

    /*
    // reset state to tests
    state.answerEnabled=true;
    state.answerCheated=false;
    state.answer = null;

    // update the view
    view.get().resetAnswer();
    */

    // use passed state if is necessary
    QuestionToCheatState savedState = mediator.getQuestionToCheatState();
    if (savedState != null) {

      Log.e(TAG, "answer: "+savedState.answer);

      // fetch the model
      model.setAnswer(savedState.answer);

      // update the state
      state.answer = model.getAnswer();
      /*if(state.answerCheated) {
        state.answer = model.getAnswer();
      }*/

    }
  }

  @Override
  public void onRecreateCalled() {
    Log.e(TAG, "onRecreateCalled");

    // update the state
    state = mediator.getCheatState();

    Log.e(TAG, "answer: " + state.answer);

    // fetch the model
    model.setAnswer(state.answer);
  }

  @Override
  public void onResumeCalled() {
    Log.e(TAG, "onResumeCalled");

    /*
    // use passed state if is necessary
    QuestionToCheatState savedState = mediator.getQuestionToCheatState();
    if (savedState != null) {

      Log.e(TAG, "answer: "+savedState.answer);

      // fetch the model
      model.setAnswer(savedState.answer);


      // update the state
      if(state.answerCheated) {
        state.answer = model.getAnswer();
      }

    }
    */

    // update the view
    view.get().displayAnswer(state);
    if(!state.answerCheated) {
      view.get().resetAnswer();
    }

    /*if(state.answer == null) {
      view.get().resetAnswer();
    }*/
  }

  @Override
  public void onDestroyCalled() {
    Log.e(TAG, "onDestroyCalled");

    //mediator.resetCheatState();
  }

  @Override
  public void onBackPressed() {
    Log.e(TAG, "onBackPressed");

    Log.e(TAG, "cheated "+state.answerCheated);

    CheatToQuestionState passedState=new CheatToQuestionState();
    passedState.answerCheated=state.answerCheated;
    mediator.setCheatToQuestionState(passedState);

    view.get().finishView();
  }

  @Override
  public void onWarningButtonClicked(int option) {
    Log.e(TAG, "onWarningButtonClicked");

    //option=1 => yes, option=0 => no

    if(option==1) {
      showAnswer();

    } else {
      onBackPressed();
      //view.get().finishView();
    }
  }

  private void showAnswer() {
    state.answerCheated=true;
    state.answerEnabled=false;
    state.answer = model.getAnswer();

    // update the view
    view.get().displayAnswer(state);
  }


  @Override
  public void injectView(WeakReference<CheatContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(CheatContract.Model model) {
    this.model = model;
  }

}
