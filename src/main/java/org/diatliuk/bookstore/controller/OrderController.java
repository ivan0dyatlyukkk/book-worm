package org.diatliuk.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.diatliuk.bookstore.dto.order.CreateOrderRequestDto;
import org.diatliuk.bookstore.dto.order.OrderDto;
import org.diatliuk.bookstore.dto.order.UpdateOrderStatusRequestDto;
import org.diatliuk.bookstore.dto.order.item.OrderItemDto;
import org.diatliuk.bookstore.service.OrderItemService;
import org.diatliuk.bookstore.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order management", description = "Endpoints for managing orders")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get all orders", description = "Allow to get list of all orders")
    public List<OrderDto> getAll(Pageable pageable) {
        return orderService.getAll(pageable);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Place an order", description = "Allow to place an order")
    public OrderDto save(Authentication authentication,
                         @RequestBody @Valid CreateOrderRequestDto requestDto) {
        return orderService.save(authentication, requestDto);
    }

    @GetMapping("/{id}/items")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get order items by order id", description = "Allow to get all order "
                                                    + "items in a certain order by an order id")
    public List<OrderItemDto> getOrderItemsByOrderId(@PathVariable Long id) {
        return orderItemService.getItemsByOrderId(id);
    }

    @GetMapping("/{orderId}/items/{itemId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get an order item by an order id and an item id",
            description = "Allow to get a certain order item by specifying "
                            + "an order id and an item id")
    public OrderItemDto getItemInOrderById(@PathVariable Long orderId, @PathVariable Long itemId) {
        return orderItemService.getItemInOrderById(orderId, itemId);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Update a status of an order", description = "Allow to update a status "
                                                        + "by an order id")
    public OrderDto update(@PathVariable Long id,
                           @RequestBody @Valid UpdateOrderStatusRequestDto requestDto) {
        return orderService.update(id, requestDto);
    }
}
