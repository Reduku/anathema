package net.sf.anathema.character.reporting.pdf.rendering.boxes.anima;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.HorizontalLineEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.ListUtils;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.generic.impl.persistence.SecondEdition.SECOND_EDITION;

public class GenericAnimaEncoder implements ContentEncoder {

  private final float fontSize;
  private final float lineHeight;
  private final Resources resources;
  private final ITableEncoder tableEncoder;

  public GenericAnimaEncoder(Resources resources, float fontSize, ITableEncoder encoder) {
    this.resources = resources;
    this.fontSize = fontSize;
    this.lineHeight = fontSize * 1.5f;
    this.tableEncoder = encoder;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    float powerHeight = bounds.getHeight() - AnimaTableEncoder.TABLE_HEIGHT - IVoidStateFormatConstants.TEXT_PADDING / 2f;
    Bounds animaPowerBounds = new Bounds(bounds.getMinX(), bounds.getMaxY() - powerHeight, bounds.getWidth(), powerHeight);
    encodeAnimaPowers(graphics, reportSession.getCharacter(), animaPowerBounds);

    Bounds animaTableBounds = new Bounds(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), AnimaTableEncoder.TABLE_HEIGHT);
    tableEncoder.encodeTable(graphics, reportSession, animaTableBounds);
  }

  private void encodeAnimaPowers(SheetGraphics graphics, IGenericCharacter character, Bounds bounds) throws DocumentException {
    Phrase phrase = new Phrase("", graphics.createFont(fontSize));
    // Add standard powers for character type
    Chunk symbolChunk = graphics.createSymbolChunk();
    ICharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
    ListUtils.addBulletedListText(resources, symbolChunk, "Sheet.AnimaPower." + characterType.getId(), phrase, false);
    String casteResourceKey = "Sheet.AnimaPower." + character.getCasteType().getId() + "." + SECOND_EDITION;
    if (resources.supportsKey(casteResourceKey)) {
      phrase.add(symbolChunk);
      phrase.add(resources.getString(casteResourceKey) + "\n");
    }
    phrase.add(symbolChunk);
    float yPosition = graphics.createSimpleColumn(bounds).withLeading(lineHeight).andTextPart(phrase).encode().getYLine();
    Position lineStartPosition = new Position((bounds.getMinX() + graphics.getCaretSymbolWidth()), yPosition);
    int lineCount = 1 + (int) ((yPosition - bounds.getMinY()) / lineHeight);
    new HorizontalLineEncoder().encodeLines(graphics, lineStartPosition, bounds.getMinX(), bounds.getMaxX(), lineHeight, lineCount);
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }

  @Override
  public String getHeader(ReportSession session) {
    return resources.getString("Sheet.Header.Anima");
  }
}
