<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Carwash Club</title>
        <style type="text/css"> b { color: green; } </style>
    </head>
    <body>
     <div class="banner">
     <rendering:inlinePng bytes="${imageBytes}" /></div>
       <p>
            <b>Carwash Club</b>
            Here are your vouchers!!
       </p>
        <g:each in="${vouchers}" var="voucher">
            <p>Token:${voucher.token} Pin:${voucher.pin}</p>
        </g:each>
    </body>
</html>