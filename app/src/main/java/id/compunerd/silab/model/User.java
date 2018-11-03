package id.compunerd.silab.model;

import java.util.List;

public class User {

    String value;
    String message;
    List<UserResult> result;

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public List<UserResult> getResult() {
        return result;
    }
}
