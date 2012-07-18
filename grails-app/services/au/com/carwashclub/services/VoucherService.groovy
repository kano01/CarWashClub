package au.com.carwashclub.services

import java.security.SecureRandom

class VoucherService {

    static scope = "singleton"

    private static SecureRandom random = new SecureRandom();

    private static final String TOKEN_SYMBOLS = "ABCDEFGJKLMNPRSTUVWXYZ0123456789";

    private static final String PIN_SYMBOLS = "0123456789";

    private static final int TOKEN_LENGTH = 10;

    private static final int PIN_LENGTH = 4;

    public String[] generateTokens() {

        String[] tokenPin = new String[2];

        tokenPin[0] = randomToken();
        tokenPin[1] = randomPin();

        log.info("Token generated Token: " + tokenPin[0] + " Pin: " + tokenPin[1]  )
        return tokenPin;


    }


    String randomToken() {
       char[] buf = new char[TOKEN_LENGTH];

       for (int idx = 0; idx < TOKEN_LENGTH; ++idx) {
        buf[idx] = TOKEN_SYMBOLS.charAt(random.nextInt(TOKEN_SYMBOLS.length()));
       }

       return new String(buf);

    }

    String randomPin() {
       char[] buf = new char[PIN_LENGTH];

       for (int idx = 0; idx < PIN_LENGTH; ++idx) {
        buf[idx] = PIN_SYMBOLS.charAt(random.nextInt(PIN_SYMBOLS.length()));
       }

       return new String(buf);

    }
}
