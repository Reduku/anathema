package net.sf.anathema.character.lunar.reporting.rendering.health;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.second.rendering.health.HealthAndMovementEncoder;
import net.sf.anathema.character.reporting.second.rendering.health.HealthAndMovementTableEncoder;
import net.sf.anathema.lib.resources.Resources;

public class SecondEditionLunarHealthAndMovementEncoder extends HealthAndMovementEncoder {

  public SecondEditionLunarHealthAndMovementEncoder(Resources resources) {
    super(resources);
  }

  @Override
  protected final ITableEncoder createTableEncoder() {
    return new HealthAndMovementTableEncoder(getResources()) {
      @Override
      protected IGenericTraitCollection getTraits(IGenericCharacter character) {
        return ((IBeastformModel) character.getAdditionalModel(BeastformTemplate.TEMPLATE_ID)).getBeastTraitCollection();
      }
    };
  }

}
