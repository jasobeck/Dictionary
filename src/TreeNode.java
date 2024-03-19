// Jane Sobeck
// 3/15/2024
// CS 145 Lab 6
//     The purpose of these programs program is to help me understand both Binary search trees
// as well as how to traverse the tree in different orders.

//    Class TreeNode contains the methods needed to construct/modify/else the TreeNode objects that
// are used to represent each entry in the Dictionary Tree
public class TreeNode {

    public String keyword;
    public TreeNode left;
    public TreeNode right;

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String email;
    private String number;

    // This Constructor calls the next constructor to create a node with given
    // keyword, and
    // sets left and right node pointers to null.
    public TreeNode(String keyword) {
        this(keyword, null, null);
    }

    // Creates a TreeNode object with keyword as keyword, and pointer nodes to the
    // left and right
    // references.
    public TreeNode(String keyword, TreeNode left, TreeNode right) {
        this.keyword = keyword;
        this.left = left;
        this.right = right;
    }

    // returns this TreeNode's firstname string
    public String getFirstName() {
        return this.firstName;
    }

    // returns this TreeNode's lastname string
    public String getLastName() {
        return this.lastName;
    }

    // returns this TreeNode's street address string
    public String getAddress() {
        return this.address;
    }

    // returns this TreeNode's city string
    public String getCity() {
        return this.city;
    }

    // returns this TreeNode's state string
    public String getState() {
        return this.state;
    }

    // returns this TreeNode's zip code string
    public String getZip() {
        return this.zip;
    }

    // returns this TreeNode's email address string
    public String getEmail() {
        return this.email;
    }

    // returns this TreeNode's phone number string
    public String getNumber() {
        return this.number;
    }

    // sets first name data to string param
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // sets last name data to string param
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // sets street address data to string param
    public void setAddress(String address) {
        this.address = address;
    }

    // sets city data to string param
    public void setCity(String city) {
        this.city = city;
    }

    // sets state data to string param
    public void setState(String state) {
        this.state = state;
    }

    // sets zip code data to string param
    public void setZip(String zip) {
        this.zip = zip;
    }

    // sets email data to string param
    public void setEmail(String email) {
        this.email = email;
    }

    // sets phone number data to string param
    public void setNumber(String number) {
        this.number = number;
    }

    // Calls necessary setter based on enum type and passes data to be set
    public void setData(DataEnum dataType, String data) {
        switch (dataType) {
            case DataEnum.F:
                setFirstName(data);
                break;

            case DataEnum.L:
                setLastName(data);
                break;

            case DataEnum.A:
                setAddress(data);
                break;

            case DataEnum.C:
                setCity(data);
                break;

            case DataEnum.S:
                setState(data);
                break;

            case DataEnum.Z:
                setZip(data);
                break;

            case DataEnum.E:
                setEmail(data);
                break;

            case DataEnum.P:
                setNumber(data);
                break;

            default:
                System.out.println("Encountered err, unable to set" + dataType + " " + data);
                break;
        }
    }

    // Calls necessary getter based on enum and returns the getter's data
    public String getData(DataEnum dataType) {
        switch (dataType) {
            case DataEnum.F:
                return this.firstName;

            case DataEnum.L:
                return this.lastName;

            case DataEnum.A:
                return this.address;

            case DataEnum.C:
                return this.city;

            case DataEnum.S:
                return this.state;

            case DataEnum.Z:
                return this.zip;

            case DataEnum.E:
                return this.email;

            case DataEnum.P:
                return this.number;

            default:
                System.out.println("Err: unable to get" + dataType + " from " + this.keyword);
                return "Err";
        }
    }

    // Prints keyword and all other data strings of this node/entry to the console
    public void displayData() {
        System.err.println();
        System.out.println("Entry keyword: " + this.keyword);
        for (DataEnum data : DataEnum.values()) {
            System.out.println("    " + data.label + ": " + this.getData(data));
        }
    }

    // Compares this node's keyword to a different keyword, returns -1 if
    // this.keyword comes
    // before newKey alphabetically.
    public int compareKey(String newKey) {
        char[] firstKey = newKey.toUpperCase().toCharArray();
        char[] secondKey = this.keyword.toUpperCase().toCharArray();
        int shortKeyLength;
        if (this.shorterKeyword(newKey)) {
            shortKeyLength = secondKey.length;
        } else {
            shortKeyLength = firstKey.length;
        }
        for (int i = 0; i < shortKeyLength; i++) {
            char charOne = firstKey[i];
            char charTwo = secondKey[i];
            if (charOne < charTwo) {
                return -1;
            } else if (charOne == charTwo) {
                continue;
            } else if (charOne > charTwo) {
                return 1;
            }

        }
        // If all the characters of the shorter keyword match the same character
        // placements
        // for the longer keyword, returns -1 if the shorter keyword was this.keyword,
        // else returns
        // 1 if newKey is shorter than this.keyword
        if (secondKey.length > firstKey.length) {
            return -1;
        } else if (firstKey.length > secondKey.length) {
            return 1;
        } else { // if firstKey == secondKey
            return 0;
        }
    }

    // Returns true if this.keyword is shorter or equal to the other keyword
    public boolean shorterKeyword(String testKey) {
        if (this.keyword.length() <= testKey.length()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Transfers the state from the given node to this node, while preserving
     * references to the left and right children of this node.
     */
    public void transferState(TreeNode from) {
        for (DataEnum type : DataEnum.values()) {
            this.setData(type, from.getData(type));
        }
        this.keyword = from.keyword;
    }
}
