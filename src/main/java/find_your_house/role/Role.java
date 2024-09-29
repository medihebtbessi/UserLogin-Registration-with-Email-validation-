package find_your_house.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import find_your_house.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AutoCloseable.class)
public class Role {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;
    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDate createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDate lastModifiedDate;
}
