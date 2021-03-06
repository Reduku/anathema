package net.sf.anathema.cascades.presenter;

import com.google.common.base.Predicate;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.util.Identified;

import static net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities.MARTIAL_ARTS;

public class CharmFinder {

  private CharacterTypes characterTypes;
  private final ICharmCache cache;
  private final String id;

  public CharmFinder(CharacterTypes characterTypes, ICharmCache cache, String id) {
    this.characterTypes = characterTypes;
    this.cache = cache;
    this.id = id;
  }

  public ICharm find() {
    ICharm charm = searchCharmByCharacterType();
    if (charm != null) {
      return charm;
    }
    return searchCharmFromMartialArts();
  }

  private ICharm searchCharmByCharacterType() {
    String[] idParts = id.split("\\.");
    try {
    	ICharacterType characterTypeId = characterTypes.findById(idParts[0]);
    	return findCharm(characterTypeId);
    }
    catch (IllegalArgumentException e) {
    	return null;
    }
  }

  private ICharm searchCharmFromMartialArts() {
    return findCharm(MARTIAL_ARTS);
  }

  private ICharm findCharm(Identified treeType) {
    ICharm[] charms = cache.getCharms(treeType);
    return ArrayUtilities.find(new Predicate<ICharm>() {
      @Override
      public boolean apply(ICharm candidate) {
        return candidate.getId().equals(id);
      }
    }, charms);
  }
}