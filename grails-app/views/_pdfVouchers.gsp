<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Carwash Club</title>
        <style type="text/css">
            b {
                color: green;
            }

            div.voucher {
                border: 2px solid gray;
                width: 250px;
                height: 250px;
                display: inline-block;
                padding: 10px;
                margin: 10px;
            }

            .even {
                float: left;
            }

            .odd {
                float: right;
            }
        </style>

    </head>
    <body>
     <div class="banner">
     <g:if test="${imageBytes}">
            <rendering:inlinePng bytes="${imageBytes}" /></div>
     </g:if>

       <p>
            <b>Carwash Club</b>
            Here are your vouchers!!
       </p>

            <g:each in="${vouchers}" var="voucher" status="i">
                <div class="${(i % 2 == 0) ? 'even' : 'odd'}, voucher">
                    ${voucher.membership.membershipType}

                    <p>Token:${voucher.token}</p>
                    <p>Pin:${voucher.pin}</p>
                </div>

            </g:each>
    </body>
</html>