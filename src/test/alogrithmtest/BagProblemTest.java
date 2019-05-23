package test.alogrithmtest;

import alogrithm.BagProblem;
import ds.Bag;
import ds.BagItem;
import org.junit.Test;
import util.ArrayUtil;

import java.util.ArrayList;

public class BagProblemTest {
    @Test
    public void insertTest(){
        char[] items = {'A','B','C','D','E','F','G'};
        int[] benefits = {10,40,30,50,35,40,30};
        int[] weights = {35,30,60,50,40,10,25};

        Bag bag = new Bag(150);
        ArrayList<BagItem> bagItems = BagItem.createBagItem(items,weights,benefits);

        System.out.println("-------效益优先-------");
        BagProblem.byBenefit(bag,bagItems);
        bag.clearBag();

        System.out.println("-------重量优先-------");
        BagProblem.byWeight(bag,bagItems);
        bag.clearBag();

        System.out.println("-------比例优先-------");
        BagProblem.byRatio(bag,bagItems);

    }
}
