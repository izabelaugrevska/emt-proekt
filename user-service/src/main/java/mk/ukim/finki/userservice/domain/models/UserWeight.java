package mk.ukim.finki.userservice.domain.models;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "user_weights")
public class UserWeight extends AbstractEntity<UserWeightId> implements Comparable<UserWeight> {

    private int weight;
    private LocalDate dateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private UserWeight() {
        super(UserWeightId.randomId(UserWeightId.class));
        dateCreated = LocalDate.now();
    }

    public UserWeight(int weight, User user) {
        super(UserWeightId.randomId(UserWeightId.class));
        this.weight = weight;
        this.user = user;
    }

    @Override
    public int compareTo(UserWeight o) {
        return dateCreated.compareTo(o.dateCreated);
    }

}
