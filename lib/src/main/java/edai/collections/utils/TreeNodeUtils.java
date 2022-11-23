package edai.collections.utils;

import edai.collections.List;
import edai.collections.TreeNode;

public class TreeNodeUtils<T> {

    public static <T extends Comparable<T>> Object[] listPath(TreeNode<T> root, T value) {

        List<T> output = new List<>();

        if(root != null && fillPathInList(root, value, output)){
            return output.listData();
        }

        return new Object[0];
    }

    private static <T extends Comparable<T>> boolean fillPathInList(TreeNode<T> visitedNode, T value, List<T> outList){
        outList.insert(visitedNode.getData(), -1);

        final int comparisonResult = value.compareTo( visitedNode.getData() );

        if(comparisonResult == 0) return true;

        if(comparisonResult < 0){
            if(visitedNode.getLeft() == null) return false;

            return fillPathInList(visitedNode.getLeft(), value, outList);
        }else{
            if(visitedNode.getRight() == null) return false;

            return fillPathInList(visitedNode.getRight(), value, outList);
        }
    }

    public static <T extends Comparable<T>> Object[] listLeaves(TreeNode<T> root){
        List<T> output = new List<>();

        if(root != null)
            fillLeavesInList(root, output);

        return output.listData(); 
    }

    private static <T extends Comparable<T>> void fillLeavesInList(TreeNode<T> visitedNode, List<T> output) {

        final boolean hasLeft = visitedNode.getLeft() != null;
        final boolean hasRight = visitedNode.getRight() != null;

        if(hasLeft){
            fillLeavesInList(visitedNode.getLeft(), output);
        }

        if(hasRight){
            fillLeavesInList(visitedNode.getRight(), output);
        }

        if(!hasLeft && !hasRight){
            output.insert(visitedNode.getData(), -1);
        }
    }
    
}
