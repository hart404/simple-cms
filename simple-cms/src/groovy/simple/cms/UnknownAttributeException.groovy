package simple.cms

class UnknownAttributeException extends RuntimeException {

    UnknownAttributeException(String attribute) {

        super(String.format("Unknown attribute %s", attribute))
    }

}
