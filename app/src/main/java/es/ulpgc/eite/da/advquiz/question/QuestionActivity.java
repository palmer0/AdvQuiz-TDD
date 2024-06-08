package es.ulpgc.eite.da.advquiz.question;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.advquiz.R;
import es.ulpgc.eite.da.advquiz.cheat.CheatActivity;


public class QuestionActivity
    extends AppCompatActivity implements QuestionContract.View {

  public static String TAG = "AdvQuiz.QuestionActivity";

  private QuestionContract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_question);
    setTitle(R.string.question_screen_title);

    ((TextView) findViewById(R.id.nextButton)).setText(R.string.next_button);
    ((TextView) findViewById(R.id.cheatButton)).setText(R.string.cheat_button);

    // do the setup
    QuestionScreen.configure(this);

    if(savedInstanceState == null) {
      presenter.onCreatedCalled();

    } else {
      presenter.onRecreatedCalled();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

    // load the answer
    presenter.onResumeCalled();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    presenter.onDestroyCalled();
  }

  @Override
  public void navigateToCheatScreen() {

    Intent intent = new Intent(this, CheatActivity.class);
    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }

  @Override
  public void displayQuestionData(QuestionViewModel viewModel) {
    //Log.e(TAG, "displayQuestionData");

    // deal with the answer
    ((TextView) findViewById(R.id.questionTextView)).setText(viewModel.question);
    ((TextView) findViewById(R.id.option1Button)).setText(viewModel.option1);
    ((TextView) findViewById(R.id.option2Button)).setText(viewModel.option2);
    ((TextView) findViewById(R.id.option3Button)).setText(viewModel.option3);
    ((TextView) findViewById(R.id.resultTextView)).setText(viewModel.result);

    findViewById(R.id.option1Button).setEnabled(viewModel.optionEnabled);
    findViewById(R.id.option2Button).setEnabled(viewModel.optionEnabled);
    findViewById(R.id.option3Button).setEnabled(viewModel.optionEnabled);
    findViewById(R.id.nextButton).setEnabled(viewModel.nextEnabled);
    findViewById(R.id.cheatButton).setEnabled(viewModel.cheatEnabled);
  }

  /*
  @Override
  public void resetResult() {
    ((TextView) findViewById(R.id.resultTextView)).setText(R.string.empty_result);
  }

  @Override
  public void updateResult(boolean isCorrect) {
    if(isCorrect){
      ((TextView) findViewById(R.id.resultTextView)).setText(R.string.correct_result);
    } else {
      ((TextView) findViewById(R.id.resultTextView)).setText(R.string.incorrect_result);
    }
  }
  */


  public void onNextButtonClicked(View view) {
    presenter.onNextButtonClicked();
  }

  public void onCheatButtonClicked(View view) {
    presenter.onCheatButtonClicked();
  }

  public void onOptionButtonClicked(View view) {

    int option = Integer.valueOf((String) view.getTag());
    presenter.onOptionButtonClicked(option);
  }


  @Override
  public void injectPresenter(QuestionContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
