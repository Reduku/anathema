package net.sf.anathema.charmtree.filters;

import net.sf.anathema.lib.resources.Resources;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ObtainableCharmFilterPage implements ICharmFilterPage
{
	Resources resources;
	boolean[] setting;
	
	public ObtainableCharmFilterPage(Resources resources, boolean[] setting)
	{
		this.resources = resources;
		this.setting = setting;
	}
	
	@Override
	public JPanel getContent()
	{
		JPanel panel = new JPanel();
		
		JCheckBox checkBox = new JCheckBox(resources.getString("CharmFilters.Obtainable.OnlyShowAvailable"));
		checkBox.setSelected(setting[0]);
		checkBox.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent arg0)
			{
				setting[0] = arg0.getStateChange() == ItemEvent.SELECTED;
			}	
		});
		
		panel.add(checkBox);
		
		TitledBorder title;
		title = BorderFactory.createTitledBorder(resources.getString("CharmFilters.Obtainable.PenumbraFilter"));
		panel.setBorder(title);
		
		return panel;
	}
}
