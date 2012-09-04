package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.charmtree.presenter.AbstractCharmDye;
import net.sf.anathema.charmtree.presenter.view.CharmGroupInformer;

import static java.awt.Color.WHITE;

public class CascadeCharmDye extends AbstractCharmDye {

  private final ICascadeView view;

  public CascadeCharmDye(ICascadeView view, CharmGroupInformer informer) {
    super(informer);
    this.view = view;
  }

  @Override
  public void colorCharm(ICharm charm) {
    view.setCharmVisuals(charm.getId(), WHITE);
  }

  @Override
  protected void setPrerequisiteVisuals(IndirectCharmRequirement requirement) {
    view.setCharmVisuals(requirement.getStringRepresentation(), WHITE);
  }
}