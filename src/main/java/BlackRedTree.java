public class BlackRedTree {
    private static TreeNode root;

    private static class TreeNode{
        int value;
        private TreeNode left;
        private TreeNode right;
        boolean redColor; //если true-красное
    }

    public boolean add(int value){
        if (root != null){
            boolean res = addNewNode(root,value);
            root = balancingTree(root);
            return res;
        } else {
            root = new TreeNode();
            root.redColor = false;
            root.value = value;
            return true;
        }
    }
    private boolean addNewNode(TreeNode node, int value){
        if (node.value == value) {
            return false;
        } else {
            if (node.value > value){
                if (node.left == null) {
                    node.left = new TreeNode();
                    node.left.value = value;
                    node.left.redColor = true;
                    return true;
                } else {
                    boolean res = addNewNode(node.left, value);
                    node.left = balancingTree(node.left);
                    return res;
                }
            } else {
                if (node.right == null){
                    node.right = new TreeNode();
                    node.right.value = value;
                    node.right.redColor = true;
                    return true;
                } else {
                    boolean res = addNewNode(node.right, value);
                    node.right = balancingTree(node.right);
                    return res;
                }
            }
        }
    }

    private TreeNode balancingTree(TreeNode newNode){
        TreeNode res = newNode;
        boolean needBalancing;
        do {
            needBalancing = false;
            if (res.right != null && res.right.redColor && (res.left == null || !res.left.redColor)){
                needBalancing = true;
                res = rightRotate(res);
            }
            if (res.left != null && res.left.redColor && res.left.left == null || res.left.left.redColor){
                needBalancing = true;
                res = leftRotate(res);
            }
            if (res.left != null && res.right != null && res.left.redColor && res.right.redColor){
                needBalancing = true;
                res = swapColor(res);
            }
        } while (needBalancing);
        return res;

//        TreeNode grandParent;
//        while ((parent = newNode.parent) != null && parent.redColor == true) {
//            grandParent = parent.parent;
//            if (grandParent.left == parent){
//                TreeNode uncle = grandParent.right;
//                if (uncle != null && uncle.redColor) {
//                    parent.redColor = false;
//                    uncle.redColor = false;
//                    grandParent.redColor = true;
//                    newNode = grandParent;
//                    continue;
//                }
//                if (newNode == parent.right)
//            }
//        }
    }

    private static TreeNode rightRotate(TreeNode ourNode){
        TreeNode rotateNode = ourNode.right;
        TreeNode betweenNode = rotateNode.left;
        rotateNode.left = ourNode;
        ourNode.right = betweenNode;
        rotateNode.redColor = ourNode.redColor;
        ourNode.redColor = true;
        return rotateNode;
    }

    private static TreeNode leftRotate(TreeNode ourNode){
        TreeNode rotateNode = ourNode.left;
        TreeNode betweenNode = rotateNode.right;
        rotateNode.right = ourNode;
        ourNode.left = betweenNode;
        rotateNode.redColor = ourNode.redColor;
        ourNode.redColor = true;
        return rotateNode;
    }

    private static TreeNode swapColor(TreeNode ourNode){
        ourNode.redColor = !ourNode.redColor;
        ourNode.left.redColor = !ourNode.left.redColor;
        ourNode.right.redColor = !ourNode.right.redColor;
        return ourNode;
    }
//    public boolean find (int value) {
//        TreeNode currentNode = root;
//        while (currentNode != null) {
//            if (currentNode.value == value) {return true;}
//            else if (value < currentNode.value) {currentNode = currentNode.left;}
//            else {currentNode = currentNode.right;}
//        }
//        return false;
//    }


    public static void main(String[] args) {
        BlackRedTree ourTree = new BlackRedTree();
        ourTree.add(7);
        ourTree.add(2);
        ourTree.add(1);
        ourTree.add(4);
    }
}

