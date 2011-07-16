package net.sf.anathema.character.alchemical.slots.view;

import javax.swing.Icon;

import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.resources.IResources;

public class CharmSlotsViewProperties extends BasicUi implements ICharmSlotsViewProperties
{
	public CharmSlotsViewProperties(IResources resources)
	{
		super(resources);
	}

	@Override
	public Icon getGenericSlotIcon() {
		return getIcon("AlchemicalButtonOrichalcumSecondEdition16.png");
	}

	@Override
	public Icon getDedicatedSlotIcon() {
		return getIcon("AlchemicalButtonSoulsteelSecondEdition16.png");
	}
	
	@Override
	public Icon getNewGenericSlotIcon() {
		return getIcon("AlchemicalButtonOrichalcumSecondEdition16.png");
	}

	@Override
	public Icon getNewDedicatedSlotIcon() {
		return getIcon("AlchemicalButtonSoulsteelSecondEdition16.png");
	}

}