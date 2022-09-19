package mk.ukim.finki.userservice.domain.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Embeddable
@AllArgsConstructor
public class BodyMeasurement implements ValueObject {

    @NotNull
    @Enumerated(EnumType.STRING)
    private final Sex sex;

    @NotNull
    @Min(100)
    private final int height;

    @NotNull
    @Min(40)
    private final int startWeight;

}
