class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
        "/"(controller: "voucher")
        "/pdfPreview"(view: '/_pdfVouchers')
		"500"(view:'/error')
	}
}
