package net.sf.anathema.character.sidereal.reporting.rendering.anima;

import net.sf.anathema.character.reporting.pdf.content.stats.anima.AnimaTableStealthProvider;
import net.sf.anathema.lib.resources.Resources;

public class AnimaStealthProvider extends AnimaTableStealthProvider {

  public AnimaStealthProvider(Resources resources) {
    super(resources);
  }

  @Override
  protected String getLevelTwoStealth() {
    return getNormalStealth();
  }

  @Override
  protected String getLevelThreeStealth() {
    return getString("Sheet.AnimaTable.Stealth2InDark");
  }
}
