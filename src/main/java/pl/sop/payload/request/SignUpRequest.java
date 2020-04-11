/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */


package pl.sop.payload.request;

import pl.sop.dao.entitiy.Role;

import java.util.List;
import java.util.Set;

public class SignUpRequest {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Set<String> role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> roles) {
        this.role = roles;
    }
}
