package vp.seminarska.airplanemanagmentapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Getter
@Table(name="user_roles")
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @Id
    private String name;
    public UserRole(String name)
    {
        this.name = name;
    }

    @ManyToMany(mappedBy = "userRole")
    private List<User> users;
}
