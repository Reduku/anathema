package net.sf.anathema.character.reporting.pdf.content.willpower;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = WillpowerContent.class)
public class WillpowerContentFactory implements ReportContentFactory<WillpowerContent> {

  private final Resources resources;

  public WillpowerContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public WillpowerContent create(ReportSession session, IGenericCharacter character) {
    return new WillpowerContent(resources, character);
  }
}
