// Jane Sobeck
// 3/15/2024
// CS 145 Lab 6
//     The purpose of these programs program is to help me understand both Binary search trees
// as well as how to traverse the tree in different orders.

//THIS IS AN ALPHABETICALLY SORTED BINARY SEARCH TREE!!!!
//DOES NOT BALANCE HEIGHT, BUT KEEPS NODES SORTED/RESORTS WHEN ADDING/DELETING/MODIFYING ENTRIES
//ALSO ADDED SEARCH METHOD THAT USES ALPHABETIC BINARY SEARCH, RATHER THAN NORMAL TRAVERSAL OF TREE

import java.util.NoSuchElementException;

//Dictionary tree handles all the adds/edits/removals/searches/etc to the tree
public class DictionaryTree {

    public TreeNode overallRoot;
    public int entryCount = 0;

    // Constructs DictionaryTree object with given TreeNode as overall root
    // (Only gets constructed with a null overallRoot for our case)
    public DictionaryTree(TreeNode overallRoot) {
        this.overallRoot = overallRoot;
    }

    // Base method of recursive method for adding nodes onto the tree
    public void addNode(TreeNode newNode) {
        overallRoot = addNode(overallRoot, newNode);
    }

    // Recursive method that adds nodes to the tree alphabetically, sends it to the
    // left if
    // lower (i.e "A"), sends it to the right if higher (i.e "Z")
    public TreeNode addNode(TreeNode root, TreeNode newNode) {
        if (root == null) {
            root = newNode;
        } else if (root.compareKey(newNode.keyword) == -1) {
            root.left = addNode(root.left, newNode);
        } else if (root.compareKey(newNode.keyword) == 1) {
            root.right = addNode(root.right, newNode);
        }
        return root;
    }

    // Returns true if a node with the given keyword exists in the tree
    public boolean exists(String keyword) {
        if (search(keyword) != null) {
            return true;
        } else {
            return false;
        }
    }

    // Base method of recursive method for deleting nodes from the tree
    public void delNode(String keyword) {
        overallRoot = delNode(overallRoot, keyword);
    }

    // Deletes Node with matching keyword from the tree, retains alphabetic
    // structure in tree
    // by setting deleted node's 'space' to the minimum node of the right
    // child(recursive)
    public TreeNode delNode(TreeNode node, String target) {
        if (node == null) {
            return null;
        } else if (node.compareKey(target) == -1) {
            node.left = delNode(node.left, target);
        } else if (node.compareKey(target) == 1) {
            node.right = delNode(node.right, target);
        } else { // node.keyword == target
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                TreeNode temp = getMinNode(node.right);
                node.keyword = temp.keyword;
                node.right = delNode(node.right, temp.keyword);
            }
        }
        return node;
    }

    // retuns node with lowest alphabetically ordered keyword for the entire Tree
    // using overallRoot
    public TreeNode getMinNode() {
        if (overallRoot == null) {
            throw new NoSuchElementException();
        }
        return getMinNode(overallRoot);
    }

    // returns noode with lowest alphabetically ordered keyword under given root
    // node
    private TreeNode getMinNode(TreeNode root) {
        if (root.left == null) {
            return root;
        } else {
            return getMinNode(root.left);
        }
    }

    // Base recursive method that lists all nodes under overallRoot
    public void listAllNodes() {
        listAllNodes(overallRoot);
    }

    // Recursive method that lists all nodes under given root param
    public void listAllNodes(TreeNode root) {
        if (root != null) {
            listAllNodes(root.left);
            root.displayData();
            listAllNodes(root.right);
        }
    }

    // Base recursive method that lists all node keywords under overallRoot
    public void listKeywords() {
        listKeywords(overallRoot);
    }

    // Recursive method that lists all node keyword under given root param
    public void listKeywords(TreeNode root) {
        if (root != null) {
            listKeywords(root.left);
            System.out.println(root.keyword);
            listKeywords(root.right);
        }
    }

    // Base recurive method that searches for node with keyword matching "keyword"
    // under overallRoot
    // Searches by using an alphabetic binary search, rather than a normal BST
    // traversal order
    public TreeNode search(String keyword) {
        return search(overallRoot, keyword);
    }

    // Recursive method that searches for node with keyword matching keyword under
    // given root param
    // Searches by using an alphabetic binary search, rather than a normal BST
    // traversal order
    public TreeNode search(TreeNode root, String keyword) {
        if (root == null) {
            return null;
        } else if (root.keyword.toUpperCase().equals(keyword.toUpperCase())) {
            return root;
        } else if (root.compareKey(keyword) == -1) {
            return search(root.left, keyword);
        } else { // root.compareKey(keyword) == 1)
            return search(root.right, keyword);
        }
    }

    // Base recurive method that searches for node with keyword matching "keyword"
    // under overallRoot
    // Searches by using Pre-order traversal method
    public TreeNode searchPre(String keyword) {
        return searchPre(overallRoot, keyword);
    }

    // Recursive method that searches for node with keyword matching keyword under
    // given root
    // Searches by using Pre-order traversal method
    public TreeNode searchPre(TreeNode node, String keyword) {
        if (node == null)
            return null;
        if (node.keyword.equals(keyword))
            return node;
        TreeNode searchLeft = searchPre(node.left, keyword);
        if (searchLeft == null) {
            return searchPre(node.right, keyword);
        } else {
            return searchLeft;
        }
    }

    // Base recurive method that searches for node with keyword matching "keyword"
    // under overallRoot
    // Searches by using In-order traversal method
    public TreeNode searchIn(String keyword) {
        return searchIn(overallRoot, keyword);
    }

    // Recursive method that searches for node with keyword matching keyword under
    // given root
    // Searches by using In-order traversal method
    public TreeNode searchIn(TreeNode node, String keyword) {
        if (node == null)
            return null;
        TreeNode leftNode = searchIn(node.left, keyword);
        if (leftNode != null)
            return leftNode;
        if (node.keyword.equals(keyword))
            return node;
        return searchIn(node.right, keyword);
    }

    // Base recurive method that searches for node with keyword matching "keyword"
    // under overallRoot
    // Searches by using Post-order traversal method
    public TreeNode searchPost(String keyword) {
        return searchPost(overallRoot, keyword);
    }

    // Recursive method that searches for node with keyword matching keyword under
    // given root
    // Searches by using Post-order traversal method
    public TreeNode searchPost(TreeNode node, String keyword) {
        if (node == null)
            return null;
        TreeNode leftNode = searchPost(node.left, keyword);
        if (leftNode != null)
            return leftNode;
        TreeNode rightNode = searchPost(node.right, keyword);
        if (rightNode != null)
            return rightNode;
        if (node.keyword.equals(keyword)) {
            return node;
        } else {
            return null;
        }
    }
}