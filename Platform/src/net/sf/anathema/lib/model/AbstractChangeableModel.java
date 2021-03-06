package net.sf.anathema.lib.model;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import org.jmock.example.announcer.Announcer;

public abstract class AbstractChangeableModel implements Cloneable, IChangeableModel {

  private transient Announcer<IChangeListener> listeners = Announcer.to(IChangeListener.class);

  @Override
  protected Object clone() {
    try {
      AbstractChangeableModel clone = (AbstractChangeableModel) super.clone();
      clone.listeners = Announcer.to(IChangeListener.class);
      return clone;
    }
    catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    listeners.addListener(listener);
  }

  @Override
  public void removeChangeListener(IChangeListener listener) {
    listeners.removeListener(listener);
  }

  protected void fireChangeEvent() {
    listeners.announce().changeOccurred();
  }

  protected final Object getMutex() {
    return listeners;
  }
}