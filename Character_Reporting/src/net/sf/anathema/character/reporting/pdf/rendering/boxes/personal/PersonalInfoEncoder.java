package net.sf.anathema.character.reporting.pdf.rendering.boxes.personal;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.BARE_LINE_HEIGHT;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.TEXT_PADDING;

public class PersonalInfoEncoder implements IVariableContentEncoder {

  private final Resources resources;

  public PersonalInfoEncoder(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) {
    ICharacterType characterType = reportSession.getCharacter().getTemplate().getTemplateType().getCharacterType();

    int lines = getNumberOfLines(characterType);

    float lineHeight = (bounds.height - TEXT_PADDING) / lines;
    float entryWidth = (bounds.width - TEXT_PADDING) / 2;
    float shortEntryWidth = (bounds.width - 4 * TEXT_PADDING) / 5;
    float firstColumnX = bounds.x;
    float secondColumnX = firstColumnX + entryWidth + TEXT_PADDING;

    float firstRowY = (int) (bounds.getMaxY() - lineHeight);
    String conceptContent = reportSession.getDescription().getConceptText();
    String conceptLabel = getLabel("Concept");
    if (characterType.isExaltType()) {
      graphics.drawLabelledContent(conceptLabel, conceptContent, new Position(firstColumnX, firstRowY), entryWidth);
      String casteContent = getCasteString(reportSession.getCharacter().getConcept().getCasteType());
      graphics.drawLabelledContent(getLabel("Caste." + characterType.getId()), casteContent, new Position(secondColumnX, firstRowY), entryWidth);
    } else {
      graphics.drawLabelledContent(conceptLabel, conceptContent, new Position(firstColumnX, firstRowY), 2 * entryWidth + TEXT_PADDING);
    }

    float secondRowY = firstRowY - lineHeight;
    String motivationContent = reportSession.getCharacter().getConcept().getWillpowerRegainingComment(resources);
    String motivationLabel = getLabel("Motivation");
    graphics.drawLabelledContent(motivationLabel, motivationContent, new Position(firstColumnX, secondRowY), bounds.width);

    float thirdRowY = secondRowY - lineHeight;
    float[] shortColumnX = new float[5];
    for (int i = 0; i < 5; i++) {
      shortColumnX[i] = bounds.x + i * (shortEntryWidth + TEXT_PADDING);
    }
    String ageContent = Integer.toString(reportSession.getCharacter().getAge());
    graphics.drawLabelledContent(getLabel("Age"), ageContent, new Position(shortColumnX[0], thirdRowY), shortEntryWidth);
    String sexContent = reportSession.getDescription().getSex();
    graphics.drawLabelledContent(getLabel("Sex"), sexContent, new Position(shortColumnX[1], thirdRowY), shortEntryWidth);
    String hairContent = reportSession.getDescription().getHair();
    graphics.drawLabelledContent(getLabel("Hair"), hairContent, new Position(shortColumnX[2], thirdRowY), shortEntryWidth);
    String skinContent = reportSession.getDescription().getSkin();
    graphics.drawLabelledContent(getLabel("Skin"), skinContent, new Position(shortColumnX[3], thirdRowY), shortEntryWidth);
    String eyesContent = reportSession.getDescription().getEyes();
    graphics.drawLabelledContent(getLabel("Eyes"), eyesContent, new Position(shortColumnX[4], thirdRowY), shortEntryWidth);

    if (characterType.isExaltType()) {
      float fourthRowY = thirdRowY - lineHeight;
      String animaContent = reportSession.getDescription().getAnima();
      graphics.drawLabelledContent(getLabel("Anima"), animaContent, new Position(firstColumnX, fourthRowY), bounds.width);
    }
  }

  private int getNumberOfLines(IGenericCharacter character) {
    return getNumberOfLines(character.getTemplate().getTemplateType().getCharacterType());
  }

  private int getNumberOfLines(ICharacterType characterType) {
    return (characterType.isExaltType() ? 4 : 3);
  }

  private String getCasteString(ICasteType casteType) {
    if (casteType == null || casteType == ICasteType.NULL_CASTE_TYPE) {
      return null;
    }
    return resources.getString("Caste." + casteType.getId());
  }

  protected final String getLabel(String key) {
    return resources.getString("Sheet.Label." + key) + ":";
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }

  @Override
  public String getHeader(ReportSession session) {
    String name = session.getDescription().getName();
    return StringUtilities.isNullOrTrimmedEmpty(name) ? resources.getString("Sheet.Header.PersonalInfo") : name;
  }

  @Override
  public float getRequestedHeight(SheetGraphics graphics, ReportSession session, float width) {
    return getPreferredContentHeight(session);
  }

  public float getPreferredContentHeight(ReportSession session) {
    return getNumberOfLines(session.getCharacter()) * BARE_LINE_HEIGHT + TEXT_PADDING;
  }
}
