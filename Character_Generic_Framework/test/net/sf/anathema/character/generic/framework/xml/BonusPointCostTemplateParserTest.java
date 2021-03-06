package net.sf.anathema.character.generic.framework.xml;

import net.sf.anathema.character.generic.dummy.DummyFavorableGenericTrait;
import net.sf.anathema.character.generic.dummy.template.DummyXmlTemplateRegistry;
import net.sf.anathema.character.generic.framework.xml.creation.BonusPointCostTemplateParser;
import net.sf.anathema.character.generic.framework.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.experience.ICostAnalyzer;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BonusPointCostTemplateParserTest extends BasicTemplateParsingTestCase {

  private static final String ORIGINAL_TEMPLATE_ID = "original";
  private BonusPointCostTemplateParser parser;
  private GenericBonusPointCosts originalTemplate;

  @Before
  public void setUp() throws Exception {
    DummyXmlTemplateRegistry<GenericBonusPointCosts> registry = new DummyXmlTemplateRegistry<>();
    this.parser = new BonusPointCostTemplateParser(registry, MartialArtsLevel.Celestial);
    originalTemplate = new GenericBonusPointCosts();
    registry.register(ORIGINAL_TEMPLATE_ID, originalTemplate);
  }

  @Test
  public void testUsedTemplateRemainsUnmodifiedWithParsing() throws Exception {
    originalTemplate.setAttributeCost(3, 3);
    String changeContent = "<attributes><generalAttribute><fixedCost cost=\"4\" /></generalAttribute></attributes>";
    GenericBonusPointCosts parsedTemplate = parser.parseTemplate(getDocumentRoot(createUsesOriginalTemplate(changeContent)));
    assertEquals(3, originalTemplate.getAttributeCosts(new DummyFavorableGenericTrait(AttributeType.Wits, 1, false)));
    assertEquals(4, parsedTemplate.getAttributeCosts(new DummyFavorableGenericTrait(AttributeType.Wits, 1, false)));
  }

  @Test
  public void testNoFavoredAttributeCost() throws Exception {
    String xml = "<root><attributes><generalAttribute><fixedCost cost=\"4\" /></generalAttribute></attributes></root>";
    GenericBonusPointCosts costs = parseXmlToCost(xml);
    assertEquals(4, costs.getAttributeCosts(new DummyFavorableGenericTrait(AttributeType.Wits, 1, false)));
    assertEquals(4, costs.getAttributeCosts(new DummyFavorableGenericTrait(AttributeType.Wits, 1, true)));
  }

  @Test
  public void testMissingFavoredValueIsOverwrittenByNewGeneralValue() throws Exception {
    originalTemplate.setAttributeCost(3, 3);
    String changeContent = "<attributes><generalAttribute><fixedCost cost=\"4\" /></generalAttribute></attributes>";
    GenericBonusPointCosts parsedTemplate = parser.parseTemplate(getDocumentRoot(createUsesOriginalTemplate(changeContent)));
    assertEquals(4, parsedTemplate.getAttributeCosts(new DummyFavorableGenericTrait(AttributeType.Wits, 1, false)));
    assertEquals(4, parsedTemplate.getAttributeCosts(new DummyFavorableGenericTrait(AttributeType.Wits, 1, true)));
  }

  @Test
  public void testFavoredAttributeCostSpecified() throws Exception {
    String xml = "<root><attributes><generalAttribute><fixedCost cost=\"4\" /></generalAttribute><favoredAttribute><fixedCost cost=\"3\" /></favoredAttribute></attributes></root>";
    GenericBonusPointCosts costs = parseXmlToCost(xml);
    assertEquals(4, costs.getAttributeCosts(new DummyFavorableGenericTrait(AttributeType.Wits, 1, false)));
    assertEquals(3, costs.getAttributeCosts(new DummyFavorableGenericTrait(AttributeType.Wits, 1, true)));
  }

  private String createUsesOriginalTemplate(String content) {
    return "<root uses=\"original\">" + content + "</root>";
  }

  @Test
  public void testMaximumFreeVirtueDotsUnchanged() throws Exception {
    String xml = "<root><advantages><virtues><fixedCost cost=\"3\"/></virtues></advantages></root>";
    GenericBonusPointCosts costs = parseXmlToCost(xml);
    assertEquals(3, costs.getVirtueCosts().getRatingCosts(1));
    assertEquals(3, costs.getMaximumFreeVirtueRank());
  }

  @Test
  public void testMaximumFreeVirtueDotsChanged() throws Exception {
    String xml = "<root><advantages><virtues><fixedCost cost=\"3\"/><maximumFreeVirtueRank rank=\"4\"/></virtues></advantages></root>";
    GenericBonusPointCosts costs = parseXmlToCost(xml);
    assertEquals(3, costs.getVirtueCosts().getRatingCosts(1));
    assertEquals(4, costs.getMaximumFreeVirtueRank());
  }

  @Test
  public void testBasicCharmCostIsSetAndMartialArtsCostEquals() throws Exception {
    String xml = "<root><charms><generalCharms><fixedCost cost=\"7\" /></generalCharms><favoredCharms><fixedCost cost=\"5\" /></favoredCharms></charms></root>";
    GenericBonusPointCosts costs = parseXmlToCost(xml);
    DummyCharm testCharm = new DummyCharm("test");
    assertCosts7WhenItsNotFavored(costs, testCharm);
    assertCosts5WhenItIsFavored(costs, testCharm);
    assertCosts7WhenItsUnfavoredMartialArts(costs, testCharm);
    assertCosts5WhenItsFavoredMartialArts(costs, testCharm);
    assertEquals(7, costs.getMagicCosts(testCharm, new ICostAnalyzer() {
      @Override
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return MartialArtsLevel.Sidereal;
      }

      @Override
      public boolean isMagicFavored(IMagic magic) {
        return false;
      }

      @Override
      public boolean isOccultFavored() {
        return false;
      }
    }));
    assertEquals(5, costs.getMagicCosts(testCharm, new ICostAnalyzer() {
      @Override
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return MartialArtsLevel.Sidereal;
      }

      @Override
      public boolean isMagicFavored(IMagic magic) {
        return true;
      }

      @Override
      public boolean isOccultFavored() {
        return false;
      }
    }));
  }

  private GenericBonusPointCosts parseXmlToCost(String xml) throws AnathemaException {
    Element element = DocumentUtilities.read(xml).getRootElement();
    return parser.parseTemplate(element);
  }

  private void assertCosts5WhenItsFavoredMartialArts(GenericBonusPointCosts costs, DummyCharm testCharm) {
    assertEquals(5, costs.getMagicCosts(testCharm, new ICostAnalyzer() {
      @Override
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return MartialArtsLevel.Celestial;
      }

      @Override
      public boolean isMagicFavored(IMagic magic) {
        return true;
      }

      @Override
      public boolean isOccultFavored() {
        return false;
      }
    }));
  }

  private void assertCosts5WhenItIsFavored(GenericBonusPointCosts costs, DummyCharm testCharm) {
    assertEquals(5, costs.getMagicCosts(testCharm, new ICostAnalyzer() {
      @Override
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return null;
      }

      @Override
      public boolean isMagicFavored(IMagic magic) {
        return true;
      }

      @Override
      public boolean isOccultFavored() {
        return false;
      }
    }));
  }

  private void assertCosts7WhenItsNotFavored(GenericBonusPointCosts costs, DummyCharm testCharm) {
    assertEquals(7, costs.getMagicCosts(testCharm, new ICostAnalyzer() {
      @Override
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return null;
      }

      @Override
      public boolean isMagicFavored(IMagic magic) {
        return false;
      }

      @Override
      public boolean isOccultFavored() {
        return false;
      }
    }));
  }

  @Test
  public void testBasicCharmCostIsSetAndMartialArtsCostOverrides() throws Exception {
    String xml = "<root><charms><generalCharms><fixedCost cost=\"7\" /></generalCharms><favoredCharms><fixedCost cost=\"5\" /></favoredCharms><generalHighLevelMartialArtsCharms><fixedCost cost=\"10\"/></generalHighLevelMartialArtsCharms>"
      + "<favoredHighLevelMartialArtsCharms><fixedCost cost=\"7\"/></favoredHighLevelMartialArtsCharms></charms></root>";
    GenericBonusPointCosts costs = parseXmlToCost(xml);
    DummyCharm testCharm = new DummyCharm("test");
    assertCosts7WhenItsNotFavored(costs, testCharm);
    assertCosts5WhenItIsFavored(costs, testCharm);
    assertCosts7WhenItsUnfavoredMartialArts(costs, testCharm);
    assertCosts5WhenItsFavoredMartialArts(costs, testCharm);
    assertEquals(10, costs.getMagicCosts(testCharm, new ICostAnalyzer() {
      @Override
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return MartialArtsLevel.Sidereal;
      }

      @Override
      public boolean isMagicFavored(IMagic magic) {
        return false;
      }

      @Override
      public boolean isOccultFavored() {
        return false;
      }
    }));
    assertEquals(7, costs.getMagicCosts(testCharm, new ICostAnalyzer() {
      @Override
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return MartialArtsLevel.Sidereal;
      }

      @Override
      public boolean isMagicFavored(IMagic magic) {
        return true;
      }

      @Override
      public boolean isOccultFavored() {
        return false;
      }
    }));
  }

  private void assertCosts7WhenItsUnfavoredMartialArts(GenericBonusPointCosts costs, DummyCharm testCharm) {
    assertEquals(7, costs.getMagicCosts(testCharm, new ICostAnalyzer() {
      @Override
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return MartialArtsLevel.Celestial;
      }

      @Override
      public boolean isMagicFavored(IMagic magic) {
        return false;
      }

      @Override
      public boolean isOccultFavored() {
        return false;
      }
    }));
  }
}