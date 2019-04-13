package com.epam.course.cp.service;

/**
 * The {@code ServiceResult} is a wrapper class
 * for result of {@code service} classes work
 */
public class ServiceResult {

    /**
     * Represents is a service call successful or not
     */
    private boolean isOk;

    /**
     * Tittle of returning message
     */
    private String title;

    /**
     * Message describing a result of service call
     */
    private String message;

    /**
     * Constructs new object with given title and message, describing
     * service call as successful one
     *
     * @param title new title of an object
     * @param message new message of an object
     * @return New {@code ServiceResult} with given title, message, where field isOk = true
     */
    public static ServiceResult ok(String title, String message) {

        ServiceResult result = new ServiceResult(title, message);
        result.isOk = true;

        return result;
    }

    /**
     * Constructs new object with given title and message, describing
     * service call as unsuccessful one
     *
     * @param title new title of an object
     * @param message new message of an object
     * @return New {@code ServiceResult} with given title, message, where field isOk = false
     */
    public static ServiceResult error(String title, String message) {

        ServiceResult result = new ServiceResult(title, message);
        result.isOk = false;

        return result;
    }

    /**
     * Constructs an empty new object
     */
    public ServiceResult() {
    }

    private ServiceResult(String title, String message) {

        this.title = title;
        this.message = message;
    }

    /**
     * Gets the result success
     *
     * @return a boolean representing if it's success or not
     */
    public boolean isOk() {
        return isOk;
    }

    /**
     * Gets a title of a {@code ServiceResult}
     *
     * @return a string representing title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets a message, describing {@code ServiceResult}
     *
     * @return String representing a message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the {@code ServiceResult} success
     *
     * @param ok boolean, representing is it a successful one or not
     */
    public void setOk(boolean ok) {
        isOk = ok;
    }

    /**
     * Sets a title to {@code ServiceResult} object
     *
     * @param title string representation of a title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets a message to {@code ServiceResult} object
     *
     * @param message string representation of a message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ServiceResult{" +
                "isOk=" + isOk +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
