package ds;

import java.util.ArrayList;

/**
 * 放入背包的物品
 */
public class BagItem {

    public char item;

    public int weight;

    public int benefit;

    public BagItem(char item,int weight,int benefit){
        this.item = item;
        this.weight = weight;
        this.benefit = benefit;
    }

    public static ArrayList<BagItem> createBagItem(char[] items,int[] weights,int[] benefits){
        ArrayList<BagItem> bagItems = new ArrayList();
        for(int i = 0;i < items.length;i++){
            bagItems.add(new BagItem(items[i],weights[i],benefits[i]));
        }
        return bagItems;
    }
}
