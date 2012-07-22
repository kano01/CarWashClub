class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
        //Default admin home page
//        "/"(controller:"voucher" /list")
        "/"(controller: "voucher")
        "/pdf"(view:'/_pdfVouchers')
		"500"(view:'/error')
	}
}
