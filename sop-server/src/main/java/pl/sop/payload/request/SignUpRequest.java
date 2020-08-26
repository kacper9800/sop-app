/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */


package pl.sop.payload.request;

import java.util.Set;

public class SignUpRequest {

    private String token;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Set<String> role;
    private Long collegeId;

    public SignUpRequest() {
    }

    public SignUpRequest(String token, String firstName, String lastName, String username,
        String email, String password, Set<String> role, Long collegeId) {
        this.token = token;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.collegeId = collegeId;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

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

    public Long getCollegeId() { return collegeId; }

    public void setCollegeId(Long collegeId) { this.collegeId = collegeId; }
}
