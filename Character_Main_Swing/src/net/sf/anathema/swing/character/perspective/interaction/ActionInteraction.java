package net.sf.anathema.swing.character.perspective.interaction;

import net.sf.anathema.framework.perspective.ToolBar;
import net.sf.anathema.interaction.CommandProxy;
import net.sf.anathema.interaction.Interaction;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.resources.Resources;

import java.awt.Component;

public class ActionInteraction implements Interaction {

  private final CommandProxy commandProxy = new CommandProxy();
  private final SmartAction action = new SmartAction() {
    @Override
    protected void execute(Component parentComponent) {
      commandProxy.execute();
    }
  };
  private final Resources resources;

  public ActionInteraction(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void setIcon(RelativePath relativePath) {
    action.setIcon(new ImageProvider().getImageIcon(relativePath));
  }

  @Override
  public void setTooltip(String key) {
    action.setToolTipText(resources.getString(key));
  }

  @Override
  public void setText(String key) {
    action.setName(resources.getString(key));
  }

  @Override
  public void enable() {
    action.setEnabled(true);
  }

  @Override
  public void disable() {
    action.setEnabled(false);
  }

  @Override
  public void setCommand(net.sf.anathema.interaction.Command command) {
    commandProxy.setDelegate(command);
  }

  public void addTo(ToolBar toolbar) {
    toolbar.addTools(action);
  }
}