package net.sf.anathema.character.lunar.heartsblood;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.lunar.heartsblood.presenter.HeartsBloodPresenter;
import net.sf.anathema.character.lunar.heartsblood.presenter.IHeartsBloodModel;
import net.sf.anathema.character.lunar.heartsblood.view.HeartsBloodView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.resources.Resources;

public class HeartsBloodViewFactory implements IAdditionalViewFactory {

  @Override
  public IView createView(IAdditionalModel model, Resources resources, ICharacterType type) {
    HeartsBloodView view = new HeartsBloodView();
    new HeartsBloodPresenter((IHeartsBloodModel) model, view, resources).initPresentation();
    return view;
  }
}