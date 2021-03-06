package net.sf.anathema.character.db.reporting.rendering;

import net.sf.anathema.character.db.DbCharacterModule;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.anima.AbstractAnimaEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.anima.AnimaTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class AnimaEncoderFactory extends AbstractAnimaEncoderFactory {

  @Override
  protected ITableEncoder getAnimaTableEncoder(Resources resources) {
    return new AnimaTableEncoder(resources, getFontSize(), new AnimaTableProvider());
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isOfType(DbCharacterModule.type);
  }
}
