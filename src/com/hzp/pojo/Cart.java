package com.hzp.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author ASUS
 * @projectName book
 * @description: 购物车对象
 * @date 2022-02-01 21:04
 */
public class Cart {
    /**
     * key是商品编号
     * value 是商品信息
     */
    private Map<Integer,CartItem> items=new LinkedHashMap<>();

    public Cart(){

    }


    public void addItem(CartItem cartItem){
        // 先查看购物车中是否已经添加过此商品，如果已添加，则数量累加，总金额更新，如果没有添加过，直接放到
        //集合中即可
        CartItem item = items.get(cartItem.getId());
        if(item==null){
            //之前没添加过此商品
            items.put(cartItem.getId(),cartItem);
        }else{
            //已经添加过的情况
            item.setCount(item.getCount()+1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public void deleteItem(Integer id){
        items.remove(id);
    }

    public void clear(){
        items.clear();
    }

    public void updateCount(Integer id,Integer count){
        CartItem cartItem = items.get(id);
        if(cartItem!=null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }




    public Integer getTotalCount() {
        Integer totalCount=0;
        for(Map.Entry<Integer,CartItem>entry: items.entrySet()){
            totalCount+= entry.getValue().getCount();
        }
        return totalCount;
    }



    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CartItem>entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}
