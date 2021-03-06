package net.sf.anathema.charmtree.filters;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.resources.Resources;
import org.dom4j.Element;

import javax.swing.JPanel;

public interface ICharmFilter {
  boolean acceptsCharm(ICharm charm, boolean isAncestor);

  boolean isDirty();

  void apply();

  void reset();

  void save(Element parent);

  boolean load(Element node);

  JPanel getFilterPreferencePanel(Resources resources);
}