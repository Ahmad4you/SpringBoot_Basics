package home.ahmad.boot_joinfaces.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    /*
     * fetch = FetchType.EAGER: Dies bedeutet, dass die Bestellungen immer zusammen mit dem Kunden geladen werden, 
     * auch wenn die Sitzung geschlossen ist
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> orders;


    public Customer() {
		// TODO Auto-generated constructor stub
	}
    
    
	public Customer(Long id, String name, List<Order> orders) {
		super();
		this.id = id;
		this.name = name;
		this.orders = orders;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
    
	
    
}

