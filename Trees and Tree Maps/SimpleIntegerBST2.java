import java.util.ArrayList;

/**
 * A simple BST that is only used to store Integer types. For use with final
 * exam.
 * 
 * @author john1819
 *
 */
public class SimpleIntegerBST2 {

    protected TreeNode<Integer> root;
    protected int size = 0;
    static final int COUNT = 10;

    /**
     * Add your code here - you may add additional methods but DO NOT ADD CLASS
     * VARIABLES.
     * 
     */

    public int findNumOdds() {
        int numOdd = 0;
        TreeNode<Integer> left = root.left;
        TreeNode<Integer> right = root.right;
        if (root.element % 2 != 0) {
            numOdd++;
        }
        while (left != null) {
            if (left.left == null) {
                TreeNode<Integer> rightMost = left.right;
                TreeNode<Integer> leftMost = left.right;
                while (rightMost != null) {
                    if (rightMost.element % 2 != 0) {
                        numOdd++;
                    }
                    rightMost = rightMost.right;
                }
                while (leftMost != null) {
                    if (leftMost.element % 2 != 0) {
                        numOdd++;
                    }
                    leftMost = leftMost.left;
                }
            } else {
                TreeNode<Integer> rightMost = left.left;
                TreeNode<Integer> leftMost = left.left;
                while (rightMost != null) {
                    if (rightMost.element % 2 != 0) {
                        numOdd++;
                    }
                    rightMost = rightMost.right;
                }
                while (leftMost != null) {
                    if (leftMost.element % 2 != 0) {
                        numOdd++;
                    }
                    leftMost = leftMost.left;
                }
            }
            left = left.left;
        }
        while (right != null) {
            if (right.left == null) {
                TreeNode<Integer> rightMost = right.right;
                TreeNode<Integer> leftMost = right.right;
                while (rightMost != null) {
                    if (rightMost.element % 2 != 0) {
                        numOdd++;
                    }
                    rightMost = rightMost.right;
                }
                while (leftMost != null) {
                    if (leftMost.element % 2 != 0) {
                        numOdd++;
                    }
                    leftMost = leftMost.left;
                }
            } else {
                TreeNode<Integer> rightMost = right.left;
                TreeNode<Integer> leftMost = right.left;
                while (rightMost != null) {
                    if (rightMost.element % 2 != 0) {
                        numOdd++;
                    }
                    rightMost = rightMost.right;
                }
                while (leftMost != null) {
                    if (leftMost.element % 2 != 0) {
                        numOdd++;
                    }
                    leftMost = leftMost.left;
                }
            }
            right = right.right;
        }
        return numOdd;
    }

    // DO NOT CHANGE BELOW THIS COMMENT
    public boolean search(Integer e) {
        TreeNode<Integer> current = root; // Start from the root

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
                return true; // Element is found
        }

        return false;
    }

    public boolean insert(Integer e) {
        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
            // Locate the parent node
            TreeNode<Integer> parent = null;
            TreeNode<Integer> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else
                    return false; // Duplicate node not inserted
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }

        size++;
        return true; // Element inserted successfully
    }

    protected TreeNode<Integer> createNewNode(Integer e) {
        return new TreeNode<Integer>(e);
    }

    public boolean delete(Integer e) {
        /**
         * Delete an element from the binary tree. Return true if the element is
         * deleted successfully Return false if the element is not in the tree
         */

// Locate the node to be deleted and also locate its parent node
        TreeNode<Integer> parent = null;
        TreeNode<Integer> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else
                break; // Element is in the tree pointed at by current
        }

        if (current == null)
            return false; // Element is not in the tree

// Case 1: current has no left child
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        } else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<Integer> parentOfRightMost = current;
            TreeNode<Integer> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
        }

        size--;
        return true; // Element deleted successfully
    }

    public void inorder() {
        inorder(root);
    }

    /**
     * In order helper method
     * 
     * @param root
     */
    protected void inorder(TreeNode<Integer> root) {
        // prints the tree contents in order
        if (root == null)
            return;
        inorder(root.left);
        System.out.println(root.element);
        inorder(root.right);

    }

    public void postorder() {
        // TODO Auto-generated method stub

    }

    public void preorder() {
        // TODO Auto-generated method stub

    }

    public int getSize() {

        return size;
    }

    public boolean isEmpty() {

        return size == 0;
    }

    // additional code modified from
    // https://www.geeksforgeeks.org/print-binary-tree-2-dimensions/
    // prints a 2-d representation of the tree
    private void print2DUtil(TreeNode<Integer> root, int space) {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += COUNT;

        // Process right child first
        print2DUtil(root.right, space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(root.element + "\n");

        // Process left child
        print2DUtil(root.left, space);
    }

    // Wrapper over print2DUtil()
    public void print2D() {
        // Pass initial space count as 0
        print2DUtil(root, 0);
    }

    /**
     * This inner class is static, because it does not access any instance
     * members defined in its outer class
     */
    public static class TreeNode<E extends Comparable<E>> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

}
