package com.getbase.recruit;

import com.getbase.recruit.orders.*;

import java.math.BigDecimal;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class OrdersManagementSystemImpl implements OrdersManagementSystem {

    private final TaxOfficeAdapter taxOfficeAdapter;
    private final ItemsRepository itemsRepository;

    private NavigableSet<FIFOEntry> ordersQueue=new ConcurrentSkipListSet<FIFOEntry>();

    public OrdersManagementSystemImpl(TaxOfficeAdapter taxOfficeAdapter, ItemsRepository itemsRepository) {
        this.taxOfficeAdapter = taxOfficeAdapter;
        this.itemsRepository = itemsRepository;
    }

    @Override
    public void createOrder(int itemId, int customerId, OrderType... flags) {
        BigDecimal itemPrice = itemsRepository.fetchItemPrice(itemId);
        Order newOrder = createOrder(itemId, customerId, itemPrice, flags);
        ordersQueue.add(new FIFOEntry<>(newOrder));
        taxOfficeAdapter.registerTax(newOrder.getTax());
    }

    @Override
    public Order fetchNextOrder() {
        return (Order) ordersQueue.pollLast().getEntry();
    }

    private Order createOrder(int itemId, int customerId, BigDecimal itemPrice, OrderType... orderTypes) {

        Order newOrder = new CommonOrder(itemId,customerId,itemPrice);
        for (OrderType orderType : orderTypes) {
            switch (orderType) {
                case PRIORITY:
                    newOrder = new PriorityOrder(newOrder);
                    break;
                case INTERNATIONAL:
                    newOrder = new InternationalOrder(newOrder);
                    break;
                case DISCOUNTED:
                    newOrder = new DiscountedOrder(newOrder);
                    break;
            }
        }

        return newOrder;
    }
}
