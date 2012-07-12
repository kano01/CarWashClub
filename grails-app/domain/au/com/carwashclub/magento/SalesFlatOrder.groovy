package au.com.carwashclub.magento

class SalesFlatOrder {

    String status

    String customerFirstname

    String customerLastname

    Date createdAt

    Date updatedAt

    CustomerEntity customer

    Date voucherSent

    static hasMany = [saleFlatOrderItems: SalesFlatOrderItem]

    static mapping = {
        id column: "entity_id"
        version false
    }

    static constraints = {
        id()
        createdAt()
        customer()
        voucherSent(nullable: true)

    }

    public String toString() {
        return id;
    }
}
