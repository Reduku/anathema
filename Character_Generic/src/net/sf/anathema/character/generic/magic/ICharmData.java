package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.generic.magic.charms.duration.IDuration;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.lib.util.Identified;

import java.util.List;
import java.util.Set;

public interface ICharmData extends IMagicData {
  Identified FORM_ATTRIBUTE = new Identificate("Form"); //$NON-NLS-1$
  Identified MERGED_ATTRIBUTE = new Identificate("Merged"); //$NON-NLS-1$
  Identified ALLOWS_CELESTIAL_ATTRIBUTE = new Identificate("AllowsCelestial"); //$NON-NLS-1$
  Identified UNRESTRICTED_ATTRIBUTE = new Identificate("Unrestricted"); //$NON-NLS-1$
  Identified NO_STYLE_ATTRIBUTE = new Identificate("NoStyle"); //$NON-NLS-1$
  Identified NATIVE = new Identificate("Native"); //$NON-NLS-1$
  String FAVORED_CASTE_PREFIX = "FavoredCaste."; //$NON-NLS-1$

  ICharacterType getCharacterType();

  IDuration getDuration();

  IGenericTrait getEssence();

  IGenericTrait[] getPrerequisites();

  ITraitType getPrimaryTraitType();

  String getGroupId();

  IComboRestrictions getComboRules();

  ICharmAttribute[] getAttributes();

  IndirectCharmRequirement[] getAttributeRequirements();

  Set<ICharm> getParentCharms();
  
  List<String> getParentSubeffects();

  ICharmTypeModel getCharmTypeModel();
  
  boolean isInstanceOfGenericCharm();
}