package au.com.carwashclub.domain

class MembershipType {

    String name
    // string to match in product option
    String optionsMatch
    Integer voucherQuantity

    static constraints = {
        name(inList: ["Bronze Membership"])
        optionsMatch(inList: ["x12 Washes","x6 Washes","x3 Washes"])
        voucherQuantity(inList: [12,6,3])
    }

    def String toString(){
        return name + " " + optionsMatch;
    }
}
