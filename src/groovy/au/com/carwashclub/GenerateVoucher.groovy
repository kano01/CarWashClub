package au.com.carwashclub

import java.security.SecureRandom
import org.apache.log4j.Logger

/**
 * Created by IntelliJ IDEA.
 * User: Kane
 * Date: 16/07/12
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
 */
class GenerateVoucher {

    private static Logger log = Logger.getInstance(this);

    private static SecureRandom random = new SecureRandom();

    private static final String TOKEN_SYMBOLS = "ABCDEFGJKLMNPRSTUVWXYZ0123456789";

    private static final String PIN_SYMBOLS = "0123456789";

    private static final GenerateVoucher INSTANCE = new GenerateVoucher();

    private static final int TOKEN_LENGTH = 10;

    private static final int PIN_LENGTH = 4;


    private GenerateVoucher() {

    }

    public static getInstance(){
        return INSTANCE;
    }

    String[] generateTokenAndPin(){
        String[] tokenPin = new String[2];

        tokenPin[0] = randomToken();
        tokenPin[1] = randomPin();

        log.info("Token generated Token: " + tokenPin[0] + " Pin: " + tokenPin[1]  )
        return tokenPin;

    }

    String randomToken(){
       char[] buf = new char[TOKEN_LENGTH];

       for (int idx = 0; idx < TOKEN_LENGTH; ++idx) {
        buf[idx] = TOKEN_SYMBOLS.charAt(random.nextInt(TOKEN_SYMBOLS.length()));
       }

       return new String(buf);

    }

    String randomPin(){
       char[] buf = new char[PIN_LENGTH];

       for (int idx = 0; idx < PIN_LENGTH; ++idx) {
        buf[idx] = PIN_SYMBOLS.charAt(random.nextInt(PIN_SYMBOLS.length()));
       }

       return new String(buf);

    }


}
