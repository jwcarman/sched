package sched;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of= "id")
public class Customer {
    @Id
    private String id = UUID.randomUUID().toString();

    @NotEmpty
    @Setter
    private String name;
}
