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
				println "Deleted a Gateway"
			}
			catch (DataIntegrityViolationException e) {
				println("Unable to delete a Gateway: ${e}")
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
			println "Could not delete photo id ${id} because it wasn't found"
			return
		}
		try {
			photoInstance.delete(flush: true)
			println "Deleted photo id ${id} "
		}
		catch (DataIntegrityViolationException e) {
			println "Could not delete photo id ${id} because it is in use"
		}
	}
	


}
