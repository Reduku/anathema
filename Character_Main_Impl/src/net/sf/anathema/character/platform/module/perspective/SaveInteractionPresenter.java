package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.character.perspective.model.model.ItemSelectionModel;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Interaction;
import net.sf.anathema.lib.file.RelativePath;

import java.io.IOException;

public class SaveInteractionPresenter {

  private final ItemSelectionModel model;
  private final Interaction interaction;

  public SaveInteractionPresenter(ItemSelectionModel model, Interaction interaction) {
    this.model = model;
    this.interaction = interaction;
  }

  public void initPresentation() {
    initializeAppearance();
    initializeEnabling();
    initializeCommand();
  }

  private void initializeAppearance() {
    interaction.setTooltip("AnathemaPersistence.SaveAction.Tooltip");
    interaction.setIcon(new RelativePath("icons/TaskBarSave24.png"));
  }

  private void initializeEnabling() {
    model.whenCurrentSelectionBecomesDirty(new EnableInteraction(interaction));
    model.whenCurrentSelectionBecomesClean(new DisableInteraction(interaction));
    interaction.disable();
  }

  private void initializeCommand() {
    interaction.setCommand(new SaveCurrent());
  }

  private class SaveCurrent implements Command {
    @Override
    public void execute() {
      try {
        model.saveCurrent();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
