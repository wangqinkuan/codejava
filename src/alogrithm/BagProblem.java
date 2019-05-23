package alogrithm;

import ds.Bag;
import ds.BagItem;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 分别利用贪心算法解决背包问题，背包容
 * 量和物品效益、重量由输入决定，结果得
 * 到最优分配方案，即满足容量约束的前提
 * 下总效益最大。
 * 按三种不同的量度标准分别计算所得最大总效
 * 益（第一指标相同，第二指标较好的优先）。
 *  按效益值由大到小取物品
 *  按重量值由小到大取物品
 *  按效益/重量的值由大到小取物品
 * 示例：
 * 背包容量：150
 * 物品：A B C D E F G
 * 效益：10 40 30 50 35 40 30
 * 重量：35 30 60 50 40 10 25
 * 按效益值：165
 * 按重量：155
 * 按比值：160
 */
public class BagProblem {

    /**
     * 根据效益取物
     * @param bag
     * @param bagItems
     */
    public static void byBenefit(Bag bag, ArrayList<BagItem> bagItems){
        bagItems.sort(new Comparator<BagItem>() {
            @Override
            public int compare(BagItem o1, BagItem o2) {
                if(o2.benefit == o1.benefit){
                    return o1.weight - o2.weight;
                }
                return o2.benefit - o1.benefit;
            }
        });
        for(BagItem bagItem : bagItems){
            bag.add(bagItem);
        }
    }

    /**
     * 按重量取物
     * @param bag
     * @param bagItems
     */
    public static void byWeight(Bag bag,ArrayList<BagItem> bagItems){
        bagItems.sort(new Comparator<BagItem>() {
            @Override
            public int compare(BagItem o1, BagItem o2) {
                if(o1.weight == o2.weight){
                    return o2.benefit - o1.benefit;
                }
                return o1.weight - o2.weight;
            }
        });
        for(BagItem bagItem : bagItems){
            if(!bag.add(bagItem)) return;
        }
    }

    /**
     * 按照效益
     * @param bag
     * @param bagItems
     */
    public static void byRatio(Bag bag,ArrayList<BagItem> bagItems){
        bagItems.sort(new Comparator<BagItem>() {
            @Override
            public int compare(BagItem o1, BagItem o2) {
                if(((float)o2.benefit/(float)o2.weight) - ((float)o1.benefit/(float)o1.weight) == 0) return 0;
                return (((float)o2.benefit/(float)o2.weight) - ((float)o1.benefit/(float)o1.weight)) > 0 ? 1 : -1;
            }
        });
        for(BagItem bagItem : bagItems){
            bag.add(bagItem);
        }
    }
}
