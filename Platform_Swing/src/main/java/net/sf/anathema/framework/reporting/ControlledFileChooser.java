package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.file.FileChoosingUtilities;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.nio.file.Files;
import java.nio.file.Path;

public class ControlledFileChooser implements FileChooser {

  private IItem item;
  private Resources resources;
  private JComponent parent;

  public ControlledFileChooser(IItem item, Resources resources, JComponent parent) {
    this.item = item;
    this.resources = resources;
    this.parent = parent;
  }

  @Override
  public Path getPrintFile() {
    String suggestedFileName = getBaseName(item) + PrintCommand.PDF_EXTENSION;
    Path selectedFile = FileChoosingUtilities.selectSaveFile(parent, suggestedFileName);
    if (selectedFile == null) {
      return null;
    }
    if (!checkFileAllowed(parent, selectedFile)) {
      return null;
    }
    return selectedFile;
  }

  private boolean checkFileAllowed(Component parentComponent, Path selectedFile) {
    String message = resources.getString("Anathema.Reporting.PrintAction.OverwriteMessage");
    String title = resources.getString("Anathema.Reporting.PrintAction.OverwriteTitle");
    return !Files.exists(selectedFile) || JOptionPane.showConfirmDialog(parentComponent, message, title, JOptionPane.YES_NO_OPTION) != 1;
  }

  private String getBaseName(IItem item) {
    return StringUtilities.getFileNameRepresentation(item.getDisplayName());
  }
}
