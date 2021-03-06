package net.sf.anathema.character.presenter.advance;

import net.sf.anathema.character.view.advance.IExperienceConfigurationViewProperties;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;
import net.sf.anathema.lib.gui.table.columsettings.IntegerTableColumnSettings;
import net.sf.anathema.lib.gui.table.columsettings.StringTableColumnSettings;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Icon;
import javax.swing.table.TableModel;
import java.awt.Color;

public class ExperienceConfigurationViewProperties implements IExperienceConfigurationViewProperties {

  private final TableModel tableModel;
  private final BasicUi basicUi;
  private Resources resources;

  public ExperienceConfigurationViewProperties(Resources resources, TableModel tableModel) {
    this.basicUi = new BasicUi();
    this.tableModel = tableModel;
    this.resources = resources;
  }

  @Override
  public TableModel getTableModel() {
    return tableModel;
  }

  @Override
  public ITableColumnViewSettings[] getColumnSettings() {
    return new ITableColumnViewSettings[]{new StringTableColumnSettings(), new IntegerTableColumnSettings(-10000, 10000, 5, Color.RED)};
  }

  @Override
  public Icon getDeleteIcon() {
    return basicUi.getRemoveIcon();
  }

  @Override
  public Icon getAddIcon() {
    return basicUi.getAddIcon();
  }

  @Override
  public String getTotalString() {
    return resources.getString("CardView.Experience.Total");
  }
}