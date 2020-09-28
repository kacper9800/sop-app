/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.payload.response;

import java.util.List;
import pl.sop.dto.CollegeDTO;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private List<CollegeDTO> colleges;
    private Long selectedCollege;

    public JwtResponse(String token, Long id, String username, String email, List<String> roles, List<CollegeDTO> colleges,
        Long selectedCollege) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.colleges = colleges;
        this.selectedCollege = selectedCollege;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<CollegeDTO> getColleges() { return colleges; }

    public void setColleges(List<CollegeDTO> colleges) { this.colleges = colleges; }

    public Long getSelectedCollege() { return selectedCollege; }

    public void setSelectedCollege(Long selectedCollege) { this.selectedCollege = selectedCollege; }

}
