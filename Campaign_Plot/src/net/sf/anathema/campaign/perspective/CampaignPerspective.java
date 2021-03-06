package net.sf.anathema.campaign.perspective;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;

@PerspectiveAutoCollector
@Weight(weight = 9999)
public class CampaignPerspective implements Perspective {

  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon(new RelativePath("icons/CampaignPerspective.png"));
    toggle.setTooltip("Campaign.Perspective.Tooltip");
  }

  @Override
  public void initContent(Container container, IApplicationModel applicationModel, Resources resources) {
    CampaignPerspectiveView view = new CampaignPerspectiveView();
    new CampaignPerspectivePresenter(applicationModel, view, resources).initPresentation();
    container.setSwingContent(view.createContent());
  }
}
