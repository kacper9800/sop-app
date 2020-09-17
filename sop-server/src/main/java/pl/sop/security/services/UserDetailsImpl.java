/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.sop.entities.User;
import pl.sop.organizationStructure.College;

public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 1L;
  private Long id;
  private String username;
  private String email;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;
  private List<College> colleges;
  private Long selectedCollegeId;

  public UserDetailsImpl(Long id, String username, String email, String password,
      Collection<? extends GrantedAuthority> authorities, List<College> colleges, Long selectedCollegeId) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
    this.colleges = colleges;
    this.selectedCollegeId =  selectedCollegeId;
  }

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());
    return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(),
        user.getPassword(), authorities, user.getColleges(), user.getSelectedCollegeId());
  }

  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public List<College> getColleges() { return colleges; }

  public void setColleges(List<College> college) {
    this.colleges = college;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public Long getSelectedCollegeId() {
    return selectedCollegeId;
  }

  public void setSelectedCollegeId(Long selectedCollegeId) {
    this.selectedCollegeId = selectedCollegeId;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }


}
