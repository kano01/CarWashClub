// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }


grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']


// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// enable query caching by default
grails.hibernate.cache.queries = true

// set per-environment serverURL stem for creating absolute links
environments {
    development {
        grails.serverURL = "http://localhost:8080/${appName}"
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration


    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

log4j = {
//    def catalinaBase = System.properties.getProperty('catalina.base')
//    if (!catalinaBase) catalinaBase = '.'   // just in case
//    def logDirectory = "${catalinaBase}/logs"
    def logDirectory = "./logs"

    appenders {

        // Use if we want to prevent creation of a stacktrace.log file.
        'null' name:'stacktrace'
        // Use this if we want to modify the default appender called 'stdout'.
        console name:'stdout', layout:pattern(conversionPattern: '[%t] %-5p %c{2} %x - %m%n')
        // Custom log file.
        rollingFile name:"appLog",
                        file:logDirectory + '/carwashclub.log',
                        maxFileSize:'1MB',
                        maxBackupIndex:5,
                        layout:pattern(conversionPattern: '%d{[EEE, dd-MMM-yyyy @ HH:mm:ss.SSS]} [%t] %-5p %c %x - %m%n')
        rollingFile name:"processing",
                        file:logDirectory + '/processing.log',
                        maxFileSize:'1MB',
                        maxBackupIndex:5,
                        layout:pattern(conversionPattern: '%d{[EEE, dd-MMM-yyyy @ HH:mm:ss.SSS]} %-5p %c{2} %x - %m%n')
    }

// This is for the built-in stuff and from the default Grails-1.2.1 config.
    error 'org.codehaus.groovy.grails.web.servlet',  //  controllers
            'org.codehaus.groovy.grails.web.pages', //  GSP
            'org.codehaus.groovy.grails.web.sitemesh', //  layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping', // URL mapping
            'org.codehaus.groovy.grails.commons', // core / classloading
            'org.codehaus.groovy.grails.plugins', // plugins
            'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
            'org.springframework',
            'org.hibernate',
            'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log' // Jetty

    error 'grails.app' // Set the default log level for our app code.

    info 'grails.app.conf.bootstrap' // Set the log level per type and per type.class

    // Move anything that should behave differently into this section.
    environments {
        development {
            // Configure the root logger to output to stdout and appLog appenders.
            root {
                error 'stdout','appLog'
                additivity = true
            }

            info 'grails.app'
            info 'org.hibernate.SQL'
            // log to the processor file
            info processing: ['grails.app.jobs.au.com.carwashclub.jobs.SalesPollerJob',
                    'grails.app.services.au.com.carwashclub.services.VoucherService']

        }

        test {
            // Configure the root logger to only output to appLog appender.
           root {
                error 'stdout','appLog'
                additivity = true
            }

            warn 'grails.app.service'
            warn 'grails.app.controller'
        }

        production {
            // Configure the root logger to only output to appLog appender.
            root {
                error 'appLog'
                additivity = true
            }

            warn 'grails.app.service'
            warn 'grails.app.controller'
        }
    }

}

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'au.com.carwashclub.domain.User'
//grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'au.com.carwashclub.UserRole'
//grails.plugins.springsecurity.authority.className = 'au.com.carwashclub.Role'
grails.plugins.springsecurity.userLookup.authoritiesPropertyName = 'roles'
grails.plugins.springsecurity.securityConfigType = "InterceptUrlMap"
//grails.plugins.springsecurity.successHandler.defaultTargetUrl = '/index'

grails.plugins.springsecurity.interceptUrlMap = [
   '/franchise/**':    ['ROLE_ADMIN'],
   '/supplier/**':   ['ROLE_ADMIN'],
   '/voucher/**':   ['ROLE_ADMIN', 'ROLE_SUPPLIER'],
   '/customerEntity/**':   ['ROLE_ADMIN'],
//   '/validate/**':   ['ROLE_SUPPLIER'],
   '/js/**':        ['IS_AUTHENTICATED_ANONYMOUSLY'],
   '/css/**':       ['IS_AUTHENTICATED_ANONYMOUSLY'],
   '/images/**':    ['IS_AUTHENTICATED_ANONYMOUSLY'],
   '/*':            ['IS_AUTHENTICATED_ANONYMOUSLY'],
   '/login/**':     ['IS_AUTHENTICATED_ANONYMOUSLY'],
   '/logout/**':    ['IS_AUTHENTICATED_ANONYMOUSLY']
]


grails.plugin.quartz2.autoStartup = true

org{
	quartz{

		scheduler.instanceName = 'CarWashAppScheduler'
		threadPool.class = 'org.quartz.simpl.SimpleThreadPool'
		threadPool.threadCount = 1
		threadPool.threadsInheritContextClassLoaderOfInitializingThread = true
		jobStore.class = 'org.quartz.simpl.RAMJobStore'
		jobStore.misfireThreshold =60000

	}
}