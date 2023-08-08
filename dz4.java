enum Color {
    RED,
    BLACK
}

class RBNode {
    int data;
    Color color;
    RBNode left, right, parent;

    RBNode(int data) {
        this.data = data;
        color = Color.RED; // новые узлы всегда красные
    }
}

public class dz4 {
    private RBNode root;

    private RBNode rotateLeft(RBNode node) {
        RBNode temp = node.right;
        node.right = temp.left;
        if (temp.left != null) {
            temp.left.parent = node;
        }
        temp.parent = node.parent;
        if (node.parent == null) {
            root = temp;
        } else if (node == node.parent.left) {
            node.parent.left = temp;
        } else {
            node.parent.right = temp;
        }
        temp.left = node;
        node.parent = temp;
        return temp;
    }

    private RBNode rotateRight(RBNode node) {
        RBNode temp = node.left;
        node.left = temp.right;
        if (temp.right != null) {
            temp.right.parent = node;
        }
        temp.parent = node.parent;
        if (node.parent == null) {
            root = temp;
        } else if (node == node.parent.left) {
            node.parent.left = temp;
        } else {
            node.parent.right = temp;
        }
        temp.right = node;
        node.parent = temp;
        return temp;
    }

    private void fixViolations(RBNode node) {
        RBNode parent, grandparent;
        while ((node != root) && (node.color == Color.RED) && (node.parent.color == Color.RED)) {
            parent = node.parent;
            grandparent = parent.parent;

            if (parent == grandparent.left) {
                RBNode uncle = grandparent.right;
                if ((uncle != null) && (uncle.color == Color.RED)) {
                    grandparent.color = Color.RED;
                    parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    node = grandparent;
                } else {
                    if (node == parent.right) {
                        rotateLeft(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    rotateRight(grandparent);
                    Color tempColor = parent.color;
                    parent.color = grandparent.color;
                    grandparent.color = tempColor;
                    node = parent;
                }
            } 
        }
        root.color = Color.BLACK;
    }

    public void insert(int data) {
        RBNode node = new RBNode(data);
        root = insertRec(root, node);
        fixViolations(node);
    }

    private RBNode insertRec(RBNode root, RBNode node) {
        if (root == null) {
            return node;
        }

        if (node.data < root.data) {
            root.left = insertRec(root.left, node);
            root.left.parent = root;
        } else if (node.data > root.data) {
            root.right = insertRec(root.right, node);
            root.right.parent = root;
        }
        return root;
    }

    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(RBNode root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.data + "(" + root.color + ")");
            inOrderRec(root.right);
        }
    }

    public static void main(String[] args) {
        dz4 tree = new dz4();

        tree.insert(7);
        tree.insert(6);
        tree.insert(5);
        tree.insert(4);
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);

        tree.inOrder();
    }
}
