package au.com.carwashclub.services

import au.com.carwashclub.domain.Membership
import au.com.carwashclub.domain.MembershipType
import au.com.carwashclub.domain.Voucher
import au.com.carwashclub.domain.magento.CustomerEntity
import au.com.carwashclub.domain.magento.SalesFlatOrderItem
import java.security.SecureRandom
import au.com.carwashclub.domain.magento.SalesFlatOrder

class OrderProcessingService {

    static scope = "singleton"

    private static SecureRandom random = new SecureRandom();

    private static final String TOKEN_SYMBOLS = "ABCDEFGJKLMNPRSTUVWXYZ0123456789";

    private static final String PIN_SYMBOLS = "0123456789";

    private static final int TOKEN_LENGTH = 10;

    private static final int PIN_LENGTH = 4;

    public void processSalesOrder(SalesFlatOrder sale) {
            def items =  sale.saleFlatOrderItems;
            Iterator<SalesFlatOrderItem> sfIter = sale.saleFlatOrderItems.iterator()
            while ( sfIter.hasNext() ){
                SalesFlatOrderItem sf =  sfIter.next();
                if(sf.lineProcessed == null){
                    processSalesItem(sf);
                }
            }

            sale.setOrderProcessed(new Date())
            sale.save(flush: true);
    }

    private void processSalesItem(SalesFlatOrderItem saleItem) {
        def matchedMembershipType;
        log.info("Processing line item ID: " + saleItem.id + " Product:"  + saleItem.name + " Product Type: " + saleItem.productId);

        def membershipTypes = MembershipType.findAll("from MembershipType as mb where mb.name=:name",[name: saleItem.getName()]);
        membershipTypes.each {
            if(saleItem.getProductOptions().contains(it.optionsMatch)){
                matchedMembershipType = it;
            }
        }

        if(matchedMembershipType){
            log.info("Membership match succesful:" + matchedMembershipType.name + " " + matchedMembershipType.optionsMatch)
            for(int k = 0 ; k < saleItem.qtyOrdered; k ++) {
                log.info("Creating membership " + (1+k) + " of " + saleItem.qtyOrdered +" for SalesItem with ID " + saleItem.id);
                createMembership(matchedMembershipType, saleItem);
            }
            saleItem.setLineProcessed(new Date());
            saleItem.save(flush:true);
        }else {
            log.error("Membership match unsuccesful for sales order: " + saleItem.salesFlatOrder.id + " sales order item:" + saleItem.id);
        }

    }

    private Membership createMembership(MembershipType membershipType, SalesFlatOrderItem saleItem){
        Membership membership = new Membership(membershipType: membershipType, customerEntity: saleItem.getSalesFlatOrder().getCustomer());
        for(int i = 0; i < membershipType.voucherQuantity;  i++) {
            membership.addToVouchers(createVoucher(membership,saleItem.getSalesFlatOrder().getCustomer(), saleItem))
        }
        membership.save(flush:true);
        log.info("Membership created with "+ membership.vouchers.size() +" vouchers");
    }

    private Voucher createVoucher(Membership membership, CustomerEntity customer, SalesFlatOrderItem salesItem){
        Voucher voucher = new Voucher(customer: customer);
        voucher.setToken(randomToken())
        voucher.setPin(randomPin())
        voucher.setSalesItem(salesItem);
        log.info("Creating voucher. Token: " + voucher.getToken() + " Pin: " + voucher.getPin()  )
        return voucher;
    }

    private String randomToken() {
       char[] buf = new char[TOKEN_LENGTH];

       for (int idx = 0; idx < TOKEN_LENGTH; ++idx) {
        buf[idx] = TOKEN_SYMBOLS.charAt(random.nextInt(TOKEN_SYMBOLS.length()));
       }

       return new String(buf);
    }

    private String randomPin() {
       char[] buf = new char[PIN_LENGTH];

       for (int idx = 0; idx < PIN_LENGTH; ++idx) {
        buf[idx] = PIN_SYMBOLS.charAt(random.nextInt(PIN_SYMBOLS.length()));
       }

       return new String(buf);
    }
}
