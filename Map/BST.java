/**
 * wergthrjyhtewfqewtyj.
 *
 * @author jkbra
 * @version 1.01
 * @param <T> gwrehrhtwwgeth
 */
public class BST<T extends Comparable> implements Tree<T> {

    Node root;
    int size;

    /**
     * fregrfewgrebtegrw.
     */
    public BST() {
        root = null;
    }

    /**
     * btgr3fwgwefrb.
     *
     * @return rgegrfgbergwf
     */
    public Tree<T> getTree() {
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean add(T data) {
        Node n = new Node(data);


        if (root == null) { // empty tree, add as root
            root = n;
            return true;
        }

        Node stuff = root;

        while (stuff != null) {
            if (data.compareTo(stuff.data) > 0) { // go right
                if (stuff.right == null) {
                    // empty space on right, add here
                    stuff.right = n;
                    n.rent = stuff;
                    size++;
                    return true;
                }
                stuff = stuff.right;
            } else if (data.compareTo(stuff.data) < 0) { // go left
                if (stuff.left == null) {
                    // empty space on left, add here
                    stuff.left = n;
                    n.rent = stuff;
                    size++;
                    return true;
                }
                stuff = stuff.left;
            } else { // equal, found val in tree, don't add
                return false;
            }
        }
        return false; //its me beach
    }

    @Override
    public void clear() {
        root.left = null;
        root.right = null;
        root = null;
        size = 0;
    }

    //////////////////////////////////////////////////////////


    @Override
    @SuppressWarnings("unchecked")
    public T get(Object o) {
        Node n = new Node((T) o);

        if (root == null) { // empty tree, add as root
            return null;
        }
        Node a = getHer(root, o);
        if (a == null) {
            return null;
        }
        return a.getValue();
    }

    /**
     * rfevtgfbrfrgrbef.
     *
     * @param thi rvbgbrefwvwr
     * @param o   grbergfefv
     * @return grbgrewvfrve
     */
    public Node getHer(Node thi, Object o) {
        if (thi != null) {
            Node right = getHer(thi.right, o);
            Node left = getHer(thi.left, o);
            if (thi.getValue().equals(o)) { // go right
                return thi;
            } else if (right != null && right.getValue().equals(o)) {
                return right;
            } else if (left != null && left.getValue().equals(o)) {
                return left;
            }
        }
        return null;
    }

    ////////////////////////////////////////////////////////////



    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }
        Node found = getHer(root, (T) o);
        if (found != null) {
            removeNode(found);
        }
        return contains(o);
    }

    /*
    @Override
    public boolean remove(Object val) {
        try {
            Node curr = root;

            while (curr != null) {
                if (((T) val).compareTo(curr.data) > 0) { // go right
                    curr = curr.right;
                } else if (((T) val).compareTo(curr.data) < 0) { // go left
                    curr = curr.left;
                } else { // equal, found val in tree
                    break;
                }
            }
            if (curr == null) {
                return false;
            }
            // actually remove the node
            removeNode(curr);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
    */

    /**
     * rg31ewfvweqw.
     * @param n f4erfrwe
     * @param subtree gvfrevre
     */
    private void addToFarRight(Node n, Node subtree) {
        if (n == null) {
            root = subtree;
            subtree.rent = null;
        }
        if (n.right != null) {
            addToFarRight(n.right, subtree);
        } else {
            n.right = subtree;
            subtree.rent = n;
        }
    }

    /**
     * vfd wvevfwefwvfbvs.
     * @param n fvrfedsvre
     * @param subtree vfresvrwe
     */
    private void addToFarLeft(Node n, Node subtree) {
        if (n == null) {
            root = subtree;
            subtree.rent = null;
        }
        if (n.left != null) {
            addToFarLeft(n.left, subtree);
        } else {
            n.left = subtree;
            subtree.rent = n;
        }

    }

    /**
     * vregvrevrevreve.
     * @param parent vre
     * @param n vre
     */
    private void removeRight(Node parent, Node n) {
        if (n.right != null && n.left != null) {
            addToFarLeft(n.right, n.left);
            parent.right = n.right;
            n.right.rent = parent;
        } else if (n.right != null) {
            parent.right = n.right;
            n.right.rent = parent;
        } else if (n.left != null) {
            parent.right = n.left;
            n.left.rent = parent;
        } else {
            parent.right = null;
        }
    }

    /**
     * frevrervrrfrfrfr.
     * @param parent fvres
     * @param n vre
     */
    private void removeLeft(Node parent, Node n) {
        if (n.right != null && n.left != null) {
            addToFarRight(n.left, n.right);
            parent.left = n.left;
            n.left.rent = parent;
        } else if (n.right != null) {
            parent.left = n.right;
            n.right.rent = parent;
        } else if (n.left != null) {
            parent.left = n.left;
            n.left.rent = parent;
        } else {
            parent.left = null;
        }
    }

    /**
     * vrwevre.
     */
    private void removeRoot() {
        if (root.right != null && root.left != null) {
            addToFarRight(root.left, root.right);
            root = root.left;
            root.rent = null;
        } else if (root.left == null && root.right != null) {
            root = root.right;
            root.rent = null;
        } else if (root.left != null) {
            root = root.left;
            root.rent = null;
        } else {
            root = null;
        }
    }

    /**
     * fvrwefreved.
     * @param n vre
     */
    void removeNode(Node n) {
        size--;
        if (n.rent == null) {
            removeRoot();
        } else if (n.equals(n.rent.right)) {
            removeRight(n.rent, n);
        } else if (n.equals(n.rent.left)) {
            removeLeft(n.rent, n);
        }
    }


    ////////////////////////////////////////////////////

    @Override
    public boolean contains(Object n) {
        return get(n) != null;
    }


    ////////////////////////////////////////////////////


    @Override
    public boolean isEmpty() {
        return root == null;

    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        return size + 1;
    }

    /**
     * rgwtrehthewgehrte.
     *
     * @param ret  thre4geh
     * @param node gwernhtw4grehng
     * @return hrtmhre4gre
     */
    public int ret(int ret, Node node) {
        if (node != null) {
            if (node.left != null) {
                ret += ret(ret, node.left);
            }
            if (node.right != null) {
                ret += ret(ret, node.right);
            }
        }
        return ret;
    }

    /**
     * eert4her4.
     */
    public class Node implements Tree.Node<T> {

        Node right;
        Node left;
        Node rent;
        T data;

        /**
         * frevfregvregvrege.
         *
         * @param data gvrevgreer
         */
        public Node(T data) {
            this.right = null;
            this.left = null;
            this.rent = null;
            this.data = data;
        }

        /**
         * It does a thing yeagh.
         *
         * @return ten out of ten
         */
        public Node getRight() {
            return right;
        }

        /**
         * fegr3efwfqrvefvre.
         *
         * @param right whenit is s a fewfwe
         */
        public void setRight(Node right) {
            this.right = right;
        }

        /**
         * few wniefmkrw.
         *
         * @return fewjkfe
         */
        public Node getLeft() {
            return left;
        }

        /**
         * vrfwvevcrwvcre v.
         *
         * @param left vrevre
         */
        public void setLeft(Node left) {
            this.left = left;
        }

        /**
         * vrvrffvrrcrc.
         *
         * @return rfvr
         */
        public Node getParent() {
            return rent;
        }

        /**
         * rfvevrevrevrevr.
         *
         * @param rent frevrevre
         */
        public void setParent(Node rent) {
            this.rent = rent;
        }


        @Override
        public void setValue(T value) {
            this.data = value;
        }

        @Override
        public T getValue() {
            return data;
        }
    }

    /**
     * fvrevrevref4.
     *
     * @return fvrere
     */
    public Node getRoot() {
        return root;
    }

    /**
     * frwefrwe.
     *
     * @param root frverew
     */
    public void setRoot(Node root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return null;
    }

}