package pl.sop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import net.bytebuddy.asm.Advice.Local;
import pl.sop.organizationStructure.College;
import pl.sop.organizationStructure.Department;
import pl.sop.organizationStructure.Faculty;
import pl.sop.organizationStructure.Institute;

@Entity
@Table(name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "login"),
        @UniqueConstraint(columnNames = "email")
    })
public class User extends BasicEntity implements Serializable {

  @NotBlank
  @Size(max = 20)
  @Column(name = "login", nullable = false)
  private String username;

  @NotBlank
  @Size(max = 120)
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "firstName", nullable = false)
  private String firstName;

  @Column(name = "lastName", nullable = false)
  private String lastName;

  @NotBlank
  @Size(max = 50)
  @Email
  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "phone", nullable = false)
  private String phone;

  @Column(name = "activation_key", nullable = false)
  private String activationKey;

  @Column(name = "academic_title", nullable = false)
  private String academic_title;

  @Column(name = "birthDate", nullable = false)
  private LocalDate birthDate;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  //  @ManyToOne(fetch = FetchType.EAGER)
  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_colleges",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "college_id"))
  private List<College> colleges;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_companies",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "company_id"))
  private List<Company> companies;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_faculties",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "faculty_id"))
  private Set<Faculty> faculties = new HashSet<>();

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_institutes",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "institute_id"))
  private Set<Institute> institutes = new HashSet<>();

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_departments",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "department_id"))
  private Set<Department> departments = new HashSet<>();

  @Column(name = "selected_college_id")
  private Long selectedCollegeId;

  public User(@NotBlank @Size(max = 20) String username,
      @NotBlank @Size(max = 50) @Email String email,
      @NotBlank @Size(max = 120) String password,
      @NotBlank List<College> colleges) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.colleges = colleges;
  }

  public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password,
      String firstName, String lastName, @NotBlank @Size(max = 50) @Email String email,
      String phone, String activationKey, String academic_title, LocalDate birthDate) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.activationKey = activationKey;
    this.academic_title = academic_title;
    this.birthDate = birthDate;
  }

  public User() {
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

  public void setUsername(String login) {
    this.username = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getActivationKey() {
    return activationKey;
  }

  public void setActivationKey(String activationKey) {
    this.activationKey = activationKey;
  }

  public String getAcademic_title() {
    return academic_title;
  }

  public void setAcademic_title(String academic_title) {
    this.academic_title = academic_title;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public List<College> getColleges() { return colleges; }

  public void setColleges(List<College> colleges) { this.colleges = colleges; }

  public List<Company> getCompanies() { return companies; }

  public void setCompanies(List<Company> companies) { this.companies = companies; }

  public void addCollege(College college) {
    this.colleges.add(college);
  }

  public Set<Faculty> getFaculties() {
    return faculties;
  }

  public void setFaculties(Set<Faculty> faculties) {
    this.faculties = faculties;
  }

  public void addFaculty(Faculty faculty) {
    this.faculties.add(faculty);
    faculty.getUsers().add(this);
  }

  public Set<Institute> getInstitutes() {
    return institutes;
  }

  public void setInstitutes(Set<Institute> institutes) {
    this.institutes = institutes;
  }

  public void addInstitute(Institute institute) {
    this.institutes.add(institute);
    institute.getUsers().add(this);
  }

  public Set<Department> getDepartments() {
    return departments;
  }

  public void setDepartments(
      Set<Department> departments) {
    this.departments = departments;
  }

  public void addDepartment(Department department) {
    this.departments.add(department);
    department.getUsers().add(this);
  }

  public Long getSelectedCollegeId() {
    return selectedCollegeId;
  }

  public void setSelectedCollegeId(Long selectedCollegeId) {
    this.selectedCollegeId = selectedCollegeId;
  }
}
