package net.sf.anathema.character.library.trait.view;

import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.lib.gui.layout.SwingLayoutUtils;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class RearToggleButtonTraitViewWrapper<K extends ITraitView< ? >> extends
    AbstractToggleButtonTraitViewWrapper<K> implements IToggleButtonTraitView<K> {

  public RearToggleButtonTraitViewWrapper(K view, IIconToggleButtonProperties properties, boolean selected) {
    super(view, properties, selected);
  }

  @Override
  public void addComponents(JPanel panel) {
    super.addComponents(panel);
    addInnerView(panel);
    JComponent button = getButton().getComponent();
    panel.add(button, SwingLayoutUtils.constraintsForImageButton(button));
  }
}