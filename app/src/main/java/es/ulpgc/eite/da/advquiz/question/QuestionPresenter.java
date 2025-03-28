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
  }

  @Override
  public void onCreatedCalled() {
    Log.e(TAG, "onCreatedCalled");

    // init the state
    state = new QuestionState();

    initViewData();
  }

  private void initViewData() {

    // call the model
    state.question = model.getQuestion();
    state.option1 = model.getOption1();
    state.option2 = model.getOption2();
    state.option3 = model.getOption3();

    // reset state
    state.answerCheated=false;
    state.option = 0;

    // update the view
    disableNextButton();
    state.result = model.getEmptyResultText();
  }

  @Override
  public void onRecreatedCalled() {
    Log.e(TAG, "onRecreatedCalled");

    // update the state
    state = mediator.getQuestionState();

    // update the model
    model.setQuizIndex(state.quizIndex);
    Log.e(TAG, "index: "+ state.quizIndex);

    // update the view
    if(state.option != 0){

      boolean isCorrect = model.isCorrectOption(state.option);
      Log.e(TAG, "option: "+ state.option);
      Log.e(TAG, "correct: "  + isCorrect);

      if(isCorrect) {
        state.result = model.getCorrectResultText();

      } else {
        state.result = model.getIncorrectResultText();
      }

    } else {

      state.result = model.getEmptyResultText();
    }
  }


  @Override
  public void onResumeCalled() {
    Log.e(TAG, "onResumeCalled");

    // use passed state if is necessary
    CheatToQuestionState savedState = mediator.getCheatToQuestionState();
    if (savedState != null) {

      // update the state
      state.answerCheated = savedState.answerCheated;
    }

    // update the view
    if(state.answerCheated){
      if(!model.hasQuizFinished()) {
        state.answerCheated=false;
        onNextButtonClicked();
        return;

      } else {
        state.optionEnabled=false;
      }

    }

    view.get().displayQuestionData(state);

    //Log.e(TAG, "index: "+ state.quizIndex);

  }



  @Override
  public void onPauseCalled() {
    Log.e(TAG, "onPauseCalled");

    mediator.setQuestionState(state);
  }

  @Override
  public void onDestroyCalled() {
    Log.e(TAG, "onDestroyCalled");
  }

  @Override
  public void onOptionButtonClicked(int option) {
    Log.e(TAG, "onOptionButtonClicked");

    state.option=option;

    enableNextButton();

    boolean isCorrect = model.isCorrectOption(option);
    if(isCorrect) {
      state.cheatEnabled=false;
      state.result = model.getCorrectResultText();

    } else {
      state.cheatEnabled=true;
      state.result = model.getIncorrectResultText();
    }

    view.get().displayQuestionData(state);
  }

  @Override
  public void onNextButtonClicked() {
    Log.e(TAG, "onNextButtonClicked");

    model.incrQuizIndex();
    state.quizIndex=model.getQuizIndex();
    Log.e(TAG, "index: "+ state.quizIndex);
    initViewData();
    view.get().displayQuestionData(state);
  }

  @Override
  public void onCheatButtonClicked() {
    Log.e(TAG, "onCheatButtonClicked");

    QuestionToCheatState newState = new QuestionToCheatState();
    newState.answer = model.getCorrectAnswer();
    mediator.setQuestionToCheatState(newState);

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

  @Override
  public void injectView(WeakReference<QuestionContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(QuestionContract.Model model) {
    this.model = model;
  }

}
