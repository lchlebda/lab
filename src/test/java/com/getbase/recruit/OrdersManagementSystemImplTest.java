package com.getbase.recruit;

import com.getbase.recruit.orders.Order;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrdersManagementSystemImplTest {

    @Mock
    private TaxOfficeAdapter taxOfficeAdapter;

    @Mock
    private ItemsRepository itemsRepository;

    @InjectMocks
    private OrdersManagementSystemImpl ordersManagementSystem = new OrdersManagementSystemImpl(taxOfficeAdapter, itemsRepository);

    @Test
    public void priority_order_should_be_returned_first() {

        //given
        givenFetchItemPrices();

        //when
        whenCreateOrders(new OrderType[]{OrderType.STANDARD},
                         new OrderType[]{OrderType.INTERNATIONAL},
                         new OrderType[]{OrderType.PRIORITY},
                         new OrderType[]{OrderType.DISCOUNTED});
        Order firstOrder = ordersManagementSystem.fetchNextOrder();

        //then
        assertThat(firstOrder).isNotNull();
        assertThat(firstOrder.getItemId()).isEqualTo(3);

    }

    @Test
    public void orders_should_be_queued_fifo() {

        //given
        givenFetchItemPrices();

        //when
        whenCreateOrders(new OrderType[]{OrderType.STANDARD},
                new OrderType[]{OrderType.DISCOUNTED},
                new OrderType[]{OrderType.INTERNATIONAL},
                new OrderType[]{OrderType.STANDARD});

        Order first = ordersManagementSystem.fetchNextOrder();
        Order second = ordersManagementSystem.fetchNextOrder();
        Order third = ordersManagementSystem.fetchNextOrder();
        Order fourth = ordersManagementSystem.fetchNextOrder();

        //then
        assertThat(first.getItemId()).isEqualTo(1);
        assertThat(second.getItemId()).isEqualTo(2);
        assertThat(third.getItemId()).isEqualTo(3);
        assertThat(fourth.getItemId()).isEqualTo(4);

    }

    @Test
    // this should be review with client - it looks reasonable to queued priority orders as FIFO
    public void priority_orders_should_be_queued_fifo_between_them() {

        //given
        givenFetchItemPrices();

        //when
        whenCreateOrders(new OrderType[]{OrderType.STANDARD},
                new OrderType[]{OrderType.PRIORITY},
                new OrderType[]{OrderType.PRIORITY},
                new OrderType[]{OrderType.PRIORITY});

        Order first = ordersManagementSystem.fetchNextOrder();
        Order second = ordersManagementSystem.fetchNextOrder();
        Order third = ordersManagementSystem.fetchNextOrder();

        //then
        assertThat(first.getItemId()).isEqualTo(2);
        assertThat(second.getItemId()).isEqualTo(3);
        assertThat(third.getItemId()).isEqualTo(4);

    }

    @Test
    // this should be review with client - it looks reasonable to queued priority orders as FIFO
    public void mixed_priority_orders_should_be_queued_fifo_between_them() {

        //given
        givenFetchItemPrices();

        //when
        whenCreateOrders(new OrderType[]{OrderType.STANDARD},
                new OrderType[]{OrderType.DISCOUNTED, OrderType.PRIORITY},
                new OrderType[]{OrderType.INTERNATIONAL, OrderType.PRIORITY},
                new OrderType[]{OrderType.PRIORITY, OrderType.INTERNATIONAL});

        Order first = ordersManagementSystem.fetchNextOrder();
        Order second = ordersManagementSystem.fetchNextOrder();
        Order third = ordersManagementSystem.fetchNextOrder();

        //then
        assertThat(first.getItemId()).isEqualTo(2);
        assertThat(second.getItemId()).isEqualTo(3);
        assertThat(third.getItemId()).isEqualTo(4);

    }

    @Test
    public void tax_for_priority_order_should_be_correct() {

        //given
        given(itemsRepository.fetchItemPrice(1)).willReturn(new BigDecimal("3.33"));

        //when
        ordersManagementSystem.createOrder(1,1, OrderType.PRIORITY);
        Order nextOrder = ordersManagementSystem.fetchNextOrder();

        //then
        assertThat(nextOrder).isNotNull();

        // 3.33 + 1.5% = 3.38   3.38 * 23.5% = 0.795
        verify(taxOfficeAdapter).registerTax(new BigDecimal("0.795"));
    }

    @Test
    public void price_for_discounted_order_should_be_correct() {

        //given
        given(itemsRepository.fetchItemPrice(1)).willReturn(new BigDecimal("3.33"));

        //when
        ordersManagementSystem.createOrder(1, 1, OrderType.DISCOUNTED);
        Order nextOrder = ordersManagementSystem.fetchNextOrder();

        //then
        assertThat(nextOrder).isNotNull();

        // 3.33 - 11% = 2.97
        assertThat(nextOrder.getPrice()).isEqualTo(new BigDecimal("2.97"));
    }

    @Test
    public void price_for_priority_order_should_be_correct() {

        //given
        given(itemsRepository.fetchItemPrice(1)).willReturn(new BigDecimal("3.33"));

        //when
        ordersManagementSystem.createOrder(1, 1, OrderType.PRIORITY);
        Order nextOrder = ordersManagementSystem.fetchNextOrder();

        //then
        assertThat(nextOrder).isNotNull();

        // 3.33 + 1.5% = 3.38
        assertThat(nextOrder.getPrice()).isEqualTo(new BigDecimal("3.38"));
    }

    @Test
    public void price_for_priority_discounted_order_should_be_correct() {

        //given
        given(itemsRepository.fetchItemPrice(1)).willReturn(new BigDecimal("3.33"));

        //when
        ordersManagementSystem.createOrder(1, 1, OrderType.PRIORITY, OrderType.DISCOUNTED);
        Order nextOrder = ordersManagementSystem.fetchNextOrder();

        //then
        assertThat(nextOrder).isNotNull();

        // 3.33 - 11% + 1.5% = 3.02
        assertThat(nextOrder.getPrice()).isEqualTo(new BigDecimal("3.02"));
    }

    @Test
    public void tax_for_international_order_should_be_correct() {

        //given
        given(itemsRepository.fetchItemPrice(1)).willReturn(new BigDecimal("3.33"));

        //when
        ordersManagementSystem.createOrder(1, 1, OrderType.INTERNATIONAL);
        Order nextOrder = ordersManagementSystem.fetchNextOrder();

        //then
        assertThat(nextOrder).isNotNull();

        // 3.33 * 15% = 0.50
        verify(taxOfficeAdapter).registerTax(new BigDecimal("0.500"));
    }

    private void whenCreateOrders(OrderType[]... orderTypes ) {
        for (int i=1; i<=orderTypes.length; i++) {
            ordersManagementSystem.createOrder(i, 1, orderTypes[i-1]);
        }
    }

    private void givenFetchItemPrices() {
        given(itemsRepository.fetchItemPrice(1)).willReturn(new BigDecimal("5.00"));
        given(itemsRepository.fetchItemPrice(2)).willReturn(new BigDecimal("10.00"));
        given(itemsRepository.fetchItemPrice(3)).willReturn(new BigDecimal("10.00"));
        given(itemsRepository.fetchItemPrice(4)).willReturn(new BigDecimal("10.00"));
    }

}