package pl.erp.erpBackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long idItem;

    @Column
    private String name;

    @Column
    private Double quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idQuantityType")
    private QuantityType quantityType;

    @ManyToMany
    @JoinTable(
            name = "item_warehouse",
            joinColumns = @JoinColumn(name = "idItem"),
            inverseJoinColumns = @JoinColumn(name = "ideWarehouse")
    )
    private Set<Warehouse> warehouseSet;
}
