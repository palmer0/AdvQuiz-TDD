package es.ulpgc.eite.da.advquiz.question;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advquiz.app.AppMediator;
import es.ulpgc.eite.da.advquiz.app.CheatToQuestionState;
import es.ulpgc.eite.da.advquiz.app.QuestionToCheatState;

public class QuestionPresenter implements QuestionContract.Presenter {

  public static String TAG = "AdvQuiz.QuestionPresenter";

  private AppMediator mediator;
  private WeakReference<QuestionContract.View> view;
  private QuestionState state;
  private QuestionContract.Model model;

  public QuestionPresenter(AppMediator mediator) {
    this.mediator = mediator;
    //state = mediator.getQuestionState();
  }

  @Override
  public void onCreatedCalled() {
    Log.e(TAG, "onCreatedCalled()");

    // init the state
    state = new QuestionState();
    mediator.setQuestionState(state);

    // call the model
    state.question = model.getQuestion();
    state.option1 = model.getOption1();
    state.option2 = model.getOption2();
    state.option3 = model.getOption3();

    /*// reset state to tests
    state.answerCheated=false;
    state.optionClicked = false;
    state.option = 0;*/

    // update the view
    disableNextButton();
    view.get().resetResult();
  }


  @Override
  public void onRecreatedCalled() {
    Log.e(TAG, "onRecreatedCalled()");

    // update the state
    state = mediator.getQuestionState();

    // update the model
    model.setQuizIndex(state.quizIndex);
    Log.e(TAG, "index: "+ state.quizIndex);

    // update the view
    if(state.optionClicked){
      Log.e(TAG, "option: "+ state.option);
      Log.e(TAG, "correct: "+ model.isCorrectOption(state.option));
      view.get().updateResult(model.isCorrectOption(state.option));
      //onOptionButtonClicked(state.option);

    } else {
      view.get().resetResult();
    }
  }


  @Override
  public void onResumeCalled() {
    Log.e(TAG, "onResumeCalled()");

    // use passed state if is necessary
    CheatToQuestionState savedState = mediator.getCheatToQuestionState();
    //CheatToQuestionState savedState = getStateFromCheatScreen();
    if (savedState != null) {

      // update the state
      state.answerCheated = savedState.answerCheated;
    }

    // update the view
    if(state.answerCheated){
      state.answerCheated=false;

      if(!model.hasQuizFinished()) {
        onNextButtonClicked();

      } else {
        state.optionEnabled=false;
        view.get().displayQuestion(state);

        //boolean isCorrect = model.isCorrectOption(state.option);
        //view.get().updateReply(isCorrect);
      }

    } else {
      view.get().displayQuestion(state);
    }

  }


  @Override
  public void onDestroyCalled() {
    Log.e(TAG, "onDestroyCalled()");
  }

  @Override
  public void onOptionButtonClicked(int option) {
    Log.e(TAG, "onOptionButtonClicked()");

    state.optionClicked=true;
    state.option=option;

    enableNextButton();

    boolean isCorrect = model.isCorrectOption(option);
    if(isCorrect) {
      state.cheatEnabled=false;
    } else {
      state.cheatEnabled=true;
    }

    view.get().updateResult(isCorrect);
    onResumeCalled();
  }

  @Override
  public void onNextButtonClicked() {
    Log.e(TAG, "onNextButtonClicked()");

    //state.optionClicked=false;
    //state.option=0;

    model.updateQuizIndex();
    state.quizIndex=model.getQuizIndex();
    onCreatedCalled();
    onResumeCalled();
  }

  @Override
  public void onCheatButtonClicked() {
    Log.e(TAG, "onCheatButtonClicked()");

    QuestionToCheatState nextState = new QuestionToCheatState();
    nextState.answer = model.getAnswer();
    //passStateToCheatScreen(nextState);
    mediator.setQuestionToCheatState(nextState);

    view.get().navigateToCheatScreen();
  }

  private void disableNextButton() {
    state.optionEnabled=true;
    state.cheatEnabled=true;
    state.nextEnabled=false;

  }

  private void enableNextButton() {
    state.optionEnabled=false;

    if(!model.hasQuizFinished()) {
      state.nextEnabled=true;
    }
  }

//  private void passStateToCheatScreen(QuestionToCheatState state) {
//
//    mediator.setQuestionToCheatState(state);
//  }

//  private CheatToQuestionState getStateFromCheatScreen() {
//
//    CheatToQuestionState state = mediator.getCheatToQuestionState();
//    return state;
//  }


  @Override
  public void injectView(WeakReference<QuestionContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(QuestionContract.Model model) {
    this.model = model;
  }

}
