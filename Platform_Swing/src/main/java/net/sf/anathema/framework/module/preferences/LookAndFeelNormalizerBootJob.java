package net.sf.anathema.framework.module.preferences;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IBootJob;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.plaf.metal.MetalLookAndFeel;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.USER_LOOK_AND_FEEL_CLASSNAME;
import static net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement.SYSTEM_PREFERENCES;

/**
 * Removes the "ForceMetalLookAndFeel" preference and sets the "UserLookAndFeel" preference
 * to "javax.swing.plaf.metal.MetalLookAndFeel" if "ForceMetalLookAndFeel" was set to {@code true}.
 * <p/>
 * Note although it is not possible to set both preferences using Anathema if they were set
 * nevertheless by other means "UserLookAndFeel" will not be update regardless the value of
 * "ForceMetalLookAndFeel" (it will still remove "ForceMetalLookAndFeel").
 */
@BootJob
@Weight(weight = 20)
public class LookAndFeelNormalizerBootJob implements IBootJob {
  private static final String LOOK_AND_FEEL_PREFERENCE = "ForceMetalLookAndFeel";

  @Override
  public void run(Resources resources, IApplicationModel model) {
    String storedClassName = SYSTEM_PREFERENCES.get(USER_LOOK_AND_FEEL_CLASSNAME, null);
    if (storedClassName == null) {
      if (SYSTEM_PREFERENCES.getBoolean(LOOK_AND_FEEL_PREFERENCE, false)) {
        SYSTEM_PREFERENCES.put(USER_LOOK_AND_FEEL_CLASSNAME, MetalLookAndFeel.class.getName());
      }
    }
    SYSTEM_PREFERENCES.remove(LOOK_AND_FEEL_PREFERENCE);
  }
}
