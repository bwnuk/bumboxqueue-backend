package pl.hackyeah.bumboxqueue.error;

public enum ServiceErrorCode {
    CONNECTION_FAILED(1),
    INVALID_TIME_SYNTAX(2),
    PATIENT_ALREADY_EXIST(3),
    INVALID_GENDER_TYPE(4),
    PATIENT_NOT_FOUND(5),
    DOCTOR_ALREADY_EXISTED(6),
    SPECIALIZATION_DOES_NOT_EXIST(7);

    private int code;


    ServiceErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
