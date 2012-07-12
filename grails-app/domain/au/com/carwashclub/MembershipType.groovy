package au.com.carwashclub

class MembershipType {

    String type

    static constraints = {
        type(inList: ["Gold","Silver","Bronze"])
    }
}
