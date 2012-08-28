package simple.cms

class FileUploadException extends RuntimeException {

    public FileUploadException(Throwable t) {
        super(t)
    }

	public FileUploadException(String message) {
		super(message)
	}

}
