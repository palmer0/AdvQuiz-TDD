package es.ulpgc.eite.da.advquiz;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.not;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.RemoteException;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.uiautomator.UiDevice;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import es.ulpgc.eite.da.advquiz.app.AppMediator;
import es.ulpgc.eite.da.advquiz.question.QuestionActivity;


@LargeTest
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuizEspressoTests {


  @Rule
  public ActivityTestRule<QuestionActivity> testRule =
      new ActivityTestRule(QuestionActivity.class, true, false);


  private Activity activity;

  Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

  String[] quizArray = context.getResources().getStringArray(R.array.quiz_array);
  String correctResult = context.getString(R.string.correct_result);
  String incorrectResult = context.getString(R.string.incorrect_result);
  String emptyResult = context.getString(R.string.empty_result);
  String warningMessage = context.getString(R.string.warning_message);
  String emptyAnswer = context.getString(R.string.empty_answer);


  @Before
  public void setUp() {

    AppMediator.resetInstance();

    try {

      UiDevice device = UiDevice.getInstance(getInstrumentation());
      device.setOrientationNatural();

    } catch (RemoteException e) {
    }

    testRule.launchActivity(new Intent());
    activity = testRule.getActivity();
  }

  @After
  public void tearDown() {

    try {

      UiDevice device = UiDevice.getInstance(getInstrumentation());
      device.setOrientationNatural();

    } catch (RemoteException e) {
    }

    testRule.finishActivity();
  }

  private void rotate() {

    int orientation = activity.getRequestedOrientation();

    if(orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
      orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

    } else {
      orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
    }

    activity.setRequestedOrientation(orientation);

    try {

      UiDevice device = UiDevice.getInstance(getInstrumentation());

      if(orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
        device.setOrientationNatural();

      } else {
        device.setOrientationLeft();
      }

    } catch (RemoteException e) {
    }

    /*
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {

    }
    */
  }

  @Test
  public void test01question1WithRotation() {

    //GIVEN
    //encontrándonos en pantalla Question
    //después de cargar pregunta del cuestionario
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[0])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[1])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[2])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[3])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(emptyResult)));
    (onView(withId(R.id.option1Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option2Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option3Button))).check(matches(isEnabled()));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(not(isEnabled())));

    //WHEN
    //al girar pantalla
    rotate();

    //THEN
    //visualizaremos pregunta del cuestionario existente
    //en pantalla Question antes del giro
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[0])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[1])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[2])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[3])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(emptyResult)));
    (onView(withId(R.id.option1Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option2Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option3Button))).check(matches(isEnabled()));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(not(isEnabled())));

  }

  @Test
  public void test02question1Correct() {

    //GIVEN
    //encontrándonos en pantalla Question
    //después de cargar pregunta del cuestionario
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[0])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[1])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[2])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[3])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(emptyResult)));
    (onView(withId(R.id.option1Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option2Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option3Button))).check(matches(isEnabled()));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(not(isEnabled())));

    //WHEN
    //al pulsar botón Option correcto
    (onView(withId(R.id.option3Button))).perform(click());

    //THEN
    //mostraremos mensaje Correct ya que la respuesta del usuario
    //corresponde con respuesta correcta
    //mostraremos botones Option y Cheat desactivados
    //mostraremos botón Next activado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[0])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[1])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[2])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[3])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(correctResult)));
    (onView(withId(R.id.option1Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option2Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option3Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.cheatButton))).check(matches(not(isEnabled())));
    (onView(withId(R.id.nextButton))).check(matches(isEnabled()));

  }


  @Test
  public void test03question1Incorrect() {

    //GIVEN
    //encontrándonos en pantalla Question
    //después de cargar pregunta del cuestionario
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[0])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[1])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[2])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[3])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(emptyResult)));
    (onView(withId(R.id.option1Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option2Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option3Button))).check(matches(isEnabled()));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(not(isEnabled())));

    //WHEN
    //al pulsar botón Option incorrecto
    (onView(withId(R.id.option2Button))).perform(click());

    //THEN
    //mostraremos mensaje Incorrect ya que respuesta del usuario
    //corresponde con respuesta incorrecta
    //mostraremos botones Option desactivado
    //mostraremos botón Next y Cheat activado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[0])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[1])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[2])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[3])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(incorrectResult)));
    (onView(withId(R.id.option1Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option2Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option3Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(isEnabled()));

  }



  @Test
  public void test04question1CorrectWithRotation() {
    
    //GIVEN
    //encontrándonos en pantalla Question 
    //después de responder a pregunta del cuestionario
    //mostraremos mensaje Correct ya que la respuesta del usuario fue correcta
    //mostraremos botones Option y Cheat desactivados
    //mostraremos botón Next activado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[0])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[1])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[2])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[3])));
    (onView(withId(R.id.option3Button))).perform(click());
    (onView(withId(R.id.resultTextView))).check(matches(withText(correctResult)));
    (onView(withId(R.id.option1Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option2Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option3Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.cheatButton))).check(matches(not(isEnabled())));
    (onView(withId(R.id.nextButton))).check(matches(isEnabled()));

    //WHEN
    //al girar pantalla
    rotate();

    //THEN
    //visualizaremos pregunta del cuestionario existente 
    //en pantalla Question antes del giro
    //visualizaremos mensaje de Correct o Incorrect 
    //existente en pantalla Question antes del giro
    //mostraremos botones Option y Cheat desactivados
    //mostraremos botón Next activado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[0])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[1])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[2])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[3])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(correctResult)));
    (onView(withId(R.id.option1Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option2Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option3Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.cheatButton))).check(matches(not(isEnabled())));
    (onView(withId(R.id.nextButton))).check(matches(isEnabled()));

  }


  @Test
  public void test05question1IncorrectWithRotation() {

    //GIVEN
    //encontrándonos en pantalla Question 
    //después de responder a pregunta del cuestionario
    //mostraremos mensaje Incorrect ya que respuesta del usuario fue incorrecta
    //mostraremos botones Option y Cheat desactivados
    //mostraremos botón Next activado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[0])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[1])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[2])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[3])));
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.resultTextView))).check(matches(withText(incorrectResult)));
    (onView(withId(R.id.option1Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option2Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option3Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(isEnabled()));

    //WHEN
    //al girar pantalla
    rotate();

    //THEN
    //visualizaremos pregunta del cuestionario existente 
    //en pantalla Question antes del giro
    //visualizaremos mensaje de Correct o Incorrect 
    //existente en pantalla Question antes del giro
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[0])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[1])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[2])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[3])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(incorrectResult)));
    (onView(withId(R.id.option1Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option2Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option3Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(isEnabled()));

  }


  @Test
  public void test06question1CorrectWithNextClicked() {
    
    //GIVEN
    //encontrándonos en pantalla Question 
    //después de responder a pregunta del cuestionario
    //mostraremos mensaje Correct ya que la respuesta del usuario fue correcta
    //mostraremos botones Option y Cheat desactivados
    //mostraremos botón Next activado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[0])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[1])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[2])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[3])));
    (onView(withId(R.id.option3Button))).perform(click());
    (onView(withId(R.id.resultTextView))).check(matches(withText(correctResult)));
    (onView(withId(R.id.option1Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option2Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option3Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.cheatButton))).check(matches(not(isEnabled())));
    (onView(withId(R.id.nextButton))).check(matches(isEnabled()));

    //WHEN
    //al pulsar botón Next
    (onView(withId(R.id.nextButton))).perform(click());

    //THEN
    //mostraremos idéntica pantalla Question con siguiente pregunta  ya cargada
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[5])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[6])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[7])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[8])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(emptyResult)));
    (onView(withId(R.id.option1Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option2Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option3Button))).check(matches(isEnabled()));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(not(isEnabled())));
  }


  @Test
  public void test07question1CorrectWithNextClickedAndRotation() {

    //GIVEN
    //encontrándonos en pantalla Question
    //después de responder a pregunta del cuestionario
    //mostraremos mensaje Correct ya que la respuesta del usuario fue correcta
    //mostraremos botones Option y Cheat desactivados
    //mostraremos botón Next activado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[0])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[1])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[2])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[3])));
    (onView(withId(R.id.option3Button))).perform(click());
    (onView(withId(R.id.resultTextView))).check(matches(withText(correctResult)));
    (onView(withId(R.id.option1Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option2Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option3Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.cheatButton))).check(matches(not(isEnabled())));
    (onView(withId(R.id.nextButton))).check(matches(isEnabled()));


    //WHEN
    //al pulsar botón Next y girar la pantalla
    (onView(withId(R.id.nextButton))).perform(click());
    rotate();

    //THEN
    //mostraremos idéntica pantalla Question con siguiente pregunta  ya cargada
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[5])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[6])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[7])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[8])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(emptyResult)));
    (onView(withId(R.id.option1Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option2Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option3Button))).check(matches(isEnabled()));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(not(isEnabled())));
  }


  @Test
  public void test08question1IncorrectWithNextClicked() {

    //GIVEN
    //encontrándonos en pantalla Question
    //después de responder a pregunta del cuestionario
    //mostraremos mensaje Incorrect
    //ya que la respuesta del usuario fue incorrecta
    //mostraremos botones Option desactivado
    //mostraremos botón Next y Cheat activados
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[0])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[1])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[2])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[3])));
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.resultTextView))).check(matches(withText(incorrectResult)));
    (onView(withId(R.id.option1Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option2Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option3Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(isEnabled()));

    //WHEN
    //al pulsar botón Next
    (onView(withId(R.id.nextButton))).perform(click());

    //THEN
    //mostraremos idéntica pantalla Question con siguiente pregunta  ya cargada
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[5])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[6])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[7])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[8])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(emptyResult)));
    (onView(withId(R.id.option1Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option2Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option3Button))).check(matches(isEnabled()));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(not(isEnabled())));
  }


  @Test
  public void test09question1IncorrectWithNextClickedAndRotation() {

    //GIVEN
    //encontrándonos en pantalla Question
    //después de responder a pregunta del cuestionario
    //mostraremos mensaje Incorrect
    //ya que la respuesta del usuario fue incorrecta
    //mostraremos botones Option desactivado
    //mostraremos botón Next y Cheat activados
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[0])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[1])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[2])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[3])));
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.resultTextView))).check(matches(withText(incorrectResult)));
    (onView(withId(R.id.option1Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option2Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option3Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(isEnabled()));


    //WHEN
    //al pulsar botón Next y girar la pantalla
    (onView(withId(R.id.nextButton))).perform(click());
    rotate();

    //THEN
    //mostraremos idéntica pantalla Question con siguiente pregunta  ya cargada
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[5])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[6])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[7])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[8])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(emptyResult)));
    (onView(withId(R.id.option1Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option2Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option3Button))).check(matches(isEnabled()));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(not(isEnabled())));
  }



  @Test
  public void test10question2WithCheatClicked() {

    //GIVEN
    //encontrándonos en pantalla Question
    //sin haber respondido a pregunta del cuestionario
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[5])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[6])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[7])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[8])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(emptyResult)));
    (onView(withId(R.id.option1Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option2Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option3Button))).check(matches(isEnabled()));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(not(isEnabled())));

    //WHEN
    //al pulsar botón Cheat
    (onView(withId(R.id.cheatButton))).perform(click());

    //THEN
    //visualizaremos pantalla Cheat donde se nos pedirá confirmación
    //antes de mostrar respuesta correcta
    //mostraremos botones Yes y NO activados
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(emptyAnswer)));
    (onView(withId(R.id.noButton))).check(matches(isEnabled()));
    (onView(withId(R.id.yesButton))).check(matches(isEnabled()));
  }


  @Test
  public void test11question2WithCheatClickedAndRotation() {

    //GIVEN
    //encontrándonos en pantalla Question
    //después de responder a pregunta del cuestionario
    //mostraremos mensaje Incorrect
    //ya que la respuesta del usuario fue incorrecta
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[5])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[6])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[7])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[8])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(emptyResult)));
    (onView(withId(R.id.option1Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option2Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option3Button))).check(matches(isEnabled()));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(not(isEnabled())));

    //WHEN
    //al pulsar botón Cheat y girar la pantalla
    (onView(withId(R.id.cheatButton))).perform(click());
    rotate();

    //THEN
    //visualizaremos pantalla Cheat donde se nos pedirá confirmación
    //antes de mostrar respuesta correcta
    //mostraremos botones Yes y NO activados
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(emptyAnswer)));
    (onView(withId(R.id.noButton))).check(matches(isEnabled()));
    (onView(withId(R.id.yesButton))).check(matches(isEnabled()));
  }


  @Test
  public void test12question2IncorrectWithCheatClicked() {

    //GIVEN
    //encontrándonos en pantalla Question
    //después de responder a pregunta del cuestionario
    //mostraremos mensaje Incorrect
    //ya que la respuesta del usuario fue incorrecta
    //mostraremos botones Next y Cheat activados
    //mostraremos botón Option desactivado
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[5])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[6])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[7])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[8])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(incorrectResult)));
    (onView(withId(R.id.option1Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option2Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option3Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(isEnabled()));

    //WHEN
    //al pulsar botón Cheat
    (onView(withId(R.id.cheatButton))).perform(click());

    //THEN
    //visualizaremos pantalla Cheat donde se nos pedirá confirmación
    //antes de mostrar respuesta correcta
    //mostraremos botones Yes y NO activados
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(emptyAnswer)));
    (onView(withId(R.id.noButton))).check(matches(isEnabled()));
    (onView(withId(R.id.yesButton))).check(matches(isEnabled()));
  }


  @Test
  public void test13question2IncorrectWithCheatClickedAndRotation() {

    //GIVEN
    //encontrándonos en pantalla Question
    //después de responder a pregunta del cuestionario
    //mostraremos mensaje Incorrect
    //ya que la respuesta del usuario fue incorrecta
    //mostraremos botones Next y Cheat activados
    //mostraremos botón Option desactivado
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[5])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[6])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[7])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[8])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(incorrectResult)));
    (onView(withId(R.id.option1Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option2Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option3Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(isEnabled()));

    //WHEN
    //al pulsar botón Cheat y girar la pantalla
    (onView(withId(R.id.cheatButton))).perform(click());
    rotate();

    //THEN
    //visualizaremos pantalla Cheat donde se nos pedirá confirmación
    //antes de mostrar respuesta correcta
    //mostraremos botones Yes y NO activados
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(emptyAnswer)));
    (onView(withId(R.id.noButton))).check(matches(isEnabled()));
    (onView(withId(R.id.yesButton))).check(matches(isEnabled()));
  }



  @Test
  public void test14question2WithCheatAndNoClicked() {

    //GIVEN
    //encontrándonos en pantalla Cheat
    //sin haber respondido a  pregunta del cuestionario en pantalla Question
    //mostraremos botones Yes y NO activados
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.cheatButton))).perform(click());
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(emptyAnswer)));
    (onView(withId(R.id.noButton))).check(matches(isEnabled()));
    (onView(withId(R.id.yesButton))).check(matches(isEnabled()));

    //WHEN
    //al pulsar botón No
    (onView(withId(R.id.noButton))).perform(click());

    //THEN
    //volveremos a pantalla Question
    //mostraremos pregunta del cuestionario existente
    //antes de iniciar pantalla Cheat
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[5])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[6])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[7])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[8])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(emptyResult)));
    (onView(withId(R.id.option1Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option2Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option3Button))).check(matches(isEnabled()));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(not(isEnabled())));

  }


  @Test
  public void test15question2IncorrectWithCheatAndNoClicked() {

    //GIVEN
    //encontrándonos en pantalla Cheat
    //después de responder a pregunta del cuestionario en pantalla Question
    //mostraremos mensaje Incorrect
    //ya que la respuesta del usuario fue incorrecta
    //mostraremos botones Yes y NO activados
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.cheatButton))).perform(click());
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(emptyAnswer)));
    (onView(withId(R.id.noButton))).check(matches(isEnabled()));
    (onView(withId(R.id.yesButton))).check(matches(isEnabled()));


    //WHEN
    //al pulsar botón No
    (onView(withId(R.id.noButton))).perform(click());

    //THEN
    //volveremos a pantalla Question
    //donde mostraremos pregunta del cuestionario existente
    // antes de iniciar pantalla Cheat
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[5])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[6])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[7])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[8])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(incorrectResult)));
    (onView(withId(R.id.option1Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option2Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option3Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(isEnabled()));
  }


  @Test
  public void test16question2WithCheatAndYesClicked() {

    //GIVEN
    //encontrándonos en pantalla Cheat
    //sin haber respondido a  pregunta del cuestionario en pantalla Question
    //mostraremos botones Yes y NO activados
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.cheatButton))).perform(click());
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(emptyAnswer)));
    (onView(withId(R.id.noButton))).check(matches(isEnabled()));
    (onView(withId(R.id.yesButton))).check(matches(isEnabled()));

    //WHEN
    //al pulsar botón Yes
    (onView(withId(R.id.yesButton))).perform(click());

    //THEN
    //visualizaremos respuesta correcta
    //a pregunta del cuestionario mostrada actualmente en pantalla Question
    //mostraremos botones Yes y NO desactivados
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(quizArray[8])));
    (onView(withId(R.id.noButton))).check(matches(not(isEnabled())));
    (onView(withId(R.id.yesButton))).check(matches(not(isEnabled())));
  }


  @Test
  public void test17question2WithCheatAndYesClickedAndRotation() {

    //GIVEN
    //encontrándonos en pantalla Cheat
    //sin haber respondido a  pregunta del cuestionario en pantalla Question
    //mostraremos botones Yes y NO activados
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.cheatButton))).perform(click());
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(emptyAnswer)));
    (onView(withId(R.id.noButton))).check(matches(isEnabled()));
    (onView(withId(R.id.yesButton))).check(matches(isEnabled()));

    //WHEN
    //al pulsar botón Yes y girar la pantalla
    (onView(withId(R.id.yesButton))).perform(click());
    rotate();

    //THEN
    //visualizaremos respuesta correcta
    //a pregunta del cuestionario mostrada actualmente en pantalla Question
    //mostraremos botones Yes y NO desactivados
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(quizArray[8])));
    (onView(withId(R.id.noButton))).check(matches(not(isEnabled())));
    (onView(withId(R.id.yesButton))).check(matches(not(isEnabled())));
  }


  @Test
  public void test18question2IncorrectWithCheatAndYesClicked() {

    //GIVEN
    //encontrándonos en pantalla Cheat
    //después de responder a pregunta del cuestionario
    //mostraremos mensaje Incorrect
    //ya que la respuesta del usuario fue incorrecta
    //mostraremos botones Yes y NO activados
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.cheatButton))).perform(click());
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(emptyAnswer)));
    (onView(withId(R.id.noButton))).check(matches(isEnabled()));
    (onView(withId(R.id.yesButton))).check(matches(isEnabled()));

    //WHEN
    //al pulsar botón Yes
    (onView(withId(R.id.yesButton))).perform(click());

    //THEN
    //visualizaremos respuesta correcta
    //a pregunta del cuestionario mostrada actualmente en pantalla Question
    //mostraremos botones Yes y NO desactivados
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(quizArray[8])));
    (onView(withId(R.id.noButton))).check(matches(not(isEnabled())));
    (onView(withId(R.id.yesButton))).check(matches(not(isEnabled())));
  }


  @Test
  public void test19question2IncorrectWithCheatAndYesClickedAndRotation() {

    //GIVEN
    //encontrándonos en pantalla Cheat
    //después de responder a pregunta del cuestionario
    //mostraremos mensaje Incorrect
    //ya que la respuesta del usuario fue incorrecta
    //mostraremos botones Yes y NO activados
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.cheatButton))).perform(click());
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(emptyAnswer)));
    (onView(withId(R.id.noButton))).check(matches(isEnabled()));
    (onView(withId(R.id.yesButton))).check(matches(isEnabled()));

    //WHEN
    //al pulsar botón Yes y girar la pantalla
    (onView(withId(R.id.yesButton))).perform(click());
    rotate();

    //THEN
    //visualizaremos respuesta correcta
    //a pregunta del cuestionario mostrada actualmente en pantalla Question
    //mostraremos botones Yes y NO desactivados
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(quizArray[8])));
    (onView(withId(R.id.noButton))).check(matches(not(isEnabled())));
    (onView(withId(R.id.yesButton))).check(matches(not(isEnabled())));
  }


  @Test
  public void test20question2WithCheatAndYesClickedAndBackPressed() {

    //GIVEN
    //encontrándonos en pantalla Cheat
    //sin haber respondido a  pregunta del cuestionario en pantalla Question
    //mostraremos botones Yes y NO activados
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.cheatButton))).perform(click());
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(emptyAnswer)));
    (onView(withId(R.id.noButton))).check(matches(isEnabled()));
    (onView(withId(R.id.yesButton))).check(matches(isEnabled()));

    //WHEN
    //al pulsar botón Yes y luego el botón Back
    (onView(withId(R.id.yesButton))).perform(click());
    pressBack();

    //THEN
    //volveremos a pantalla Question
    //donde mostraremos pregunta siguiente del cuestionario
    //antes de iniciar pantalla Cheat
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[10])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[11])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[12])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[13])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(emptyResult)));
    (onView(withId(R.id.option1Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option2Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option3Button))).check(matches(isEnabled()));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(not(isEnabled())));

  }


  @Test
  public void test21question2IncorrectWithCheatAndYesClickedAndBackPressed() {

    //GIVEN
    //encontrándonos en pantalla Cheat
    //después de responder a pregunta del cuestionario en pantalla Question
    //mostraremos mensaje Incorrect
    //ya que la respuesta del usuario fue incorrecta
    //mostraremos botones Yes y NO activados
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.cheatButton))).perform(click());
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(emptyAnswer)));
    (onView(withId(R.id.noButton))).check(matches(isEnabled()));
    (onView(withId(R.id.yesButton))).check(matches(isEnabled()));


    //WHEN
    //al pulsar botón Yes y luego el botón Back
    (onView(withId(R.id.yesButton))).perform(click());
    pressBack();

    //THEN
    //volveremos a pantalla Question
    //donde mostraremos pregunta siguiente del cuestionario
    //antes de iniciar pantalla Cheat
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[10])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[11])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[12])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[13])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(emptyResult)));
    (onView(withId(R.id.option1Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option2Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option3Button))).check(matches(isEnabled()));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(not(isEnabled())));


  }

  @Test
  public void test22question2WithCheatAndYesClickedAndBackPressedAndRotation() {

    //GIVEN
    //encontrándonos en pantalla Cheat
    //sin haber respondido a  pregunta del cuestionario en pantalla Question
    //mostraremos botones Yes y NO activados
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.cheatButton))).perform(click());
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(emptyAnswer)));
    (onView(withId(R.id.noButton))).check(matches(isEnabled()));
    (onView(withId(R.id.yesButton))).check(matches(isEnabled()));

    //WHEN
    //al pulsar botón Yes, el botón Back y girar la pantalla
    (onView(withId(R.id.yesButton))).perform(click());
    pressBack();
    rotate();

    //THEN
    //volveremos a pantalla Question
    //donde mostraremos pregunta siguiente del cuestionario
    //antes de iniciar pantalla Cheat
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[10])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[11])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[12])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[13])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(emptyResult)));
    (onView(withId(R.id.option1Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option2Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option3Button))).check(matches(isEnabled()));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(not(isEnabled())));

  }


  @Test
  public void test23question2IncorrectWithCheatAndYesClickedAndBackPressedAndRotation() {

    //GIVEN
    //encontrándonos en pantalla Cheat
    //después de responder a pregunta del cuestionario en pantalla Question
    //mostraremos mensaje Incorrect
    //ya que la respuesta del usuario fue incorrecta
    //mostraremos botones Yes y NO activados
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.cheatButton))).perform(click());
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(emptyAnswer)));
    (onView(withId(R.id.noButton))).check(matches(isEnabled()));
    (onView(withId(R.id.yesButton))).check(matches(isEnabled()));


    //WHEN
    //al pulsar botón Yes, el botón Back y girar la pantalla
    (onView(withId(R.id.yesButton))).perform(click());
    pressBack();
    rotate();

    //THEN
    //volveremos a pantalla Question
    //donde mostraremos pregunta siguiente del cuestionario
    //antes de iniciar pantalla Cheat
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[10])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[11])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[12])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[13])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(emptyResult)));
    (onView(withId(R.id.option1Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option2Button))).check(matches(isEnabled()));
    (onView(withId(R.id.option3Button))).check(matches(isEnabled()));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(not(isEnabled())));

  }


  @Test
  public void test24question10WithCheatAndYesClickedAndBackPressedAndRotation() {

    //GIVEN
    //encontrándonos en pantalla Cheat
    //sin haber respondido a  pregunta del cuestionario en pantalla Question
    //mostraremos botones Yes y NO activados
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.cheatButton))).perform(click());
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(emptyAnswer)));
    (onView(withId(R.id.noButton))).check(matches(isEnabled()));
    (onView(withId(R.id.yesButton))).check(matches(isEnabled()));

    //WHEN
    //al pulsar botón Yes, el botón Back y girar la pantalla
    (onView(withId(R.id.yesButton))).perform(click());
    pressBack();
    rotate();

    //THEN
    //volveremos a pantalla Question
    //donde mostraremos pregunta del cuestionario existente
    //antes de iniciar pantalla Cheat
    //mostraremos botones Option y Cheat activados
    //mostraremos botón Next desactivado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[45])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[46])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[47])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[48])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(emptyResult)));
    (onView(withId(R.id.option1Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option2Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option3Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(not(isEnabled())));

  }


  @Test
  public void test25question10IncorrectWithCheatAndYesClickedAndBackPressedAndRotation() {

    //GIVEN
    //encontrándonos en pantalla Cheat
    //después de responder a pregunta del cuestionario en pantalla Question
    //mostraremos mensaje Incorrect
    //ya que la respuesta del usuario fue incorrecta
    //mostraremos botones Yes y NO activados
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.nextButton))).perform(click());
    (onView(withId(R.id.option2Button))).perform(click());
    (onView(withId(R.id.cheatButton))).perform(click());
    (onView(withId(R.id.warningTextView))).check(matches(withText(warningMessage)));
    (onView(withId(R.id.answerTextView))).check(matches(withText(emptyAnswer)));
    (onView(withId(R.id.noButton))).check(matches(isEnabled()));
    (onView(withId(R.id.yesButton))).check(matches(isEnabled()));


    //WHEN
    //al pulsar botón Yes, el botón Back y girar la pantalla
    (onView(withId(R.id.yesButton))).perform(click());
    pressBack();
    rotate();

    //THEN
    //volveremos a pantalla Question
    //donde mostraremos pregunta del cuestionario existente
    //antes de iniciar pantalla Cheat
    //mostraremos botones Option, Next y Cheat desactivado
    (onView(withId(R.id.questionTextView))).check(matches(withText(quizArray[45])));
    (onView(withId(R.id.option1Button))).check(matches(withText(quizArray[46])));
    (onView(withId(R.id.option2Button))).check(matches(withText(quizArray[47])));
    (onView(withId(R.id.option3Button))).check(matches(withText(quizArray[48])));
    (onView(withId(R.id.resultTextView))).check(matches(withText(incorrectResult)));
    (onView(withId(R.id.option1Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option2Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.option3Button))).check(matches(not(isEnabled())));
    (onView(withId(R.id.cheatButton))).check(matches(isEnabled()));
    (onView(withId(R.id.nextButton))).check(matches(not(isEnabled())));

  }
}
