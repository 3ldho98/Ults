package Ults.common.command.domain;

import Ults.common.create.domain.OrderItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderRequest {

    private final List<OrderItem> iteams = new ArrayList<>();

    public String getProductCode() {
        return iteams.getProductCode();
    }

    public Integer getQuantity() {
        return iteams.getQuantity();
    }

    public BigDecimal getUnitPrice() {
        return iteams.getUnitPrice();
    }

    public List<OrderItem> getIteams() {
        return iteams;
    }
}
