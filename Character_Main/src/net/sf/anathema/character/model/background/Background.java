package net.sf.anathema.character.model.background;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.backgrounds.CustomizedBackgroundTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.lib.resources.Resources;

public class Background extends DefaultTrait implements IBackground {
  private final String description;

  public Background(String description, ITraitRules traitRules, ITraitContext traitContext, IValueChangeChecker valueChangeChecker) {
    super(traitRules, traitContext, valueChangeChecker);
    this.description = description;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public String getName(Resources resources) {
    ITraitType type = getType();
    String backgroundId = type.getId();
    if (type instanceof CustomizedBackgroundTemplate) {
      return backgroundId;
    }
    return resources.getString("BackgroundType.Name." + type.getId());
  }

}
