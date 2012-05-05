package net.sf.anathema.platform.svgtree.document.visualizer;

import java.awt.Dimension;


import net.sf.anathema.platform.svgtree.document.DocumentCascade;
import org.apache.batik.util.SVGConstants;
import org.dom4j.Element;

public class VisualizedGraph implements IVisualizedGraph<DocumentCascade> {

  private final Element graphElement;
  private final Dimension dimension;

  public VisualizedGraph(Element graphElement, Dimension dimension) {
    this.graphElement = graphElement;
    this.dimension = dimension;
  }

  @Override
  public Dimension getDimension() {
    return dimension;
  }

  @Override
  public boolean isSingleNode() {
    return false;
  }

  @Override
  public void translateBy(double x, double y) {
    graphElement.addAttribute(SVGConstants.SVG_TRANSFORM_ATTRIBUTE, "translate(" + x + " " + y + ")");
  }

  @Override
  public void addTo(DocumentCascade cascade) {
    cascade.add(graphElement);
  }
}