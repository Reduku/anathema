package net.sf.anathema.character.db.template;

import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.CharmAttributeRequirement;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;
import net.sf.anathema.character.generic.template.magic.IMartialArtsRules;

public class TerrestrialMartialArtsRules implements IMartialArtsRules {
  private final IndirectCharmRequirement celestialAttributeRequirement = new CharmAttributeRequirement(
      new CharmAttribute(ICharmData.ALLOWS_CELESTIAL_ATTRIBUTE.getId(), false),
      1);

  private boolean highLevelAtCreation;

  @Override
  public void setHighLevelAtCreation(boolean highLevelAtCreation) {
    this.highLevelAtCreation = highLevelAtCreation;
  }

  @Override
  public MartialArtsLevel getStandardLevel() {
    return MartialArtsLevel.Terrestrial;
  }

  @Override
  public boolean isCharmAllowed(
      ICharm martialArtsCharm,
      IGenericCharmConfiguration charmConfiguration,
      boolean isExperienced) {
    MartialArtsLevel level = MartialArtsUtilities.getLevel(martialArtsCharm);
    if (MartialArtsLevel.Terrestrial.compareTo(level) >= 0) {
      return true;
    }
    if (level == MartialArtsLevel.Celestial) {
      if (!highLevelAtCreation && !isExperienced) {
        return false;
      }
      if (martialArtsCharm.hasAttribute(ICharmData.UNRESTRICTED_ATTRIBUTE)) {
        return true;
      }
      if (celestialAttributeRequirement.isFulfilled(charmConfiguration.getLearnedCharms())) {
        String[] uncompletedGroupIds = charmConfiguration.getUncompletedCelestialMartialArtsGroups();
        if (uncompletedGroupIds.length > 1) {
          throw new IllegalStateException("The character has started learning more than one celestial style."); //$NON-NLS-1$
        }
        if (uncompletedGroupIds.length == 0) {
          return true;
        }
        return martialArtsCharm.getGroupId().equals(uncompletedGroupIds[0]);
      }
    }
    return false;
  }
}