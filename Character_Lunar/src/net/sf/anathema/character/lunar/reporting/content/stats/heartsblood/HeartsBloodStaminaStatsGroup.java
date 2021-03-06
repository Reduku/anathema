package net.sf.anathema.character.lunar.reporting.content.stats.heartsblood;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.content.stats.AbstractTextStatsGroup;
import net.sf.anathema.lib.resources.Resources;

public class HeartsBloodStaminaStatsGroup extends AbstractTextStatsGroup<IHeartsBloodStats> {

  private final Resources resources;

  public HeartsBloodStaminaStatsGroup(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void addContent(PdfPTable table, Font font, IHeartsBloodStats stats) {
    String text = stats == null ? null : stats.getStaminaString();
    table.addCell(createTextCell(font, text));
  }

  @Override
  public Float[] getColumnWeights() {
    return new Float[]{1.5f};
  }

  @Override
  public String getTitle() {
    return resources.getString("Sheet.Lunar.HeartsBlood.Stamina");
  }
}
