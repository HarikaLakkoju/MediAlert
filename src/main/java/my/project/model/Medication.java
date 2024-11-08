package my.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Medication {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@NonNull
    private String name;
@NonNull
    private int tabletCount;
@NonNull
    private String timeToTake;
@NonNull
private String specialInstruction;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    public Medication() {
    }

    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tabletCount=" + tabletCount +
                ", timeToTake='" + timeToTake + '\'' +
                ", speacialInstruction='" + specialInstruction + '\'' +
                ", user=" + user +
                '}';
    }
}
