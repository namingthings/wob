package de.androbit.wob.jira.auth;

public class JiraCredentials {
    String username;
    String password;

    public JiraCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
