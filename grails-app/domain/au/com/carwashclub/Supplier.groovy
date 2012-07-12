package au.com.carwashclub

class Supplier extends User{

    String supplierName

    String email

    String street

    String suburb

    String state

    Integer postcode

    String phone

    String contact

    Integer bsb

    String accountName

    String website

    def Franchise franchise

    Supplier(){
        authorities = "ROLE_SUPPLIER"
    }

    static constraints = {
        supplierName(blank: false)
        contact(blank: false)
        email(email:  true, blank: false)
        street()
        suburb()
        state(inList: ["VIC","NSW", "SA", "TAS", "ACT", "QLD", "WA", "NT"])
        postcode(nullable: true, size: (4))
        bsb(nullable: true, size: (4))
        franchise nullable:true
        authorities(display: false)
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(this.supplierName);

    return result.toString();
  }
}
