package au.com.carwashclub

class Franchise {

    String franchiseName

    String email

    String street

    String suburb

    String state

    Integer postcode

    String phone

    String contact

    static hasMany = [suppliers: au.com.carwashclub.Supplier]
    static constraints = {
        franchiseName()
        contact()
        phone()
        email(email:  true)
        street()
        suburb()
        state(inList: ["VIC","NSW", "SA", "TAS", "ACT", "QLD", "WA", "NT"])
        postcode(size: (4))
    }

    public String toString(){
        return getFranchiseName();
    }
}
