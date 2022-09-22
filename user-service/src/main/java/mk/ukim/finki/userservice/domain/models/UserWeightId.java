package mk.ukim.finki.userservice.domain.models;

import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

public class UserWeightId extends DomainObjectId {

    public UserWeightId() {
        super(UserWeightId.randomId(UserWeightId.class).getId());
    }

    public UserWeightId(String uuid) {
        super(uuid);
    }
}
