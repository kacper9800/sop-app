package pl.sop.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "internships")
public class Internship extends BasicEntity {

  @OneToOne
  @JoinColumn(name = "request_id")
  private Request request;

  public Internship() {
  }

  public Internship(Request request) {
    this.request = request;
  }

  public Request getRequest() {
    return request;
  }

  public void setRequest(Request request) {
    this.request = request;
  }
}
