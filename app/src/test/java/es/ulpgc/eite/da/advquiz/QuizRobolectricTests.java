package es.ulpgc.eite.da.advquiz;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuizRobolectricTests {

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

    questionCtrler.create().resume().visible();
    updateQuestionActivityResources();

  }

  @After
  public void reset(){
    AppMediator.resetInstance();
  }

  private void updateQuestionActivityResources() {

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


  private void updateCheatActivityResources() {

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
  public void test01_whenQuestion1_thenCorrect() {

    //  GIVEN
    //  encontrándonos en pantalla Question después
    //  de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(questionField.getText().toString(), equalTo(quizArray[0]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));

    //  WHEN
    //  al pulsar botón Option correcto
    option3Button.performClick();

    //  THEN
    //  mostraremos mensaje Correct ya que la respuesta
    //  del usuario corresponde con respuesta correcta
    //  mostraremos botones Option y Cheat desactivados
    //  mostraremos botón Next activado
    assertThat(questionField.getText().toString(), equalTo(quizArray[0]));
    assertThat(resultField.getText().toString(), equalTo(correctResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(false));
    assertThat(nextButton.isEnabled(), equalTo(true));

  }



  @Test
  public void test02_whenQuestion1Correct_thenRotate() {

    //  GIVEN
    //  encontrándonos en pantalla Question después
    //  de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(questionField.getText().toString(), equalTo(quizArray[0]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));

    //  WHEN
    //  al pulsar botón Option correcto y girar la pantalla
    option3Button.performClick();
    rotateQuestionActivity();

    //  THEN
    //  mostraremos mensaje Correct ya que la respuesta
    //  del usuario corresponde con respuesta correcta
    //  mostraremos botones Option y Cheat desactivados
    //  mostraremos botón Next activado
    updateQuestionActivityResources();
    assertThat(questionField.getText().toString(), equalTo(quizArray[0]));
    assertThat(resultField.getText().toString(), equalTo(correctResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(false));
    assertThat(nextButton.isEnabled(), equalTo(true));

  }



  @Test
  public void test03_whenQuestion1_thenIncorrect() {

    //  GIVEN
    //  encontrándonos en pantalla Question después
    //  de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(questionField.getText().toString(), equalTo(quizArray[0]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));

    //  WHEN
    //  al pulsar botón Option incorrecto
    option2Button.performClick();

    //  THEN
    //  mostraremos mensaje Incorrect ya que la respuesta
    //  del usuario corresponde con respuesta incorrecta
    //  mostraremos botón Option desactivado
    //  mostraremos botones Next y Cheat activados
    assertThat(questionField.getText().toString(), equalTo(quizArray[0]));
    assertThat(resultField.getText().toString(), equalTo(incorrectResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(true));
  }


  @Test
  public void test04_whenQuestion1Correct_thenNext() {

    //  GIVEN
    //  encontrándonos en pantalla Question después
    //  de responder a pregunta del cuestionario
    //  mostraremos mensaje Correct según  la respuesta del usuario
    //  mostraremos botones Option y Cheat desactivados
    //  mostraremos botón Next activado
    option3Button.performClick();
    assertThat(questionField.getText().toString(), equalTo(quizArray[0]));
    assertThat(resultField.getText().toString(), equalTo(correctResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(false));
    assertThat(nextButton.isEnabled(), equalTo(true));

    //  WHEN
    //  al pulsar botón Next
    nextButton.performClick();

    //  THEN
    //  mostraremos pantalla Question con siguiente pregunta  ya cargada
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(questionField.getText().toString(), equalTo(quizArray[5]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));

  }


  @Test
  public void test05_whenQuestion2_thenRotate() {

    //  GIVEN
    //  encontrándonos en pantalla Question después
    //  de responder a pregunta del cuestionario
    //  mostraremos mensaje Correct según  la respuesta del usuario
    //  mostraremos botones Option y Cheat desactivados
    //  mostraremos botón Next activado
    option3Button.performClick();
    assertThat(questionField.getText().toString(), equalTo(quizArray[0]));
    assertThat(resultField.getText().toString(), equalTo(correctResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(false));
    assertThat(nextButton.isEnabled(), equalTo(true));

    //  WHEN
    //  al pulsar botón Next y girar la pantalla
    nextButton.performClick();
    rotateQuestionActivity();

    //  THEN
    //  mostraremos pantalla Question con siguiente pregunta  ya cargada
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    updateQuestionActivityResources();
    assertThat(questionField.getText().toString(), equalTo(quizArray[5]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));

  }



  @Test
  public void test06_whenQuestion1Incorrect_thenNext() {

    //  GIVEN
    //  encontrándonos en pantalla Question después
    //  de responder a pregunta del cuestionario
    //  mostraremos mensaje Incorrect según  la respuesta del usuario
    //  mostraremos botón Option desactivado
    //  mostraremos botones Next y Cheat activados
    option2Button.performClick();
    assertThat(questionField.getText().toString(), equalTo(quizArray[0]));
    assertThat(resultField.getText().toString(), equalTo(incorrectResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(true));

    //  WHEN
    //  al pulsar botón Next
    nextButton.performClick();

    //  THEN
    //  mostraremos pantalla Question con siguiente pregunta  ya cargada
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(questionField.getText().toString(), equalTo(quizArray[5]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));
  }

  @Test
  public void test07_whenQuestion1_thenCheat() {

    //  GIVEN
    //  encontrándonos en pantalla Question sin haber respondido
    //  a pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(questionField.getText().toString(), equalTo(quizArray[0]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));

    //  WHEN
    //  al pulsar botón Cheat
    cheatButton.performClick();
    cheatCtrler.create().resume().visible();

    //  THEN
    //  visualizaremos pantalla Cheat donde se nos pedirá confirmación
    //  antes de mostrar respuesta correcta
    //  mostraremos botones Yes y NO activados
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(emptyAnswer));
    assertThat(yesButton.isEnabled(), equalTo(true));
    assertThat(noButton.isEnabled(), equalTo(true));
  }



  @Test
  public void test08_whenQuestion1Cheated_thenRotate() {

    //  GIVEN
    //  encontrándonos en pantalla Question sin haber respondido
    //  a pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(questionField.getText().toString(), equalTo(quizArray[0]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));

    //  WHEN
    //  al pulsar botón Cheat y girar la pantalla
    cheatButton.performClick();
    cheatCtrler.create().resume().visible();
    rotateQuestionActivity();
    rotateCheatActivity();

    //  THEN
    //  visualizaremos pantalla Cheat donde se nos pedirá confirmación
    //  antes de mostrar respuesta correcta
    //  mostraremos botones Yes y NO activados
    updateQuestionActivityResources();
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(emptyAnswer));
    assertThat(yesButton.isEnabled(), equalTo(true));
    assertThat(noButton.isEnabled(), equalTo(true));
  }

  @Test
  public void test09_whenQuestion1Incorrect_thenCheat() {

    //  GIVEN
    //  encontrándonos en pantalla Question después
    //  de responder a pregunta del cuestionario
    //  mostraremos mensaje Incorrect segun la respuesta del usuario
    //  mostraremos botones Next y Cheat activados
    //  mostraremos botón Option desactivado
    option2Button.performClick();
    assertThat(questionField.getText().toString(), equalTo(quizArray[0]));
    assertThat(resultField.getText().toString(), equalTo(incorrectResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(true));

    //  WHEN
    //  al pulsar botón Cheat
    cheatButton.performClick();
    cheatCtrler.create().resume().visible();

    //  THEN
    //  visualizaremos pantalla Cheat donde se nos pedirá confirmación
    //  antes de mostrar respuesta correcta
    //  mostraremos botones Yes y NO activados
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(emptyAnswer));
    assertThat(yesButton.isEnabled(), equalTo(true));
    assertThat(noButton.isEnabled(), equalTo(true));
  }



  @Test
  public void test10_whenQuestion1Cheated_thenNo() {

    //  GIVEN
    //  encontrándonos en pantalla Cheat sin haber respondido
    //  a  pregunta del cuestionario en pantalla Question
    //  mostraremos botones Yes y NO activados
    cheatButton.performClick();
    cheatCtrler.create().resume().visible();
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(emptyAnswer));
    assertThat(yesButton.isEnabled(), equalTo(true));
    assertThat(noButton.isEnabled(), equalTo(true));

    //  WHEN
    //  al pulsar botón No
    noButton.performClick();

    //  THEN
    //  volveremos a pantalla Question
    //  mostraremos pregunta del cuestionario existente
    //  antes de iniciar pantalla Cheat
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(questionField.getText().toString(), equalTo(quizArray[0]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));
  }



  @Test
  public void test11_whenQuestion1CheatedNo_thenRotate() {

    //  GIVEN
    //  encontrándonos en pantalla Cheat sin haber respondido
    //  a  pregunta del cuestionario en pantalla Question
    //  mostraremos botones Yes y NO activados
    cheatButton.performClick();
    cheatCtrler.create().resume().visible();
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(emptyAnswer));
    assertThat(yesButton.isEnabled(), equalTo(true));
    assertThat(noButton.isEnabled(), equalTo(true));

    //  WHEN
    //  al pulsar botón No y girar la pantalla
    noButton.performClick();
    rotateQuestionActivity();

    //  THEN
    //  volveremos a pantalla Question
    //  mostraremos pregunta del cuestionario existente
    //  antes de iniciar pantalla Cheat
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    updateQuestionActivityResources();
    assertThat(questionField.getText().toString(), equalTo(quizArray[0]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));
  }


  @Test
  public void test12_whenQuestion1IncorrectCheated_thenNo() {

    //  GIVEN
    //  encontrándonos en pantalla Cheat después de responder
    //  a pregunta del cuestionario en pantalla Question
    //  mostraremos mensaje Incorrect segun la respuesta del usuario
    //  mostraremos botones Yes y NO activados
    option2Button.performClick();
    cheatButton.performClick();
    cheatCtrler.create().resume().visible();
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(emptyAnswer));
    assertThat(yesButton.isEnabled(), equalTo(true));
    assertThat(noButton.isEnabled(), equalTo(true));

    //  WHEN
    //  al pulsar botón No
    noButton.performClick();

    //  THEN
    //  volveremos a pantalla Question donde mostraremos pregunta
    //  del cuestionario existente antes de iniciar pantalla Cheat
    //  mostraremos botones Next y Cheat activados
    //  mostraremos botón Option desactivado
    assertThat(questionField.getText().toString(), equalTo(quizArray[0]));
    assertThat(resultField.getText().toString(), equalTo(incorrectResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(true));
  }



  @Test
  public void test13_whenQuestion1IncorrectCheatedNo_thenRotate() {

    //  GIVEN
    //  encontrándonos en pantalla Cheat después de responder
    //  a pregunta del cuestionario en pantalla Question
    //  mostraremos mensaje Incorrect segun la respuesta del usuario
    //  mostraremos botones Yes y NO activados
    option2Button.performClick();
    cheatButton.performClick();
    cheatCtrler.create().resume().visible();
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(emptyAnswer));
    assertThat(yesButton.isEnabled(), equalTo(true));
    assertThat(noButton.isEnabled(), equalTo(true));

    //  WHEN
    //  al pulsar botón No y girar la pantalla
    noButton.performClick();
    rotateQuestionActivity();

    //  THEN
    //  volveremos a pantalla Question donde mostraremos pregunta
    //  del cuestionario existente antes de iniciar pantalla Cheat
    //  mostraremos botones Next y Cheat activados
    //  mostraremos botón Option desactivado
    updateQuestionActivityResources();
    assertThat(questionField.getText().toString(), equalTo(quizArray[0]));
    assertThat(resultField.getText().toString(), equalTo(incorrectResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(true));
  }



  @Test
  public void test14_whenQuestion1Cheated_thenYes() {

    //  GIVEN
    //  encontrándonos en pantalla Cheat sin haber respondido
    //  a  pregunta del cuestionario en pantalla Question
    //  mostraremos botones Yes y NO activados
    cheatButton.performClick();
    cheatCtrler.create().resume().visible();
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(emptyAnswer));
    assertThat(yesButton.isEnabled(), equalTo(true));
    assertThat(noButton.isEnabled(), equalTo(true));

    //  WHEN
    //  al pulsar botón Yes
    yesButton.performClick();

    //  THEN
    //  visualizaremos respuesta correcta a pregunta
    //  del cuestionario mostrada actualmente en pantalla Question
    //  mostraremos botones Yes y NO desactivados
    updateQuestionActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(quizArray[3]));
    assertThat(yesButton.isEnabled(), equalTo(false));
    assertThat(noButton.isEnabled(), equalTo(false));
  }



  @Test
  public void test15_whenQuestion1CheatedYes_thenRotate() {

    //  GIVEN
    //  encontrándonos en pantalla Cheat sin haber respondido
    //  a  pregunta del cuestionario en pantalla Question
    //  mostraremos botones Yes y NO activados
    cheatButton.performClick();
    cheatCtrler.create().resume().visible();
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(emptyAnswer));
    assertThat(yesButton.isEnabled(), equalTo(true));
    assertThat(noButton.isEnabled(), equalTo(true));

    //  WHEN
    //  al pulsar botón Yes y girar la pantalla
    yesButton.performClick();
    rotateQuestionActivity();
    rotateCheatActivity();

    //  THEN
    //  visualizaremos respuesta correcta a pregunta
    //  del cuestionario mostrada actualmente en pantalla Question
    //  mostraremos botones Yes y NO desactivados
    updateQuestionActivityResources();
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(quizArray[3]));
    assertThat(yesButton.isEnabled(), equalTo(false));
    assertThat(noButton.isEnabled(), equalTo(false));
  }


  @Test
  public void test16_whenQuestion1IncorrectCheated_thenYes() {

    //  GIVEN
    //  encontrándonos en pantalla Cheat después
    //  de responder a pregunta del cuestionario
    //  mostraremos mensaje Incorrect segun la respuesta del usuario
    //  mostraremos botones Yes y NO activados
    option2Button.performClick();
    cheatButton.performClick();
    cheatCtrler.create().resume().visible();
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(emptyAnswer));
    assertThat(yesButton.isEnabled(), equalTo(true));
    assertThat(noButton.isEnabled(), equalTo(true));


    //  WHEN
    //  al pulsar botón Yes
    yesButton.performClick();

    //  THEN
    //  visualizaremos respuesta correcta a pregunta
    //  del cuestionario mostrada actualmente en pantalla Question
    //  mostraremos botones Yes y NO desactivados
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(quizArray[3]));
    assertThat(yesButton.isEnabled(), equalTo(false));
    assertThat(noButton.isEnabled(), equalTo(false));
  }



  @Test
  public void test17_whenQuestion1IncorrectCheatedYes_thenRotate() {

    //  GIVEN
    //  encontrándonos en pantalla Cheat después
    //  de responder a pregunta del cuestionario
    //  mostraremos mensaje Incorrect segun la respuesta del usuario
    //  mostraremos botones Yes y NO activados
    option2Button.performClick();
    cheatButton.performClick();
    cheatCtrler.create().resume().visible();
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(emptyAnswer));
    assertThat(yesButton.isEnabled(), equalTo(true));
    assertThat(noButton.isEnabled(), equalTo(true));


    //  WHEN
    //  al pulsar botón Yes y girar la pantalla
    yesButton.performClick();
    rotateQuestionActivity();
    rotateCheatActivity();

    //  THEN
    //  visualizaremos respuesta correcta a pregunta
    //  del cuestionario mostrada actualmente en pantalla Question
    //  mostraremos botones Yes y NO desactivados
    updateQuestionActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(quizArray[3]));
    assertThat(yesButton.isEnabled(), equalTo(false));
    assertThat(noButton.isEnabled(), equalTo(false));
  }


  @Test
  public void test18_whenQuestion1Cheated_thenYesAndBack() {

    //  GIVEN
    //  encontrándonos en pantalla Cheat sin haber respondido
    //  a  pregunta del cuestionario en pantalla Question
    //  mostraremos botones Yes y NO activados
    cheatButton.performClick();
    cheatCtrler.create().resume().visible();
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(emptyAnswer));
    assertThat(yesButton.isEnabled(), equalTo(true));
    assertThat(noButton.isEnabled(), equalTo(true));

    //  WHEN
    //  al pulsar botón Yes y luego el botón Back
    yesButton.performClick();
    CheatActivity activity2 = cheatCtrler.get();
    activity2.onBackPressed();
    questionCtrler.resume();

    //  THEN
    //  volveremos a pantalla Question donde mostraremos
    //  pregunta siguiente del cuestionario antes de iniciar pantalla Cheat
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(questionField.getText().toString(), equalTo(quizArray[5]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));
  }




  @Test
  public void test19_whenQuestion1CheatedYesAndBack_thenRotate() {

    //  GIVEN
    //  encontrándonos en pantalla Cheat sin haber respondido
    //  a  pregunta del cuestionario en pantalla Question
    //  mostraremos botones Yes y NO activados
    cheatButton.performClick();
    cheatCtrler.create().resume().visible();
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(emptyAnswer));
    assertThat(yesButton.isEnabled(), equalTo(true));
    assertThat(noButton.isEnabled(), equalTo(true));

    //  WHEN
    //  al pulsar botón Yes, luego el botón Back y girar la pantalla
    yesButton.performClick();
    CheatActivity activity2 = cheatCtrler.get();
    activity2.onBackPressed();
    questionCtrler.resume();
    rotateQuestionActivity();

    //  THEN
    //  volveremos a pantalla Question donde mostraremos
    //  pregunta siguiente del cuestionario antes de iniciar pantalla Cheat
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    updateQuestionActivityResources();
    assertThat(questionField.getText().toString(), equalTo(quizArray[5]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));
  }

  @Test
  public void test20_whenQuestion1IncorrectCheated_thenYesAndBack() {

    //  GIVEN
    //  encontrándonos en pantalla Cheat después
    //  de responder a pregunta del cuestionario en pantalla Question
    //  mostraremos mensaje Incorrect segun la respuesta del usuario
    //  mostraremos botones Yes y NO activados
    option2Button.performClick();
    cheatButton.performClick();
    cheatCtrler.create().resume().visible();
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(emptyAnswer));
    assertThat(yesButton.isEnabled(), equalTo(true));
    assertThat(noButton.isEnabled(), equalTo(true));

    //  WHEN
    //  al pulsar botón Yes y luego el botón Back
    yesButton.performClick();
    CheatActivity activity2 = cheatCtrler.get();
    activity2.onBackPressed();
    questionCtrler.resume();

    //  THEN
    //  volveremos a pantalla Question donde mostraremos pregunta
    //  siguiente del cuestionario antes de iniciar pantalla Cheat
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    assertThat(questionField.getText().toString(), equalTo(quizArray[5]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));

  }



  @Test
  public void test21_whenQuestion2_thenCorrect() {

    //  GIVEN
    //  encontrándonos en pantalla Question
    //  después de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    option2Button.performClick();
    nextButton.performClick();
    assertThat(questionField.getText().toString(), equalTo(quizArray[5]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));

    //  WHEN
    //  al pulsar botón Option correcto
    option3Button.performClick();

    //  THEN
    //  mostraremos mensaje Correct segun la respuesta del usuario
    //  mostraremos botones Option, Next y Cheat desactivados
    assertThat(questionField.getText().toString(), equalTo(quizArray[5]));
    assertThat(resultField.getText().toString(), equalTo(correctResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(false));
    assertThat(nextButton.isEnabled(), equalTo(true));
  }



  @Test
  public void test22_whenQuestion2Correct_thenRotate() {

    //  GIVEN
    //  encontrándonos en pantalla Question
    //  después de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    option2Button.performClick();
    nextButton.performClick();
    assertThat(questionField.getText().toString(), equalTo(quizArray[5]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));

    //  WHEN
    //  al pulsar botón Option correcto y girar la pantalla
    option3Button.performClick();
    rotateQuestionActivity();

    //  THEN
    //  mostraremos mensaje Correct segun la respuesta del usuario
    //  mostraremos botones Option, Next y Cheat desactivados
    updateQuestionActivityResources();
    assertThat(questionField.getText().toString(), equalTo(quizArray[5]));
    assertThat(resultField.getText().toString(), equalTo(correctResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(false));
    assertThat(nextButton.isEnabled(), equalTo(true));
  }

  @Test
  public void test23_whenQuestion8_thenCorrect() {

    //  GIVEN
    //  encontrándonos en pantalla Question
    //  después de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    for(int i=1; i<=7; i++) {
      option2Button.performClick();
      nextButton.performClick();
    }

    assertThat(questionField.getText().toString(), equalTo(quizArray[35]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));

    //  WHEN
    //  al pulsar botón Option correcto
    option1Button.performClick();

    //  THEN
    //  mostraremos mensaje Correct segun la respuesta del usuario
    //  mostraremos botones Option, Next y Cheat desactivados
    assertThat(questionField.getText().toString(), equalTo(quizArray[35]));
    assertThat(resultField.getText().toString(), equalTo(correctResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(false));
    assertThat(nextButton.isEnabled(), equalTo(true));
  }



  @Test
  public void test24_whenQuestion8Correct_thenRotate() {

    //  GIVEN
    //  encontrándonos en pantalla Question
    //  después de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    for(int i=1; i<=7; i++) {
      option2Button.performClick();
      nextButton.performClick();
    }

    assertThat(questionField.getText().toString(), equalTo(quizArray[35]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));

    //  WHEN
    //  al pulsar botón Option correcto y girar la pantalla
    option1Button.performClick();
    rotateQuestionActivity(); // ???

    //  THEN
    //  mostraremos mensaje Correct segun la respuesta del usuario
    //  mostraremos botones Option, Next y Cheat desactivados
    updateQuestionActivityResources();
    assertThat(questionField.getText().toString(), equalTo(quizArray[35]));
    assertThat(resultField.getText().toString(), equalTo(correctResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(false));
    assertThat(nextButton.isEnabled(), equalTo(true));
  }

  @Test
  public void test25_whenQuestion10_thenCorrect() {

    //  GIVEN
    //  encontrándonos en pantalla Question
    //  después de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    for(int i=1; i<=9; i++) {
      option2Button.performClick();
      nextButton.performClick();
    }

    assertThat(questionField.getText().toString(), equalTo(quizArray[45]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));

    //  WHEN
    //  al pulsar botón Option correcto
    option1Button.performClick();

    //  THEN
    //  mostraremos mensaje Correct segun la respuesta del usuario
    //  mostraremos botones Option, Next y Cheat desactivados
    assertThat(questionField.getText().toString(), equalTo(quizArray[45]));
    assertThat(resultField.getText().toString(), equalTo(correctResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(false));
    assertThat(nextButton.isEnabled(), equalTo(false));
  }


  @Test
  public void test26_whenQuestion10Correct_thenRotate() {

    //  GIVEN
    //  encontrándonos en pantalla Question
    //  después de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    for(int i=1; i<=9; i++) {
      option2Button.performClick();
      nextButton.performClick();
    }

    assertThat(questionField.getText().toString(), equalTo(quizArray[45]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));

    //  WHEN
    //  al pulsar botón Option correcto y girar la pantalla
    option1Button.performClick();
    rotateQuestionActivity(); // ???

    //  THEN
    //  mostraremos mensaje Correct segun la respuesta del usuario
    //  mostraremos botones Option, Next y Cheat desactivados
    updateQuestionActivityResources();
    assertThat(questionField.getText().toString(), equalTo(quizArray[45]));
    assertThat(resultField.getText().toString(), equalTo(correctResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(false));
    assertThat(nextButton.isEnabled(), equalTo(false));
  }

  @Test
  public void test27_whenQuestion10_thenIncorrect() {

    //  GIVEN
    //  encontrándonos en pantalla Question
    //  después de cargar pregunta del cuestionario
    //  mostraremos botones Option y Cheat activados
    //  mostraremos botón Next desactivado
    for(int i=1; i<=9; i++) {
      option2Button.performClick();
      nextButton.performClick();
    }

    assertThat(questionField.getText().toString(), equalTo(quizArray[45]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(true));
    assertThat(option2Button.isEnabled(), equalTo(true));
    assertThat(option3Button.isEnabled(), equalTo(true));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));

    //  WHEN
    //  al pulsar botón Option incorrecto
    option2Button.performClick();

    //  THEN
    //  mostraremos mensaje Incorrect segun la respuesta del usuario
    //  mostraremos botones Option y Next desactivados
    //  mostraremos botón Cheat activado
    assertThat(questionField.getText().toString(), equalTo(quizArray[45]));
    assertThat(resultField.getText().toString(), equalTo(incorrectResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));
  }

  @Test
  public void test28_whenQuestion10Cheated_thenYesAndBack() {

    //  GIVEN
    //  encontrándonos en pantalla Cheat sin responder
    //  a  pregunta del cuestionario en pantalla Question
    //  mostraremos botones Yes y NO activados
    for(int i=1; i<=9; i++) {
      option2Button.performClick();
      nextButton.performClick();
    }

    cheatButton.performClick();
    cheatCtrler.create().resume().visible();
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(emptyAnswer));
    assertThat(yesButton.isEnabled(), equalTo(true));
    assertThat(noButton.isEnabled(), equalTo(true));

    //  WHEN
    //  al pulsar boton Yes y luego Back
    yesButton.performClick();
    CheatActivity activity2 = cheatCtrler.get();
    activity2.onBackPressed();
    questionCtrler.resume();

    //  THEN
    //  volveremos a pantalla Question donde mostraremos pregunta
    //  del cuestionario existente antes de iniciar pantalla Cheat
    //  mostraremos botón Cheat activado
    //  mostraremos botones Option y Next desactivados
    assertThat(questionField.getText().toString(), equalTo(quizArray[45]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));
  }


  @Test
  public void test29_whenQuestion10CheatedYesAndBack_thenRotate() {

    //  GIVEN
    //  encontrándonos en pantalla Cheat sin responder
    //  a  pregunta del cuestionario en pantalla Question
    //  mostraremos botones Yes y NO activados
    for(int i=1; i<=9; i++) {
      option2Button.performClick();
      nextButton.performClick();
    }

    cheatButton.performClick();
    cheatCtrler.create().resume().visible();
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(emptyAnswer));
    assertThat(yesButton.isEnabled(), equalTo(true));
    assertThat(noButton.isEnabled(), equalTo(true));

    //  WHEN
    //  al pulsar boton Yes, luego Back y girar la pantalla
    yesButton.performClick();
    CheatActivity activity2 = cheatCtrler.get();
    activity2.onBackPressed();
    questionCtrler.resume();
    rotateQuestionActivity();

    //  THEN
    //  volveremos a pantalla Question donde mostraremos pregunta
    //  del cuestionario existente antes de iniciar pantalla Cheat
    //  mostraremos botón Cheat activado
    //  mostraremos botones Option y Next desactivados
    updateQuestionActivityResources();
    assertThat(questionField.getText().toString(), equalTo(quizArray[45]));
    assertThat(resultField.getText().toString(), equalTo(emptyResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));
  }

  @Test
  public void test30_whenQuestion10IncorrectCheated_thenYesAndBack() {

    //  GIVEN
    //  encontrándonos en pantalla Cheat después de responder
    //  a pregunta del cuestionario en pantalla Question
    //  mostraremos mensaje Incorrect segun la respuesta del usuario
    //  mostraremos botones Yes y NO activados
    for(int i=1; i<=9; i++) {
      option2Button.performClick();
      nextButton.performClick();
    }

    option2Button.performClick();
    cheatButton.performClick();
    cheatCtrler.create().resume().visible();
    updateCheatActivityResources();
    assertThat(warningField.getText().toString(), equalTo(warningMessage));
    assertThat(answerField.getText().toString(), equalTo(emptyAnswer));
    assertThat(yesButton.isEnabled(), equalTo(true));
    assertThat(noButton.isEnabled(), equalTo(true));

    //  WHEN
    //  al pulsar botones Yes y Back
    yesButton.performClick();
    CheatActivity activity2 = cheatCtrler.get();
    activity2.onBackPressed();
    questionCtrler.resume();

    //  THEN
    //  volveremos a pantalla Question donde mostraremos pregunta
    //  del cuestionario existente antes de iniciar pantalla Cheat
    //  mostraremos botón Cheat activado
    //  mostraremos botones Next y Option desactivado
    assertThat(questionField.getText().toString(), equalTo(quizArray[45]));
    assertThat(resultField.getText().toString(), equalTo(incorrectResult));
    assertThat(option1Button.isEnabled(), equalTo(false));
    assertThat(option2Button.isEnabled(), equalTo(false));
    assertThat(option3Button.isEnabled(), equalTo(false));
    assertThat(cheatButton.isEnabled(), equalTo(true));
    assertThat(nextButton.isEnabled(), equalTo(false));
  }


}
