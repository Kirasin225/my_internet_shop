package com.kirasin.service.impl;

import com.kirasin.dto.order.OrderCreateEditDto;
import com.kirasin.dto.order.OrderReadDto;
import com.kirasin.mapper.order.OrderCreateEditMapper;
import com.kirasin.mapper.order.OrderReadMapper;
import com.kirasin.model.Order;
import com.kirasin.repository.OrderRepository;
import com.kirasin.repository.ProductRepository;
import com.kirasin.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final OrderReadMapper orderReadMapper;
    private final OrderCreateEditMapper orderCreateEditMapper;
    private final ProductRepository productRepository;

    @Override
    public OrderReadDto createOrder(OrderCreateEditDto order) {
        return Optional.of(order)
                .map(orderCreateEditMapper::map)
                .map(repository::save)
                .map(orderReadMapper::map)
                .orElseThrow();
    }

    @Override
    public Optional<OrderReadDto> findById(Long id) {
        return repository.findById(id)
                .map(orderReadMapper::map);
    }

    @Override
    public OrderReadDto findTopByOrderByOrderPlacementDateDesc() {
        return orderReadMapper.map(repository.findTopByOrderByOrderIdDesc());
    }

    private void delete(Long orderId){
        repository.findById(orderId)
                .map(entity -> {
                    repository.delete(entity);
                    repository.flush();
                    return null;
                }
        );
    }


    @Transactional
    @Override
    public boolean deleteByOrderDateBefore(LocalDate date) {
        List<Order> ordersToDelete = repository.findByOrderDateBefore(date);
        ordersToDelete.forEach(order -> delete(order.getOrderId()));
        return !ordersToDelete.isEmpty();
    }




    @Override
    public List<OrderReadDto> findOrdersByProductId(Long productId) {
        return mapToDto(repository.findOrdersByProductId(productId));
    }

    @Override
    public List<OrderReadDto> findOrdersMadeByCustomer(Long customerId) {
        return mapToDto(repository.findOrdersMadeByCustomer(customerId));
    }

    private List<OrderReadDto> mapToDto(List<Order> orders){
        return   orders.stream()
                .map(orderReadMapper::map)
                .toList();
    }
}

