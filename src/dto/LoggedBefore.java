package dto;

import java.io.Serializable;

public class LoggedBefore implements Serializable {
    private boolean loggedBefore;

    public void setLoggedBefore(boolean loggedBefore) {
        this.loggedBefore = loggedBefore;
    }

    public boolean isLoggedBefore() {
        return loggedBefore;
    }
}
