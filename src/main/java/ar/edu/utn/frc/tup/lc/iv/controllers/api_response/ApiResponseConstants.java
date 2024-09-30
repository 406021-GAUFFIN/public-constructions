package ar.edu.utn.frc.tup.lc.iv.controllers.api_response;

/**
 * Constants for API responses.
 */
public final class ApiResponseConstants {

    /**
     * OK response code.
     */
    public static final String OK = "200";

    /**
     * Created response code.
     */
    public static final String CREATED = "201";

    /**
     * No content response code.
     */
    public static final String NO_CONTENT = "204";

    /**
     * Internal server error response code.
     */
    public static final String INTERNAL_SERVER_ERROR = "500";

    /**
     * Bad request response code.
     */
    public static final String BAD_REQUEST = "400";

    /**
     * Not found response code.
     */
    public static final String NOT_FOUND = "404";

    /**
     * Conflict response code.
     */
    public static final String CONFLICT = "409";

    /**
     * Success message.
     */
    public static final String OK_MESSAGE = "Operation completed successfully";

    /**
     * Resource created message.
     */
    public static final String CREATED_MESSAGE = "Resource created successfully";

    /**
     * Resource deleted message.
     */
    public static final String NO_CONTENT_MESSAGE = "Resource deleted successfully";

    /**
     * Internal server error message.
     */
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error";

    /**
     * Bad request message.
     */
    public static final String BAD_REQUEST_MESSAGE = "Invalid request";

    /**
     * Not found message.
     */
    public static final String NOT_FOUND_MESSAGE = "Resource not found";

    /**
     * Conflict message.
     */
    public static final String CONFLICT_MESSAGE = "Resource already exists";

    /**
     * Private constructor.
     */
    private ApiResponseConstants() {
    }
}
