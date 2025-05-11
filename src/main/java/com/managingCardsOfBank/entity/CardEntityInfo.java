package com.managingCardsOfBank.entity;

import com.managingCardsOfBank.model.CardStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "card_info")
public class CardEntityInfo {
    @Id
    @SequenceGenerator(name = "card_infoSequence", sequenceName = "card_infosequence", allocationSize = 1, initialValue = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_infoSequence")
    @Column(name = "id", nullable = false)
    private Long id;

    private String cardNumber;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    private LocalDate expiredDate;

    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntityInfo user;

}
