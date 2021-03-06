package net.sf.anathema.character.generic.framework.resources;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.ui.AbstractUI;

import javax.swing.Icon;

public class CharacterUI extends AbstractUI {

  public RelativePath getCharacterDescriptionTabIcon() {
    return new RelativePath("icons/TabDescription16.png");
  }

  public Icon getLinkIcon() {
    return getIcon(new RelativePath("icons/ButtonLink16b.png"));
  }

  public Icon getRandomRealmNameIcon() {
    return getIcon(new RelativePath("icons/ButtonRandomNameC16.png"));
  }

  public Icon getRandomThresholdNameIcon() {
    return getIcon(new RelativePath("icons/ButtonRandomNameB16.png"));
  }

  public Icon getCancelComboEditIcon() {
    return getIcon(new RelativePath("icons/ButtonUndo16.png"));
  }

  public Icon getFinalizeIcon() {
    return getIcon(new RelativePath("icons/ButtonCheck16.png"));
  }

  public Icon getFinalizeXPIcon() {
    return getIcon(new RelativePath("icons/ButtonCheckXP16.png"));
  }

  public Icon getSmallTypeIcon(ICharacterType characterType) {
    return getIcon(getSmallTypeIconPath(characterType));
  }

  public RelativePath getSmallTypeIconPath(ICharacterType characterType) {
    return new RelativePath("icons/" + characterType.getId() + "Icon16.png");
  }

  public RelativePath getLargeTypeIconPath(ICharacterType characterType) {
    return new RelativePath("icons/" + characterType.getId() + "Icon100.png");
  }

  public Icon getMediumBallResource(ICharacterType characterType) {
    return getIcon(new RelativePath("icons/Border" + characterType.getId() + "Button16.png"));
  }

  public Icon getUnselectedBallResource() {
    return getIcon(new RelativePath("icons/BorderUnselectedButton16.png"));
  }

  public Icon getUnselectableBallResource() {
    return getIcon(new RelativePath("icons/BorderUnselectableButton16.png"));
  }
}