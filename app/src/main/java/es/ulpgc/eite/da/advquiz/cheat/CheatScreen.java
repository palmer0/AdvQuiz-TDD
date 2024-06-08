package es.ulpgc.eite.da.advquiz.cheat;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advquiz.R;
import es.ulpgc.eite.da.advquiz.app.AppMediator;


public class CheatScreen {

  public static void configure(CheatContract.View view) {

    WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

    AppMediator mediator = AppMediator.getInstance();
    CheatContract.Presenter presenter = new CheatPresenter(mediator);

    CheatContract.Model model = new CheatModel();
    model.setEmptyText( context.get().getString(R.string.empty_result));

    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(view));
    view.injectPresenter(presenter);

  }
}
