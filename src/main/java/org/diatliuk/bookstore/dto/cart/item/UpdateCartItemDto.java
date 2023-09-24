package org.diatliuk.bookstore.dto.cart.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCartItemDto {
    @NotNull
    @Min(value = 1)
    private Integer quantity;
}