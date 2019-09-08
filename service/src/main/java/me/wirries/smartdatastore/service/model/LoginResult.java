package me.wirries.smartdatastore.service.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * This class is a simple POJO for the result of the login.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.2019
 */
public class LoginResult {

    private String user;
    private boolean success;

    /**
     * Default constructor.
     *
     * @param user    user
     * @param success login successful
     */
    public LoginResult(String user, boolean success) {
        this.user = user;
        this.success = success;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user", user)
                .append("success", success)
                .toString();
    }

}
