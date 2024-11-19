package find_your_house.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import find_your_house.entity.Roles;
import find_your_house.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.Arrays;
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
   // @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private Roles roleType;
//    @OneToMany(mappedBy = "roles")
//    @JsonIgnore
//    private List<User> users;
//    @CreatedDate
//    @Column(nullable = false,updatable = false)
//    private LocalDate createdDate;
//    @LastModifiedDate
//    @Column(insertable = false)
//    private LocalDate lastModifiedDate;
//    @JsonCreator
//    public static Role fromValue(String value) {
//        return Arrays.stream(Role.values())
//                .filter(role -> role.toString().equals(value))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Invalid role: " + value));
//    }

}
