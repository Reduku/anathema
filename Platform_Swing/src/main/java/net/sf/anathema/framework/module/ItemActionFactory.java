package net.sf.anathema.framework.module;

import net.sf.anathema.framework.model.IItemActionFactory;
import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.presenter.itemmanagement.GivenItemCloseAction;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Action;

public class ItemActionFactory implements IItemActionFactory {

  private final IItemManagementModel model;
  private final Resources resources;

  public ItemActionFactory(IItemManagementModel model, Resources resources) {
    this.model = model;
    this.resources = resources;
  }

  @Override
  public Action createAction(IItem item) {
    return GivenItemCloseAction.createForItem(model, resources, item);
  }
}
