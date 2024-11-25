package euler;

import java.util.LinkedList;
import java.util.List;
 
public class IntTreeNode {
 
  private int value;
  private int downStreamTotal;
  private int originalRow;
  private int originalColumn;
  private List<IntTreeNode> childNodes;
 
  public IntTreeNode(int value) {
    this.value = value;
    this.childNodes = new LinkedList<>();
  }
 
  public void addChild(IntTreeNode childNode) {
    this.childNodes.add(childNode);
  }
 
/*
  public void showTreeNodes() {
    BreathFirstSearchPrintTreeNodes.printNodes(this);
  }
*/
 
  public int getValue() {
    return value;
  }
  
  public int getDownStreamTotal() {
	  return downStreamTotal;
  }
  
  public void setDownStreamTotal(int value) {
	  this.downStreamTotal = value;
  }
 
  public List<IntTreeNode> getChildNodes() {
    return childNodes;
  }
}