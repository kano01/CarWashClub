<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">

        <r:require module="jquery-ui"/>
		<g:layoutHead/>
        <r:layoutResources/>

	</head>
	<body>
		<div id="grailsLogo" role="banner">
            <div class="banner"><img src="${resource(dir: 'images', file: 'cwc_logo.png')}" alt="Car Wash Club"/></div>
            <div class="login">
                <sec:ifLoggedIn>
                Welcome Back <strong><sec:username/>!</strong>  <br/>
                <g:link controller='logout' action='index' class="button">Logout</g:link>
                </sec:ifLoggedIn>
            </div>

              <div id="menu">
              <sec:ifAnyGranted roles="ROLE_ADMIN">
               <span> <g:link class="button" controller="customerEntity">Customers</g:link> </span>
               <span> <g:link class="button" controller="voucher">Vouchers</g:link> </span>
               <span> <g:link class="button" controller="salesFlatOrder">Sales</g:link> </span>
               <span> <g:link class="button" controller="franchise">Franchises</g:link> </span>
               <span> <g:link class="button" controller="supplier">Suppliers</g:link> </span>

              </sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_SUPPLIER">
               <span>  <g:link class="button" controller="voucher">Vouchers</g:link> </span>
              </sec:ifAnyGranted>
            </div>

        </div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application" />

        <r:layoutResources />

      <script>
      $(function() {
          $( ".button").button();
      });
      </script>
	</body>
</html>