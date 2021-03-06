package net.sf.anathema.campaign.concrete.plot;

import net.sf.anathema.campaign.concrete.Series;
import net.sf.anathema.campaign.model.plot.IPlotElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SeriesDirtyTest {

  private Series series;

  @Before
  public void setUp() {
    this.series = new Series();
    series.setClean();
  }

  @Test
  public void testNewSeriesClean() {
    Assert.assertFalse(series.isDirty());
  }

  @Test
  public void testChangeDirties() throws Exception {
    series.getPlot().getRootElement().getDescription().getName().setText("Means and Inbetweens");
    Assert.assertTrue(series.getPlot().getRootElement().getDescription().getName().isDirty());
    Assert.assertTrue(series.isDirty());
  }

  @Test
  public void testAddElementDirties() throws Exception {
    Assert.assertFalse(series.isDirty());
    series.getPlot().getRootElement().addChild("Maximum Exposure");
    Assert.assertTrue(series.isDirty());
  }

  @Test
  public void testRemoveElementDirties() throws Exception {
    Assert.assertFalse(series.isDirty());
    IPlotElement addChild = series.getPlot().getRootElement().addChild("Maximum Exposure");
    series.setClean();
    Assert.assertFalse(series.isDirty());
    series.getPlot().getRootElement().removeChild(addChild);
    Assert.assertTrue(series.isDirty());
  }

  @Test
  public void testDeepChangeDirties() throws Exception {
    Assert.assertFalse(series.isDirty());
    IPlotElement child = series.getPlot().getRootElement().addChild("Maximum Exposure");
    series.setClean();
    Assert.assertFalse(series.isDirty());
    child.getDescription().getName().setText("Change for change's sake");
    Assert.assertTrue(series.isDirty());
  }
}