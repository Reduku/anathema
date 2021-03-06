package net.sf.anathema.character.presenter.magic.detail;

import net.sf.anathema.framework.swing.IView;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class NullMagicDetailPresenter implements MagicDetailPresenter {

  @Override
  public MagicDetailModel getModel() {
    return new NullMagicDetailModel();
  }

  @Override
  public String getDetailTitle() {
    return "No details available";
  }

  @Override
  public IView getView() {
    return new NullCharmDetailView();
  }

  @Override
  public void initPresentation() {
    //nothing to do
  }

  private static class NullCharmDetailView implements IView {
    @Override
    public JComponent getComponent() {
      return new JLabel("No details available");
    }
  }
}
