package ds;

import java.util.ArrayList;

/**
 * 背包
 */
public class Bag {

    public ArrayList<BagItem> bagItems;

    public int capacity;

    public int leftCapacity;

    public int benefit;

    /**
     *
     * @param capacity 背包容量
     */
    public Bag(int capacity){
        bagItems = new ArrayList<>();
        this.capacity = capacity;
        this.leftCapacity = capacity;
        this.benefit = 0;
    }

    public boolean add(BagItem bagItem){
        if(bagItem.weight <= leftCapacity){
            bagItems.add(bagItem);
            leftCapacity -= bagItem.weight;
            benefit += bagItem.benefit;
            System.out.println("物品:"+ bagItem.item+"--效益:"+bagItem.benefit+"--重量:"+bagItem.weight+"--放入背包--背包剩余容量:"+this.leftCapacity+"--总效益:"+this.benefit);
            return true;
        }
        return false;
    }

    public void clearBag(){
        bagItems.clear();
        benefit = 0;
        leftCapacity = capacity;
    }

}
