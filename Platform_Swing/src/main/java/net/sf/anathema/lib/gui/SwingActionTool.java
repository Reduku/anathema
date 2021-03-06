package net.sf.anathema.lib.gui;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.NullCommand;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.icon.ImageProvider;

import javax.swing.Action;
import javax.swing.ImageIcon;
import java.awt.Component;

public class SwingActionTool implements Tool {

  private Command command = new NullCommand();
  private final SmartAction action = new SmartAction() {
    @Override
    protected void execute(Component parentComponent) {
      command.execute();
    }
  };

  @Override
  public void setIcon(RelativePath relativePath) {
    ImageIcon icon = new ImageProvider().getImageIcon(relativePath);
    action.setIcon(icon);
  }

  @Override
  public void setTooltip(String text) {
    action.setToolTipText(text);
  }

  @Override
  public void setText(String text) {
    action.setName(text);
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
  public void setCommand(Command command) {
    this.command = command;
  }

  public Action getAction() {
    return action;
  }
}