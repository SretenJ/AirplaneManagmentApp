package vp.seminarska.airplanemanagmentapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Getter
@Table(name="user_role")
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @Id
    private String name;
}
