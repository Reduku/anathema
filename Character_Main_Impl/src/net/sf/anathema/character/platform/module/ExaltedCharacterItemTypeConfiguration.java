package net.sf.anathema.character.platform.module;

import net.sf.anathema.character.CharacterPrintNameFileScanner;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.model.advance.ExperiencePointManagement;
import net.sf.anathema.character.impl.model.creation.bonus.BonusPointManagement;
import net.sf.anathema.character.impl.persistence.ExaltedCharacterPersister;
import net.sf.anathema.character.impl.view.TaskedCharacterView;
import net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.platform.module.repository.CharacterCreationTemplateFactory;
import net.sf.anathema.character.presenter.CharacterPresenter;
import net.sf.anathema.character.presenter.PlayerCharacterPointPresentation;
import net.sf.anathema.character.presenter.PointPresentationStrategy;
import net.sf.anathema.character.view.CharacterView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.module.AbstractPersistableItemTypeConfiguration;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.ItemViewFactory;
import net.sf.anathema.framework.presenter.SwingItemViewFactory;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.framework.view.SwingItemView;
import net.sf.anathema.initialization.ItemTypeConfiguration;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.Resources;

@ItemTypeConfiguration
@Weight(weight = 0)
public class ExaltedCharacterItemTypeConfiguration extends AbstractPersistableItemTypeConfiguration {

  public ExaltedCharacterItemTypeConfiguration() throws AnathemaException {
    super(new ItemType(CharacterItemTypeRetrieval.CHARACTER_ITEM_TYPE_ID,
            new RepositoryConfiguration(".ecg", "ExaltedCharacter/")));
  }

  @Override
  protected IRepositoryItemPersister createPersister(IApplicationModel model) {
    return new ExaltedCharacterPersister(getItemType(), CharacterGenericsExtractor.getGenerics(model), model.getMessaging());
  }

  @Override
  protected ItemViewFactory createItemViewFactory(final IApplicationModel model, final Resources resources) {
    return new SwingItemViewFactory() {
      @Override
      public SwingItemView createView(IItem item) throws AnathemaException {
        String printName = item.getDisplayName();
        ICharacter character = (ICharacter) item.getItemData();
        CharacterUI characterUI = new CharacterUI();
        ICharacterType characterType = ((ICharacter) item.getItemData()).getCharacterTemplate().getTemplateType().getCharacterType();
        IntegerViewFactory intValueDisplayFactory = IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(characterType);
        IntegerViewFactory markerLessIntValueDisplayFactory =
                IntValueDisplayFactoryPrototype.createWithoutMarkerForCharacterType(characterType);
        RelativePath typeIcon = characterUI.getSmallTypeIconPath(characterType);
        CharacterView characterView = new TaskedCharacterView(intValueDisplayFactory, printName, typeIcon, markerLessIntValueDisplayFactory);
        IBonusPointManagement bonusPointManagement = new BonusPointManagement(((ICharacter) item.getItemData()));
        IExperiencePointManagement experiencePointManagement = new ExperiencePointManagement(((ICharacter) item.getItemData()));
        PointPresentationStrategy pointPresentation =
                choosePointPresentation(character, characterView, bonusPointManagement, experiencePointManagement, resources);
        new CharacterPresenter((ICharacter) item.getItemData(), characterView, resources, model, pointPresentation).initPresentation();
        item.getItemData().setClean();
        return characterView;
      }
    };
  }

  private PointPresentationStrategy choosePointPresentation(ICharacter character, CharacterView characterView,
                                                            IBonusPointManagement bonusPointManagement,
                                                            IExperiencePointManagement experiencePointManagement, Resources resources) {
    if (character.getCharacterTemplate().isNpcOnly()) {
      return new NpcPointPresentation();
    }
    return new PlayerCharacterPointPresentation(resources, character, characterView, bonusPointManagement, experiencePointManagement);
  }

  @Override
  protected IItemTypeViewProperties createItemTypeCreationProperties(IApplicationModel anathemaModel, Resources resources) {
    ICharacterGenerics generics = CharacterGenericsExtractor.getGenerics(anathemaModel);
    CharacterCreationTemplateFactory factory = new CharacterCreationTemplateFactory(generics, resources);
    IRegistry<ICharacterType, ICasteCollection> casteCollectionIRegistry = generics.getCasteCollectionRegistry();
    IRepositoryFileResolver fileResolver = anathemaModel.getRepository().getRepositoryFileResolver();
    CharacterPrintNameFileScanner scanner =
            new RegExCharacterPrintNameFileScanner(generics.getCharacterTypes(), casteCollectionIRegistry, fileResolver);
    return new CharacterViewProperties(getItemType(), resources, factory, scanner);
  }
}
