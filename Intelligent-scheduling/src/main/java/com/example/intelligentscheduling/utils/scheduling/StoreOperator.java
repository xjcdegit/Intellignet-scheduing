package com.example.intelligentscheduling.utils.scheduling;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Store的HashMap初始化，增删查改
 */
public class StoreOperator {

    public static void allStoreInformation(HashMap<String,Store> newList) {//店铺表显示
        System.out.println("\n\n店铺表:");
        for(Store sto : newList.values()) {//强化for拿到Store
            sto.store_information();
            System.out.println();
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }


    //用店铺HashMap之前必须先初始化！！！
    public static HashMap<String,Store> store_arrInit() {
        HashMap<String, Store> retList = new HashMap<String, Store>();
        //System.out.println("店铺HashMap初始化成功！！");
        return retList;
    }

    public static  void  store_add(HashMap<String, Store> newList) {
        /////////////////////////////////////////////////////////////////////有问题
        Scanner sc = new Scanner(System.in);


        System.out.println("请依次输入店铺名称、地址、面积（以空格分割）:");
        String name = sc.next();
        String address = sc.next();
        double size = sc.nextDouble();
        System.out.print("请输入员工人数:");
        int input = sc.nextInt();

        HashMap<String, Stuff> tmpStuffMap = new HashMap<>();//临时Map
        for(int i=0;i<input;i++) {
            StuffOperator.stuff_add(tmpStuffMap);
        }
        Store sto = new Store(name,address,size,tmpStuffMap);
        ///////////////////////////////////////////////////////////////////////



        //开始规则设置,在Store中新添字段
        /////////////////////////////////////////////////////////////////////
        System.out.println();
        System.out.println("请依次输入门店工作规则：");
        System.out.print("①请输入开门前准备时间（默认值为0.5,建议不要超过2）:");
        double preWorkTime;
        preWorkTime = sc.nextDouble();
        sto.setPreWorkTime(preWorkTime);

        System.out.println();

        System.out.print("②请输入开门前需要人数公式值（默认值为50）:");
        int StoreSizeNeedBefore;
        StoreSizeNeedBefore = sc.nextInt();
        sto.setStoreSizeNeedBefore(StoreSizeNeedBefore);

        System.out.println();

        System.out.print("③请输入客流量人数公式值（默认值为3.8）:");
        double passengerFlowNeed;
        passengerFlowNeed = sc.nextDouble();
        sto.setPassengerFlowNeed(passengerFlowNeed);

        System.out.println();

        System.out.print("④请输入没有客流时需要人数（默认值为1）:");
        int free_population;
        free_population = sc.nextInt();
        sto.setFree_population(free_population);

        System.out.println();

        System.out.print("⑤请输入关门后工作时间（默认值为1）:");
        double afrWorkTime;
        afrWorkTime = sc.nextDouble();
        sto.setAftWorkTime(afrWorkTime);

        System.out.println();

        System.out.print("⑥请输入门后需要人数公式值1（默认值为50）:");
        int StoreSizeNeedAfter1;
        StoreSizeNeedAfter1 = sc.nextInt();
        sto.setStoreSizeNeedAfter1(StoreSizeNeedAfter1);

        System.out.println();

        System.out.print("⑦请输入门后需要人数公式值2（默认值为2）:");
        int StoreSizeNeedAfter2;
        StoreSizeNeedAfter2 = sc.nextInt();
        sto.setStoreSizeNeedAfter2(StoreSizeNeedAfter2);
        //////////////////////////////////////////////////////////////////


        newList.put(sto.getName(),sto);

        StoreOperator.allStoreInformation(newList);
    }

    //根据名字搜索信息
    public static void store_serchByName(HashMap<String, Store> newList) {
        System.out.print("请输入你想查找的店铺名称:");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();//名称
        Store sto  = newList.get(name);
        sto.store_information();
        System.out.println();
        System.out.println();
    }

    //通过名字删除
    public  static void stuff_delByName(HashMap<String, Store> newList) {
        System.out.print("请输入你想修改的店铺名称:");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        System.out.println("结果如下：");
        newList.get(name).store_information();
        System.out.println("确认删除？Y/N");
        String ch = sc.next();
        if (ch.equals("Y")) {
            System.out.println("删除成功!!");
            newList.remove(name);
            StoreOperator.allStoreInformation(newList);
        }
        else ;
    }

    //通过名字来修改员工信息
    public static void store_modifyByName(HashMap<String, Store> newList) {
        System.out.print("请输入你想删除的店铺名称:");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        System.out.println("结果如下：");
        newList.get(name).store_information();
        System.out.println("请输入你想修改的区域： 1.名称 2.地址 3.面积");//店员通过店铺修改，这里只管修改店铺
        int input = 0;
        while (input < 1 || input > 4) {
            input = sc.nextInt();
        }
        switch (input) {
            case 1://
                System.out.println("请输入修改后的店铺名称：");
                String modiName = sc.next();
                Store tmpStore = new Store();
                tmpStore = newList.get(name);//获取到value重新插入hashmap
                newList.put(modiName,tmpStore);
                newList.remove(name);
                System.out.println("修改成功！");
                newList.get(name).store_information();
                break;
            case 2:
                System.out.println("请输入修改后的地址：");
                String modiAddress = sc.next();
                newList.get(name).setAddress(modiAddress);
                System.out.println("修改成功！");
                newList.get(name).store_information();
                break;
            case 3:
                System.out.println("请输入修改后的面积：");
                double modiSize = sc.nextDouble();
                newList.get(name).setSize(modiSize);
                System.out.println("修改成功！");
                newList.get(name).store_information();
                break;
            default:
                System.out.println("非整数，匹配错误，退出修改模式");
                break;
        }
        StoreOperator.allStoreInformation(newList);
    }
}
