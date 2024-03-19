// Jane Sobeck
// 3/15/2024
// CS 145 Lab 6
//     The purpose of these programs program is to help me understand both Binary search trees
// as well as how to traverse the tree in different orders.

//Enum to get the Datatype label just from the first character representing the data type
public enum DataEnum {
    L("Last Name"),
    F("First Name"),
    A("Street Address"),
    C("City"),
    S("State"),
    Z("Zip Code"),
    E("E-mail Address"),
    P("Phone Number");

    public final String label;

    // Enum constructor
    private DataEnum(String label) {
        this.label = label;
    }
}
