package com.vladgoncharov.eshop.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "product")
public class Product {
    private static final String SEQ_NAME = "product_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME,sequenceName = SEQ_NAME,allocationSize = 1)
    private Long id;
    private String title;
    private Long price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
