package pl.erp.erpBackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class QuantityType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idQuantityType;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quantityType")
    private List<Item> itemList;
}
