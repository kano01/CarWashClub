package au.com.carwashclub.services

import java.security.SecureRandom
import au.com.carwashclub.domain.magento.SalesFlatOrder
import au.com.carwashclub.domain.magento.SalesFlatOrderItem
import au.com.carwashclub.domain.MembershipType
import au.com.carwashclub.domain.Membership
import au.com.carwashclub.domain.Voucher
import au.com.carwashclub.domain.magento.CustomerEntity

class VoucherService {

    static scope = "singleton"

    private static SecureRandom random = new SecureRandom();

    private static final String TOKEN_SYMBOLS = "ABCDEFGJKLMNPRSTUVWXYZ0123456789";

    private static final String PIN_SYMBOLS = "0123456789";

    private static final int TOKEN_LENGTH = 10;

    private static final int PIN_LENGTH = 4;

    public void processSalesItem(SalesFlatOrderItem saleItem){
        def matchedMembershipType;

        def membershipTypes = MembershipType.findAll("from MembershipType as mb where mb.name=:name",[name: saleItem.getName()]);
        membershipTypes.each {
            if(saleItem.getProductOptions().contains(it.optionsMatch)){
                matchedMembershipType = it;
            }
        }

        if(matchedMembershipType){
            log.info("Membership match succesful:" + matchedMembershipType.name + " " + matchedMembershipType.optionsMatch)
            for(int i = 0 ; i < saleItem.qtyOrdered; i ++) {
                createMembership(matchedMembershipType, saleItem);
            }
        }else {
            log.info("Membership match unsuccesful");
        }

    }

    private Membership createMembership(MembershipType membershipType, SalesFlatOrderItem saleItem){
        Membership membership = new Membership(membershipType: membershipType, customerEntity: saleItem.getSalesFlatOrder().getCustomer());
        membership.save()
        for(int i = 0; i < membershipType.voucherQuantity;  i++) {
            Voucher voucher = createVoucher(membership,saleItem.getSalesFlatOrder().getCustomer())
            membership.addToVouchers(voucher);
        }

        log.info("Membership created with "+ membership.vouchers.size() +" vouchers");
    }

    private createVoucher(Membership membership, CustomerEntity customer){
        Voucher voucher = new Voucher(customer: customer);
        String[] tokenPin = generateTokens();
        voucher.setToken(tokenPin[0])
        voucher.setPin(tokenPin[1])
        if(membership != null){
            voucher.setMembership(membership);
        }
        voucher.save()
        return voucher;
    }

    private String[] generateTokens() {

        String[] tokenPin = new String[2];

        tokenPin[0] = randomToken();
        tokenPin[1] = randomPin();

        log.info("Token generated Token: " + tokenPin[0] + " Pin: " + tokenPin[1]  )
        return tokenPin;


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
