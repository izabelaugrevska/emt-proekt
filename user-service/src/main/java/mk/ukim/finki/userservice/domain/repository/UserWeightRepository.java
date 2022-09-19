package mk.ukim.finki.userservice.domain.repository;

import mk.ukim.finki.userservice.domain.models.User;
import mk.ukim.finki.userservice.domain.models.UserWeight;
import mk.ukim.finki.userservice.domain.models.UserWeightId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface UserWeightRepository extends JpaRepository<UserWeight, UserWeightId> {

    @Query(value = "select uw from UserWeight uw where uw.user = ?1 and uw.dateCreated >= ?2")
    List<UserWeight> findAllByUserAndDate(User user, LocalDate date);

}
