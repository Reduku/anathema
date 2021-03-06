package net.sf.anathema.character.generic.dummy;

import net.sf.anathema.lib.data.ICondition;

public class DummyCondition implements ICondition {

  private boolean value;

  @Override
  public boolean isFulfilled() {
    return value;
  }

  public void setValue(boolean value) {
    this.value = value;
  }

}
