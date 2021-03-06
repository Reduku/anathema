package net.sf.anathema.character.craft;

import net.sf.anathema.character.craft.presenter.CraftPresenter;
import net.sf.anathema.character.craft.presenter.ICraftModel;
import net.sf.anathema.character.craft.view.CraftView;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.resources.Resources;

public class CraftViewFactory implements IAdditionalViewFactory {

  @Override
  public IView createView(IAdditionalModel model, Resources resources, ICharacterType type) {
    ICraftAdditionalModel additionalModel = (ICraftAdditionalModel) model;
    ICraftModel craftModel = additionalModel.getCraftModel();
    int maximum = craftModel.getAbsoluteMaximum();
    CraftView view = new CraftView(IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(type), maximum);
    new CraftPresenter(craftModel, view, resources).initPresentation();
    return view;
  }
}