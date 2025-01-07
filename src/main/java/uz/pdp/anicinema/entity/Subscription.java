package uz.pdp.anicinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.anicinema.utils.enums.SubscriptionStatus;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Plan plan;
    private Long startDate;
    private Long endDate;
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;
}
