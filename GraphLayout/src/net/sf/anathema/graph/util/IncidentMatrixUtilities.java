package net.sf.anathema.graph.util;

import net.sf.anathema.graph.nodes.ISimpleNode;
import org.apache.commons.lang3.ArrayUtils;

public class IncidentMatrixUtilities {

  public static boolean[][] buildMatrix(ISimpleNode[] upperLayer, ISimpleNode[] lowerLayer) {
    boolean[][] matrix = new boolean[upperLayer.length][lowerLayer.length];
    for (int upperLayerIndex = 0; upperLayerIndex < upperLayer.length; upperLayerIndex++) {
      ISimpleNode[] children = upperLayer[upperLayerIndex].getChildren();
      for (int lowerLayerIndex = 0; lowerLayerIndex < lowerLayer.length; lowerLayerIndex++) {
        if (ArrayUtils.contains(children, lowerLayer[lowerLayerIndex])) {
          matrix[upperLayerIndex][lowerLayerIndex] = true;
        }
      }
    }
    return matrix;
  }

  public static boolean[] getColumnVector(boolean[][] matrix, int columnIndex) {
    boolean[] vector = new boolean[matrix.length];
    for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
      vector[rowIndex] = matrix[rowIndex][columnIndex];
    }
    return vector;
  }
}