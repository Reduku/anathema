package net.sf.anathema.character.sidereal.reporting.rendering;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.Resources;

public class ArcaneFateInfoEncoder implements ContentEncoder {

  private final Resources resources;
  private final int fontSize;
  private float lineHeight;

  public ArcaneFateInfoEncoder(int fontSize, Resources resources) {
    this.fontSize = fontSize;
    this.resources = resources;
    this.lineHeight = fontSize * 1.5f;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    Chunk symbolChunk = graphics.createSymbolChunk();
    Phrase phrase = new Phrase("", graphics.createFont(fontSize));
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.ArcaneFate.Masquerade") + "\n");
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.ArcaneFate.Disguise") + "\n");
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.ArcaneFate.Destiny") + "\n");
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.ArcaneFate.Relationship") + "\n");
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.ArcaneFate.Stealth") + "\n");
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.ArcaneFate.Remembering2nd") + "\n");
    graphics.createSimpleColumn(bounds).withLeading(lineHeight).andTextPart(phrase).encode();
  }

  @Override
  public String getHeader(ReportSession session) {
    return resources.getString("Sheet.Header.Sidereal.ArcaneFate");
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }
}
