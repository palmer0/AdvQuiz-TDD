package es.ulpgc.eite.da.advquiz;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import es.ulpgc.eite.da.advquiz.app.AppMediator;
import es.ulpgc.eite.da.advquiz.cheat.CheatActivity;
import es.ulpgc.eite.da.advquiz.question.QuestionActivity;


@RunWith(RobolectricTestRunner.class)
//@Config(manifest=Config.NONE)
@Config(sdk = 28)
public class ExtraRobolectricTest {

  ActivityController<QuestionActivity> questionCtrler;
  ActivityController<CheatActivity> cheatCtrler;

  TextView questionField, resultField;
  TextView warningField, answerField;
  Button option1Button, option2Button, option3Button;
  Button nextButton, cheatButton;
  Button yesButton, noButton;

  String[] quizArray;
  String emptyResult, correctResult, incorrectResult;
  String emptyAnswer, warningMessage;

  @Before
  public void setup(){

    //AppMediator.resetInstance();

    questionCtrler = Robolectric.buildActivity(QuestionActivity.class);
    cheatCtrler = Robolectric.buildActivity(CheatActivity.class);

    questionCtrler.create().resume().visible().get();
    initQuestionActivityResources();

  }

  @After
  public void reset(){
    AppMediator.resetInstance();
  }

  private void initQuestionActivityResources() {

    QuestionActivity activity = questionCtrler.get();

    quizArray = activity.getResources().getStringArray(R.array.quiz_array);

    emptyResult =activity.getResources().getString(R.string.empty_result);
    correctResult =activity.getResources().getString(R.string.correct_result);
    incorrectResult =activity.getResources().getString(R.string.incorrect_result);

    questionField = activity.findViewById(R.id.questionTextView);
    resultField = activity.findViewById(R.id.resultTextView);
    option1Button = activity.findViewById(R.id.option1Button);
    option2Button = activity.findViewById(R.id.option2Button);
    option3Button = activity.findViewById(R.id.option3Button);
    nextButton = activity.findViewById(R.id.nextButton);
    cheatButton = activity.findViewById(R.id.cheatButton);
  }


  private void initCheatActivityResources() {

    CheatActivity activity = cheatCtrler.get();

    warningMessage =activity.getResources().getString(R.string.warning_message);
    emptyAnswer =activity.getResources().getString(R.string.empty_answer);

    warningField = activity.findViewById(R.id.warningTextView);
    answerField = activity.findViewById(R.id.answerTextView);
    yesButton = activity.findViewById(R.id.yesButton);
    noButton = activity.findViewById(R.id.noButton);

  }


  private void rotateQuestionActivity() {

    Bundle bundle = new Bundle();

    questionCtrler
        .saveInstanceState(bundle)
        .pause()
        .stop()
        .destroy();

    questionCtrler = Robolectric.buildActivity(QuestionActivity.class)
        .create(bundle)
        .restoreInstanceState(bundle)
        .resume()
        .visible();

  }

  private void rotateCheatActivity() {

    Bundle bundle = new Bundle();

    cheatCtrler
        .saveInstanceState(bundle)
        .pause()
        .stop()
        .destroy();

    cheatCtrler = Robolectric.buildActivity(CheatActivity.class)
        .create(bundle)
        .restoreInstanceState(bundle)
        .resume()
        .visible();

  }


  @Test
  public void test() {

    // GIVEN

    cheatButton.performClick();
    cheatCtrler.create().resume().visible().visible().get();
    initCheatActivityResources();
    yesButton.performClick();


    // THEN

    assertThat(
        answerField.getText().toString(), equalTo("América del Sur")
    );

    // WHEN

    CheatActivity cheatActivity = cheatCtrler.get();
    cheatActivity.onBackPressed();
    questionCtrler.resume();


    // THEN

    initQuestionActivityResources();
    assertThat(
        questionField.getText().toString(),
        equalTo("¿Qué país es el segundo más grande del mundo?")
    );
    assertThat(resultField.getText().toString(), equalTo("???"));


    // WHEN

    cheatButton.performClick();
    //cheatCtrler.pause().stop().destroy();
    cheatCtrler = Robolectric.buildActivity(CheatActivity.class);
    cheatCtrler.create().resume().visible();

    initCheatActivityResources();
    yesButton.performClick();

    // THEN

    assertThat(
        answerField.getText().toString(), equalTo("Canadá")
    );

    // WHEN

    cheatActivity = cheatCtrler.get();
    cheatActivity.onBackPressed();
    questionCtrler.resume();


    // THEN

    initQuestionActivityResources();
    assertThat(
        questionField.getText().toString(),
        equalTo("¿Cómo se llama la tercera isla más grande del mundo?")
    );
    assertThat(resultField.getText().toString(), equalTo("???"));


    // WHEN

    cheatButton.performClick();
    //cheatCtrler.pause().stop().destroy();
    cheatCtrler = Robolectric.buildActivity(CheatActivity.class);
    cheatCtrler.create().resume().visible();

    // THEN

    initCheatActivityResources();
    assertThat(
        answerField.getText().toString(),
        equalTo("???")
    );

    // WHEN

    rotateQuestionActivity();
    rotateCheatActivity();

    // THEN

    assertThat(answerField.getText().toString(), equalTo("???"));

    // WHEN

    initCheatActivityResources();
    yesButton.performClick();


    // THEN

    initCheatActivityResources();
    assertThat(answerField.getText().toString(), equalTo("Borneo"));


    // WHEN

    rotateQuestionActivity();
    rotateCheatActivity();


    // THEN

    initCheatActivityResources();
    assertThat(answerField.getText().toString(), equalTo("Borneo"));


    // WHEN

    cheatActivity = cheatCtrler.get();
    cheatActivity.onBackPressed();
    questionCtrler.resume();


    // THEN

    initQuestionActivityResources();
    assertThat(
        questionField.getText().toString(),
        equalTo("¿Qué porcentaje de la superficie de la Tierra no es agua?")
    );
    assertThat(resultField.getText().toString(), equalTo("???"));

  }


}
