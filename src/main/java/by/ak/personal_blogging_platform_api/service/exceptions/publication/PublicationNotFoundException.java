package by.ak.personal_blogging_platform_api.service.exceptions.publication;

public class PublicationNotFoundException extends RuntimeException {

    public PublicationNotFoundException() {
    }

    public PublicationNotFoundException(String message) {
        super(message);
    }

    public PublicationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PublicationNotFoundException(Throwable cause) {
        super(cause);
    }
}
