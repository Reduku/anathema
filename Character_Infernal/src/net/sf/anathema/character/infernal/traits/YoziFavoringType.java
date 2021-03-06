package net.sf.anathema.character.infernal.traits;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.YoziType;

public class YoziFavoringType implements FavoringTraitType {
  @Override
  public YoziType[] getTraitTypesForGenericCharms() {
    return YoziType.values();
  }

  @Override
  public ITraitType getSpellFavoringType() {
    return AbilityType.Occult;
  }

  @Override
  public String getId() {
    return "YoziType";
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof YoziFavoringType;
  }

  @Override
  public int hashCode() {
    return 4;
  }
}