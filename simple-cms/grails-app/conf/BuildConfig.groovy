grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsCentral()
		ebr()
        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        mavenCentral()
        mavenLocal()
        mavenRepo "http://snapshots.repository.codehaus.org"
        mavenRepo "http://repository.codehaus.org"
        mavenRepo "http://download.java.net/maven/2/"
        mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
        // runtime 'mysql:mysql-connector-java:5.1.5'
		compile ('com.jamesmurty.utils:java-xmlbuilder:0.6')
		compile ('com.drewnoakes:metadata-extractor:2.6.2')
    }

    plugins {
        build (":tomcat:7.0.47") {
            export = false
        }
        runtime ":hibernate:3.6.10.4"
		build (":release:2.0.4") { excludes 'svn' }
		compile ":ckeditor:3.6.3.0"
		compile ":searchable:0.6.5"
		runtime ":jquery:1.10.2"
		compile ":jquery-ui:1.8.24"
		compile ":joda-time:1.4"	
    }
}
