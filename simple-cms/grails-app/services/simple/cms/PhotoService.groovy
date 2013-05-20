package simple.cms

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.transaction.annotation.Transactional

class PhotoService {
	
	@Transactional(readOnly = false)
	def deleteUnnecessaryGateways() {
		def defaultPhotos = SCMSPhoto.findAllBySource("http://new.mcdowellsonoran.org")
		defaultPhotos.reverse().each { gateway ->
			try {
				gateway.delete(flush: true)
			}
			catch (DataIntegrityViolationException e) {
			}
			catch (Exception e) {
				println("Unable to delete a Gateway with other exception: ${e}")
			}
		}
	}
	
	@Transactional(readOnly = false)
	def deleteIndividualPhoto(id) {
		def photoInstance = SCMSPhoto.get(id)
		if (!photoInstance) {
			return
		}
		try {
			photoInstance.delete(flush: true)
		}
		catch (DataIntegrityViolationException e) {
			println "Could not delete photo id ${id} because it is in use"
		}
	}

}
