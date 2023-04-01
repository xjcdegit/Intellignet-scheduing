package com.example.intelligentscheduling.utils.scheduling;

import com.example.intelligentscheduling.entity.Shop;
import com.example.intelligentscheduling.utils.scheduling.yichuan.Chromo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class main {
    public static List<List<String>> res = new ArrayList<>();
    public static List<List<String>> scheduling(List<Stuff> StuffList, Shop shop){

        /**
         * GA算法测试
         */
        System.out.println("智能排班系统欢迎你！");
        Scanner sc = new Scanner(System.in);
        //stuff_arr员工集合

        /**
         * 偏好输入规则：
         *
         * 工作日偏好 1、3、4、5、7
         * 工作时间偏好 9:30-21:30 9:00-21:00
         * 日工作时间偏好 6小时
         * 无偏好，正常工作
         */
        HashMap<String, Stuff> stuff_arr = StuffOperator.stuff_arrInit();
        /*for (Stuff stuff:StuffList){
            stuff_arr.put(stuff.getName(),stuff);
        }*/
        //Stuff a = new Stuff("张三", "店长", "173028", "qq.com", "1", "工作日偏好 1、3、4、5、6");
        //Stuff a = new Stuff("张三", "店长", "173028", "qq.com", "1", "工作时间偏好 12:00-18:00");
        /*Stuff a = new Stuff("张三", "店长", "173028", "qq.com", "1", "日工作时间偏好 7小时");
        Stuff b = new Stuff("李四", "经理", "173029", "weixin.com", "1", "工作日偏好 1、3、4、5、6");
        Stuff c = new Stuff("王五", "经理", "173029", "weixin.com", "1", "无偏好，正常工作");
        Stuff d = new Stuff("老六", "经理", "173029", "weixin.com", "1", "无偏好，正常工作");
        Stuff e = new Stuff("田七", "经理", "173029", "weixin.com", "1", "无偏好，正常工作");
        Stuff f = new Stuff("丈八", "经理", "173029", "weixin.com", "1", "工作日偏好 1、3、4、5、6");
        Stuff g = new Stuff("小松", "经理", "173029", "weixin.com", "1", "工作日偏好 1、2、5、6");
        Stuff h = new Stuff("小美", "经理", "173029", "weixin.com", "1", "无偏好，正常工作");
        Stuff i = new Stuff("金发妹", "经理", "173029", "weixin.com", "1", "无偏好，正常工作");
        Stuff j = new Stuff("哈哈", "经理", "173029", "weixin.com", "1", "无偏好，正常工作");
        Stuff k = new Stuff("12", "经理", "173029", "weixin.com", "1", "无偏好，正常工作");
        Stuff l = new Stuff("23", "经理", "173029", "weixin.com", "1", "无偏好，正常工作");
        Stuff m = new Stuff("34", "经理", "173029", "weixin.com", "1", "工作时间偏好 10:30-21:00");
        Stuff n = new Stuff("45", "经理", "173029", "weixin.com", "1", "工作时间偏好 12:00-19:30");
        Stuff o = new Stuff("56", "经理", "173029", "weixin.com", "1", "无偏好，正常工作");*/


        /*stuff_arr.put(a.getName(),a);
        stuff_arr.put(b.getName(),b);
        stuff_arr.put(c.getName(),c);
        stuff_arr.put(d.getName(),d);
        stuff_arr.put(e.getName(),e);
        stuff_arr.put(f.getName(),f);
        stuff_arr.put(g.getName(),g);
        stuff_arr.put(h.getName(),h);
        stuff_arr.put(i.getName(),i);
        stuff_arr.put(j.getName(),j);
        stuff_arr.put(k.getName(),k);
        stuff_arr.put(l.getName(),l);
        stuff_arr.put(m.getName(),m);
        stuff_arr.put(n.getName(),n);
        stuff_arr.put(o.getName(),o);*/
        for(Stuff stuff:StuffList){
            stuff_arr.put(stuff.getName(), stuff);
        }
        //店集合
//        Store store1 = new Store("1","张家界",100.0,stuff_arr);
        Store store1 = new Store(shop.getName(),shop.getAddress(),shop.getSize(),stuff_arr);
        //StuffOperator.allStuffInformation(stuff_arr);//显示员工集

        HashMap<String, Store> store_arr = StoreOperator.store_arrInit();
        store_arr.put(store1.getName(),store1);
        //StoreOperator.allStoreInformation(store_arr);//显示店集

        //已存在店、员工
        //员工染色体已自动初始化

        /**
         * 需要排班的天数，这里只是为了获取是星期几
         */
        int DAY1=7;//
        for(int day=1;day<=DAY1;day++)
        {
            List<Chromo> chromos = store1.use_GA(day);//哪天
            List<String> list = new ArrayList<>();
            for (Chromo chromo:chromos){
                list.add(String.valueOf(chromo.chromo));
            }
            res.add(new ArrayList<>(list));
        }
        return res;

        //用的是一天来排的班

        /*StoreOperator.store_add(store_arr);//add出问题
        int DAY2=30;
        for(int day=1;day<=DAY2;day++)
        {
            store_arr.get("1").use_GA(day);
        }*/











        /**
         * 以下为增添改查代码测试
         */
        //HashMap<String, Stuff> stuff_arr = StuffOperator.stuff_arrInit();



        /*Stuff a = new Stuff("张三", "店长", "173028", "qq.com", 1, 1);
        Stuff b = new Stuff("李四", "经理", "173029", "weixin.com", 1, 2);
        stuff_arr.put(a.getName(),a);
        stuff_arr.put(b.getName(),b);
        Store store1 = new Store("Z店","张家界",13.14,stuff_arr);
        StuffOperator.allStuffInformation(stuff_arr);
        */



        /**
         * Stuff功能测试
         */
        //StuffOperator.stuff_add(stuff_arr);//静态方法
        //StuffOperator.stuff_serchByName(stuff_arr);
        //StuffOperator.stuff_modifyByName(stuff_arr);
        //StuffOperator.stuff_delByName(stuff_arr);




        /**
         * Store功能测试
         */
        /*HashMap<String,Store> store_arr = StoreOperator.store_arrInit();
        store_arr.put(store1.getName(),store1);
        StoreOperator.allStoreInformation(store_arr);

        Store store2 = new Store("Y店","陈家界",5.20,stuff_arr);
        store_arr.put(store2.getName(),store2);
        StoreOperator.allStoreInformation(store_arr);

        Store store3 = new Store("X店","朱家界",1314.520,stuff_arr);
        store_arr.put(store3.getName(),store3);
        StoreOperator.allStoreInformation(store_arr);


        StoreOperator.store_add(store_arr);
        StoreOperator.store_serchByName(store_arr);
        StoreOperator.store_modifyByName(store_arr);
        StoreOperator.stuff_delByName(store_arr);
        */


    }
}




