package net.sf.anathema.character.equipment;

import com.google.common.base.Functions;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.initialization.Instantiater;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ReflectionMaterialRules implements MaterialRules {

  private final Map<String, CharacterTypeMaterialRules> rulesByCharacterType = new HashMap<>();

  public ReflectionMaterialRules(Instantiater instantiater) {
    Collection<CharacterTypeMaterialRules> allRules = instantiater.instantiateAll(MaterialRulesDefinition.class);
    for (CharacterTypeMaterialRules rules : allRules) {
      String applicableType = rules.getClass().getAnnotation(MaterialRulesDefinition.class).characterType();
      rulesByCharacterType.put(applicableType, rules);
    }
  }

  public MagicalMaterial getDefault(ICharacterType characterType) {
    CharacterTypeMaterialRules rules = getRulesForCharacter(characterType);
    return rules.getDefault();
  }

  public ArtifactAttuneType[] getAttunementTypes(ICharacterType characterType,
                                                 MagicalMaterial material) {
    CharacterTypeMaterialRules rules = getRulesForCharacter(characterType);
    return rules.getAttunementTypes(material);
  }

  public boolean canAttuneToMalfeanMaterials(ICharacterType characterType) {
    CharacterTypeMaterialRules rules = getRulesForCharacter(characterType);
    return rules.canAttuneToMalfeanMaterials();
  }

  private CharacterTypeMaterialRules getRulesForCharacter(ICharacterType type) {
    return Functions.forMap(rulesByCharacterType, new DefaultMaterialRules()).apply(type.getId());
  }
}