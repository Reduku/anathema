package net.sf.anathema.character.reporting.text;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.MultiColumnText;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.resources.BackgroundInternationalizer;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.lib.resources.Resources;

public class BackgroundsTextEncoder extends AbstractTextEncoder {

  private final BackgroundInternationalizer internationalizer;

  public BackgroundsTextEncoder(PdfReportUtils utils, Resources resources) {
    super(utils, resources);
    this.internationalizer = new BackgroundInternationalizer(resources);
  }

  public void createParagraphs(MultiColumnText columnText, IGenericCharacter genericCharacter) throws DocumentException {
    IGenericTrait[] backgrounds = genericCharacter.getBackgrounds();
    if (backgrounds.length == 0) {
      return;
    }
    Phrase traitPhrase = createTextParagraph(createBoldTitle(getString(getLabelKey()) + ": "));
    for (int index = 0; index < backgrounds.length; index++) {
      if (index > 0) {
        traitPhrase.add(createTextChunk(", "));
      }
      IGenericTrait trait = backgrounds[index];
      traitPhrase.add(createTextChunk(internationalizer.getDisplayName((IBackgroundTemplate) trait.getType())));
      traitPhrase.add(createTextChunk(" " + String.valueOf(trait.getCurrentValue())));
    }
    columnText.addElement(traitPhrase);
  }

  private String getLabelKey() {
    return "TextDescription.Label.Backgrounds";
  }

}
