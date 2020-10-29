/*
 * Property of Sheridan College (see Copyright Ownership Policy, ref #24)
 */

package burrell.michael.data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Michael Burrell
 * @since 23-Nov-2015
 */
public class ShoppingItem implements Serializable {
    private String description;
    private BigDecimal unitPrice;
    private int quantity;

    public ShoppingItem(String description, BigDecimal unitPrice, int quantity) {
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
