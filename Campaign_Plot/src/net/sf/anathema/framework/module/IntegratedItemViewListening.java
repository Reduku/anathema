package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.model.ItemManagmentModelListener;
import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.presenter.IModelViewMapping;
import net.sf.anathema.framework.presenter.ModelViewMapping;
import net.sf.anathema.framework.presenter.itemmanagement.ItemViewSelectionListener;
import net.sf.anathema.framework.view.IItemViewManagement;
import net.sf.anathema.lib.resources.Resources;

public class IntegratedItemViewListening {

  public void init(Resources resources, IApplicationModel model, IItemViewManagement itemViewManagement) {
    IModelViewMapping mapping = new ModelViewMapping();
    IItemManagementModel itemManagement = model.getItemManagement();
    ItemActionFactory actionFactory = new ItemActionFactory(itemManagement, resources);
    itemManagement.addListener(new ItemManagmentModelListener(model.getViewFactoryRegistry(), itemViewManagement, mapping, actionFactory));
    itemViewManagement.addViewSelectionListener(new ItemViewSelectionListener(itemManagement, mapping));
  }
}
