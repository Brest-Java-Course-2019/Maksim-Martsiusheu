package com.epam.course.cp.service;

public class ServiceResult {

    private boolean isOk;
    private String title;
    private String message;

    public static ServiceResult ok(String title, String message) {

        ServiceResult result = new ServiceResult(title, message);
        result.isOk = true;

        return result;
    }

    public static ServiceResult error(String title, String message) {

        ServiceResult result = new ServiceResult(title, message);
        result.isOk = false;

        return result;
    }

    public ServiceResult() {
    }

    private ServiceResult(String title, String message) {

        this.title = title;
        this.message = message;
    }

    public boolean isOk() {
        return isOk;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
