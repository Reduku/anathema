package net.sf.anathema.character.generic.persistence.load.load;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_DURATION;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_EXALT;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_GROUP;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARMTYPE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_DURATION;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_PREREQUISITE_LIST;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_TRAIT;

public class CharmXmlTestUtils {

  public static Element createCharmElement(String charmId) {
    Element charmElement = new DefaultElement(TAG_CHARM);
    charmElement.addAttribute(ATTRIB_ID, charmId);
    charmElement.addAttribute(ATTRIB_EXALT, "Dummy");
    charmElement.addAttribute(ATTRIB_GROUP, "AbilityGroup");
    Element prerequisiteListElement = charmElement.addElement(TAG_PREREQUISITE_LIST);
    fillBasicPrerequisites(prerequisiteListElement);
    charmElement.addElement(TAG_DURATION).addAttribute(ATTRIB_DURATION, "Duration");
    charmElement.addElement(TAG_CHARMTYPE).addAttribute(ATTRIB_TYPE, "Simple");
    return charmElement;
  }

  public static void fillBasicPrerequisites(Element prerequisiteListElement) {
    Element prerequisiteElement = prerequisiteListElement.addElement(TAG_TRAIT);
    prerequisiteElement.addAttribute("id", "Archery");
    prerequisiteElement.addAttribute("value", "4");
    prerequisiteListElement.addElement("essence").addAttribute("value", "3");
  }
}