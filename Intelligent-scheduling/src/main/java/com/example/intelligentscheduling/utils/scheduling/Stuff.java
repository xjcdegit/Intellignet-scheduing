package com.example.intelligentscheduling.utils.scheduling;



import com.example.intelligentscheduling.entity.Staff;
import com.example.intelligentscheduling.utils.scheduling.yichuan.Chromo;

import java.util.ArrayList;

public class Stuff {
    String name;
    String pos;//职位
    String tel;//电话号码
    String mail;//电子邮箱
    String stores;//所属门店
    String loves;//偏好 三种①工作日  ②工作时间范围 如9-18 ③日工作时间 ④无，正常工作
    //loves输入格式：
    //工作日偏好 1、3、4、5、7
    //工作时间偏好 9:30-21:30 9:00-21:00
    //日工作时间偏好 6小时
    //无偏好，正常工作
    public ArrayList<Integer> love_arr=new ArrayList<>();
    //0设置种类(1 2 3) 1-7 工作日   8-31 工作时间（工作日代表9-21 周末代表10-22）   32 日工作时间偏好

    //日工作时间
    private int countDay=0;//日

    //二进制位串集字段
    public Chromo chro;//暂时用一天的时间集染色体来进行GA
    //chromo调用构造函数初始化

    //构造方法
    public Stuff() {
        this.name = "";
        this.pos = "";
        this.tel = "";
        this.mail = "";
        this.stores = "";
        this.loves = "";
        chro=new Chromo();
    }



    public Stuff(String name, String pos, String tel, String mail, String stores, String loves) {
        this.name = name;
        this.pos = pos;
        this.tel = tel;
        this.mail = mail;
        this.stores = stores;
        this.loves = loves;
        /**
         * 工作日偏好 1、3、4、5、7
         * 工作时间偏好 9:30-21:30 9:00-21:00
         * 日工作时间偏好 6小时
         * 无偏好，正常工作
         *
         * 解析loves为love_arr
         * 0设置种类(1 2 3) 1-7 工作日   8-31 工作时间（工作日代表9-21 周末代表10-22）   32 日工作时间偏好
         */
        for (int i = 0; i < 33; i++) {
            love_arr.add(0);
        }

        /*System.out.println(loves.charAt(0)+"0");
        System.out.println(loves.charAt(1)+"1");
        System.out.println(loves.charAt(2)+"2");
        System.out.println(loves.charAt(3)+"3");
        System.out.println(loves.charAt(4)+"4");
        System.out.println(loves.charAt(5)+"5");
        System.out.println(loves.charAt(6)+"6");*/
        if(loves.charAt(2)=='日'){
            love_arr.set(0,1);
            for(int i=6;i<loves.length();i++) {
                if(loves.charAt(i)=='1') love_arr.set(1,1);//0则check为休息
                if(loves.charAt(i)=='2') love_arr.set(2,1);//0则check为休息
                if(loves.charAt(i)=='3') love_arr.set(3,1);//0则check为休息
                if(loves.charAt(i)=='4') love_arr.set(4,1);//0则check为休息
                if(loves.charAt(i)=='5') love_arr.set(5,1);//0则check为休息
                if(loves.charAt(i)=='6') love_arr.set(6,1);//0则check为休息
                if(loves.charAt(i)=='7') love_arr.set(7,1);//0则check为休息
            }
        }
        //工作时间偏好 10:00-21:00(范围)
        else if(loves.charAt(2)=='时') {
            love_arr.set(0,2);
            char ch1 = loves.charAt(7);
            char ch2 = loves.charAt(8);
            int i = ch1-'0';
            int j = ch2-'0';
            int res = i*10+j;
            if(loves.charAt(10)=='3') {
                love_arr.set((res - 9) * 2 + 8 , 1);
                System.out.println();
            }
            else love_arr.set((res-9)*2+7,1);


            ch1 = loves.charAt(13);
            ch2 = loves.charAt(14);
            i = ch1-'0';
            j = ch2-'0';
            res = i*10+j;
            if(loves.charAt(16)=='3') love_arr.set((res-9)*2+8,1);
            else love_arr.set((res-9)*2+7,1);

            for(int k=8;k<=31;k++)
            {
                if(love_arr.get(k)==1)
                {
                    while(love_arr.get(k)==1&&k<=31) k++;
                    while(love_arr.get(k)==0&&k<=31) {
                        love_arr.set(k,1);
                        k++;
                    }
                    break;
                }
            }
        }
        else if(loves.charAt(2)=='作') {
            love_arr.set(0,3);
            int z = loves.charAt(8)-'0';//不能超过8小时
            love_arr.set(32,z*2);
        }


        /*System.out.print("该员工偏好数组：");
        for(int i=0;i<33;i++){
            System.out.print(love_arr.get(i));
        }
        System.out.println();*/
        chro=new Chromo();
    }


    //getter setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getStores() {
        return stores;
    }

    public void setStores(String stores) {
        this.stores = stores;
    }

    public String getLoves() {
        return loves;
    }

    public void setLoves(String loves) {
        this.loves = loves;
    }

    public int getCountDay() {
        return countDay;
    }

    public void setCountDay(int countDay) {
        this.countDay = countDay;
    }




    //stuff中内置的显示方法
    public void stuff_information() {
        System.out.printf("%-10s" + "%-15s" + "%-12s" + "%-12s" + "%-6s" +"%-20s\n", getName(), getPos(),getTel(),getMail(),getStores(),getLoves());
    }
}




