/*
#include"reg52.h"

        typedef unsigned char u8;
        typedef unsigned int u16;

        sbit dj=P0^1;
        sbit k1=P1^0; //启动键
        sbit k2=P1^1; //减速键
        sbit k3=P1^2; //加速键
        sbit k4=P1^3; //停止键
        sbit smgwei1=P2^0;
        sbit smgwei3=P2^1;

        u16 T1_H,T1_L,T0_H,T0_L;
        u8 a,fr;   //a表示占空比：0-100，fr*100后为PWM频率值：1-255。

        u8 code smgduan[16]={0x3f,0x06,0x5b,0x4f,0x66,0x6d,0x7d,0x07,
        0x7f,0x6f,0x77,0x7c,0x39,0x5e,0x79,0x71};  //共阴
        //0-F段码

        void delay(u16 i)
        {        //延迟函数
        while(i--);
        }

        void init()    //中断  初始化
        {
        TMOD=0X01;
        EA=1;
        ET0=1;//51单片机的定时器0允许中断
        }

        void display()    //显示当前占空比，无消隐会导致乱码。
        {
        P3=smgduan[a/10];smgwei1=1;smgwei3=0;delay(100);P3=0X00;
        P3=smgduan[a%10];smgwei1=0;smgwei3=1;delay(100);P3=0X00;
        //数码管动态扫描间隔1ms，过快产生重影，过慢则闪烁。
        }

        void key()
        {
        //u8 i=255;
        if(k1==0){delay(1000);while(!k1);TR0=1;a=50;}
        if(k2==0){delay(1000);while(!k2);a=(a==0)?0:(a-10);}
        if(k3==0){delay(1000);while(!k3);a=(a==100)?100:(a+10);}
        if(k4==0){delay(1000);while(!k4);TR0=0;dj=0;a=0;}
        }

        void time0()interrupt 1
        {
        dj=~dj;
        if(dj==1){TH0=T1_H;TL0=T1_L;}//若正在供电，赋供电初值，使供电维持对应时间；
        if(dj==0){TH0=T0_H;TL0=T0_L;}//若正在断电，赋断电初值；使断电维持对应时间；
        }

        void chuzhi()
        //注意此处不可写为65536，
        //否则当a为0或100时，产生溢出，造成错乱。
        //这样也造成实际定时产生的占空比，与a%相比存在轻微偏差。
        {
        T0_H=(65535-10000/fr+a*100/fr)/256;
        T0_L=(65535-10000/fr+a*100/fr)%256;
        //断电时间初值；

        T1_H=(65535-a*100/fr)/256;
        T1_L=(65535-a*100/fr)%256;
        //供电时间初值；
        }

        main()
        {
        dj=0;fr=5;
        init();
        while(1)
        {
        chuzhi();
        key();
        display();
        }
        }*/
