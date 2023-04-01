package com.example.intelligentscheduling.utils.scheduling;

import java.util.HashMap;
import java.util.Scanner;


/**
 * Stuff的HashMap初始化，增删查改
 */
public class StuffOperator {

    public static void allStuffInformation(HashMap<String, Stuff> newList){//员工表显示
        System.out.println("\n\n员工表:");
        for(Stuff stu : newList.values()) {
            stu.stuff_information();
        }
        System.out.println();
    }



    //用员工HashMap之前必须先初始化！！！
    public static HashMap<String, Stuff> stuff_arrInit() {
        HashMap<String, Stuff> retList = new HashMap<>();
        System.out.println("员工HashMap初始化成功！");
        return retList;
    }

    public static  void  stuff_add(HashMap<String, Stuff> newList) {
        Scanner sc = new Scanner(System.in);

        System.out.println("偏好输入格式如下：(注意空格)");
        System.out.println("    工作日偏好 1、3、4、5、7\n" +
                "    工作时间偏好 10:00-21:00(偏好的范围)\n" +
                "    日工作时间偏好 6小时(范围为0-8)\n" +
                "    无偏好，正常工作");

        System.out.println("请依次输入员工姓名、职位、电话号码、电子邮箱、所在门店、偏好（以空格分割）");
        String name = sc.next();
        String pos = sc.next();
        String tel = sc.next();
        String mail = sc.next();
        String stores = sc.next();
        String loves = sc.nextLine();
        loves=loves.substring(1);
        Stuff stu= new Stuff(name,pos,tel,mail,stores,loves);
        newList.put(name,stu);
        System.out.println("添加员工成功！!");

        StuffOperator.allStuffInformation(newList);
    }

    //根据名字搜索信息
    public static void stuff_serchByName(HashMap<String, Stuff> newList) {
        System.out.print("请输入你想查找的员工姓名:");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        System.out.println();
        System.out.println("查找成功，查找结果为：");
        newList.get(name).stuff_information();
        System.out.println();
    }

    //通过名字删除
    public  static void stuff_delByName(HashMap<String, Stuff> newList) {
        System.out.print("请输入你想删除的员工姓名:");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        System.out.println("结果如下：");
        newList.get(name).stuff_information();
        System.out.println();

        System.out.println("确认删除？Y/N");
        String ch = sc.next();
        if (ch.equals("Y")) {
            newList.remove(name);
            System.out.println("删除成功！！");

            StuffOperator.allStuffInformation(newList);
        }
        else ;

    }

    //通过名字来修改员工信息
    public static void stuff_modifyByName(HashMap<String, Stuff> newList) {
        System.out.print("请输入你想修改的员工姓名:");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        System.out.println("结果如下：");
        newList.get(name).stuff_information();
        System.out.println("请输入你想修改的区域： 1.姓名 2.职位 3.电话号码 4.电子邮箱 5.所在门店 6.偏好");
        int input = 0;
        while (input < 1 || input > 6) {
            input = sc.nextInt();
        }
        switch (input) {
            case 1:
                System.out.println("请输入修改后的姓名：");
                String modiName = sc.next();
                Stuff tmpStuff = new Stuff();
                tmpStuff = newList.get(name);//获取到value重新插入hashmap
                tmpStuff.setName(modiName);
                newList.put(modiName, tmpStuff);
                newList.remove(name);
                System.out.println("修改成功！");
                break;
            case 2:
                System.out.println("请输入修改后的职位：");
                String modiPos = sc.next();
                newList.get(name).setPos(modiPos);
                System.out.println("修改成功！");
                break;
            case 3:
                System.out.println("请输入修改后的电话号码：");
                String modiTel = sc.next();
                newList.get(name).setTel(modiTel);
                System.out.println("修改成功！");
                break;
            case 4:
                System.out.println("请输入修改后的电子邮件：");
                String modiMail = sc.next();
                newList.get(name).setMail(modiMail);
                System.out.println("修改成功！");
                break;
            case 5:
                System.out.println("请输入修改后的所在门店：");
                String modiStores = sc.next();
                newList.get(name).setStores(modiStores);
                System.out.println("修改成功！");
                break;
            case 6:
                System.out.println("请输入修改后的偏好：");
                String modiLoves = sc.next();
                newList.get(name).setLoves(modiLoves);
                System.out.println("修改成功！");
                break;
            default:
                System.out.println("非整数，匹配错误，退出修改模式");
                break;
        }
        StuffOperator.allStuffInformation(newList);
    }
}
