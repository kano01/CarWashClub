package au.com.carwashclub.domain

class MembershipType {

    String type

    static constraints = {
        type(inList: ["Gold","Silver","Bronze"])
    }
}
