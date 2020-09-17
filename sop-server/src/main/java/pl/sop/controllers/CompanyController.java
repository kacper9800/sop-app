package pl.sop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sop.security.services.UserDetailsImpl;
import pl.sop.services.CompanyService;

@Controller
@RequestMapping(value = "api/companies")
public class CompanyController {

  @Autowired
  private CompanyService companyService;

  @GetMapping(value = "")
  public ResponseEntity getAllCompanies(Authentication authentication) {
    UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
    return ResponseEntity.ok(this.companyService.getAllCompanies(user.getSelectedCollegeId()));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity getCompanyById(Authentication authentication, @PathVariable("id") Long id) {
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    return ResponseEntity.ok(this.companyService.getCompanyById(id));
  }
}
