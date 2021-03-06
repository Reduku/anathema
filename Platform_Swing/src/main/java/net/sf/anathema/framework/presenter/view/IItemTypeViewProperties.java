package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.workflow.wizard.selection.DialogBasedTemplateFactory;

public interface IItemTypeViewProperties {

  RelativePath getIcon();

  DialogBasedTemplateFactory getNewItemWizardFactory();

  String getLabelKey();

  AgnosticUIConfiguration getItemTypeUI();
}