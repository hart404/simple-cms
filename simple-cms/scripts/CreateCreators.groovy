includeTargets << grailsScript("_GrailsInit")

target(main: "Create the WidgetCreator classes") {
    grails("create-domain-class simple.cms.SCMSGalleryWidgetCreator")
    grails("create-domain-class simple.cms.SCMHTMLSWidgetCreator")
    grails("create-domain-class simple.cms.SCMSLightboxWidgetCreator")
    grails("create-domain-class simple.cms.SCMSMapWidgetCreator")
    grails("create-domain-class simple.cms.SCMSMenuWidgetCreator")
    grails("create-domain-class simple.cms.SCMSPhotoWidgetCreator")
    grails("create-domain-class simple.cms.SCMSWidgetCreator")
}

setDefaultTarget(main)
