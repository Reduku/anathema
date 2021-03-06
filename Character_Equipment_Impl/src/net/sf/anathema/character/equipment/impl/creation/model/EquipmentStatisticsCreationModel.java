package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.stats.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IArtifactStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.ICloseCombatStatsticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.IRangedCombatStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.ITraitModifyingStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTagsModel;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.data.Range;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.RangedIntValueModel;
import org.apache.commons.lang3.ArrayUtils;
import org.jmock.example.announcer.Announcer;

public class EquipmentStatisticsCreationModel implements IEquipmentStatisticsCreationModel {

  private final ICloseCombatStatsticsModel closeCombatStatisticsModel = new CloseCombatStatsticsModel(
          createOffensiveSpeedModel());
  private final IRangedCombatStatisticsModel rangedWeaponStatisticsModel = new RangedWeaponStatisticsModel(
          createOffensiveSpeedModel());
  private final IArmourStatisticsModel armourStatisticsModel = new ArmourStatsticsModel();
  private final IArtifactStatisticsModel artifactStatisticsModel = new ArtifactStatisticsModel();
  private final ITraitModifyingStatisticsModel traitModifyingStatisticsModel = new TraitModifyingStatisticsModel();
  private final Announcer<IChangeListener> equipmentTypeChangeControl = Announcer.to(IChangeListener.class);
  private final IWeaponTagsModel weaponTagsModel = new WeaponTagsModel();
  private EquipmentStatisticsType statisticsType;
  private String[] existingNames;

  private static IIntValueModel createOffensiveSpeedModel() {
    return new RangedIntValueModel(new Range(1, Integer.MAX_VALUE), 1);
  }

  @Override
  public void setEquipmentType(EquipmentStatisticsType statisticsType) {
    if (this.statisticsType == statisticsType) {
      return;
    }
    if (statisticsType == EquipmentStatisticsType.RangedCombat) {
      getWeaponTagsModel().setTagsRangedCombatStyle();
    } else {
      getWeaponTagsModel().setTagsCloseCombatStyle();
    }
    this.statisticsType = statisticsType;
    equipmentTypeChangeControl.announce().changeOccurred();
  }

  @Override
  public ICloseCombatStatsticsModel getCloseCombatStatsticsModel() {
    return closeCombatStatisticsModel;
  }

  @Override
  public IWeaponTagsModel getWeaponTagsModel() {
    return weaponTagsModel;
  }

  @Override
  public IRangedCombatStatisticsModel getRangedWeaponStatisticsModel() {
    return rangedWeaponStatisticsModel;
  }

  @Override
  public IArmourStatisticsModel getArmourStatisticsModel() {
    return armourStatisticsModel;
  }

  @Override
  public IArtifactStatisticsModel getArtifactStatisticsModel() {
    return artifactStatisticsModel;
  }

  @Override
  public ITraitModifyingStatisticsModel getTraitModifyingStatisticsModel() {
    return traitModifyingStatisticsModel;
  }

  @Override
  public EquipmentStatisticsType getEquipmentType() {
    return statisticsType;
  }

  @Override
  public boolean isNameUnique(String name) {
    return !ArrayUtils.contains(existingNames, name);
  }

  @Override
  public void setForbiddenNames(String[] definedNames) {
    this.existingNames = definedNames;
  }
}