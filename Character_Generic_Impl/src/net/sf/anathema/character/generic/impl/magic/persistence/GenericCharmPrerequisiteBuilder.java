package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericCharmUtilities;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.CharmPrerequisiteBuilder;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.util.Identified;
import org.dom4j.Element;

import java.util.Collection;

public class GenericCharmPrerequisiteBuilder extends CharmPrerequisiteBuilder implements IGenericsBuilder {

  private Identified type;

  @Override
  protected Collection<String> getCharmIds(Element parent) throws CharmException {
    return GenericCharmUtilities.getAllReferencedGenericCharms(parent, type);
  }

  @Override
  public void setType(ITraitType type) {
    this.type = type;
  }
}
