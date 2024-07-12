package by.ak.personal_blogging_platform_api.service.exceptions.publication;

public class PublicationServiceExceptoin extends RuntimeException {

    public PublicationServiceExceptoin() {
    }

    public PublicationServiceExceptoin(String message) {
        super(message);
    }

    public PublicationServiceExceptoin(String message, Throwable cause) {
        super(message, cause);
    }

    public PublicationServiceExceptoin(Throwable cause) {
        super(cause);
    }
}
