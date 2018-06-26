package j.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by j on 2018/4/3.
 */
public class TargetFind {
    List<Drink> drinkList;
    int error;
    int maxWeight;
    boolean[][] dp;

    public TargetFind() {
        initiateDrinkList();
        error = 1;
        maxWeight = 1000;
        initiateCombination(maxWeight);
    }

    /**
     * 初始化饮料的种类
     */
    public void initiateDrinkList() {
        drinkList = new ArrayList<>();
        drinkList.add(new Drink(0, "fenda", 20));
        drinkList.add(new Drink(1, "yinyangkuaixian", 30));
        drinkList.add(new Drink(2, "jiaduobao", 32));
        drinkList.add(new Drink(3, "maidong", 40));
    }

    public void initiateCombination(int maxWeight) {
        dp = new boolean[drinkList.size() + 1][maxWeight + 1];
        for (int j = 0; j <= error; j++) {
            dp[0][j] = true;
        }
        for (int i = 1; i < dp.length; i++) {
            int drinkWeight = drinkList.get(i - 1).weight;
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = (dp[i - 1][j]) || (j >= drinkWeight && dp[i][j - drinkWeight]);
            }
        }
    }

    /**
     * 主功能函数
     * @param changedWeight 重力传感器得到的重力变化值
     * @return 所有可能的组合
     */
    public List<List<Drink>> getAllCombinations(int changedWeight) {
        if (changedWeight + error > maxWeight) {
            maxWeight = changedWeight + error;
            initiateCombination(maxWeight);
        }

        List<List<Drink>> feasibleCombinations = new ArrayList<>();
        List<Drink> combination = new ArrayList<>();

         dfs(dp.length - 1, changedWeight, combination, feasibleCombinations);
        dfs(dp.length - 1, changedWeight + error, combination, feasibleCombinations);

        return feasibleCombinations;
    }

    private void dfs(int size, int weight, List<Drink> combination, List<List<Drink>> feasibleCombinations) {
        if (weight < 0 || !dp[size][weight]) return;

        if (size == 0) {
            feasibleCombinations.add(new ArrayList<>(combination));
            return;
        }

        int drinkWeight = drinkList.get(size - 1).weight;

        // 选择当前饮料
        combination.add(drinkList.get(size - 1));
        dfs(size, weight - drinkWeight, combination, feasibleCombinations);
        combination.remove(combination.size() - 1);

        // 不选择当前饮料
        dfs(size - 1, weight, combination, feasibleCombinations);
    }

    public static void main(String[] args) {
        TargetFind tf = new TargetFind();
        System.out.println(tf.getAllCombinations(32));
    }
}

class Drink {
    int id;
    String name;
    int weight;

    public Drink(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}

