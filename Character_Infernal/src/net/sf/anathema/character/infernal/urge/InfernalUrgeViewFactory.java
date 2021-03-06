package net.sf.anathema.character.infernal.urge;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.infernal.urge.model.IInfernalUrgeModel;
import net.sf.anathema.character.infernal.urge.presenter.InfernalUrgePresenter;
import net.sf.anathema.character.infernal.urge.view.InfernalUrgeView;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.resources.Resources;

public class InfernalUrgeViewFactory implements IAdditionalViewFactory {

  @Override
  public IView createView(IAdditionalModel model, Resources resources, ICharacterType type) {
    InfernalUrgeView view = new InfernalUrgeView(
            IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(type));
    new InfernalUrgePresenter(resources, view, (IInfernalUrgeModel) model).initPresentation();
    return view;
  }
}