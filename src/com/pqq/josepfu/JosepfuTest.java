package com.pqq.josepfu;

import javax.sound.midi.Soundbank;

/**
 * @author 潘勤强
 * @create 2022-02-02 13:19
 * 约瑟夫问题分析和图解1
 */
public class JosepfuTest {
    public static void main(String[] args) {
        //测试构建和遍历
        CircleSingleLinkedList csl = new CircleSingleLinkedList();
        csl.addBoy(1);
        csl.addBoy(2);
        csl.addBoy(3);
        csl.addBoy(4);
        csl.addBoy(5);
        csl.showBoy();


        //测试一把小孩出圈是否正确
        csl.countBoy(1,2,5);

    }
}

//创建一个单向的缓刑的单向链表
class CircleSingleLinkedList
{
    //创建一个first节点，当前没有编号
    private Boy first = new Boy(-1);
    //添加小孩节点，构建一个环形的链表
    public void addBoy(int nums)
    {
        //对nums进行一个数据校验
        if(nums < 1)
        {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;  //辅助指针，帮助我们构建环形链表
        //使用for循环创建环形链表
        for(int i = 1; i <= nums; i++)
        {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if(i == 1)
            {
                first = boy;
                first.setNext(first);
                curBoy = first;  //让curBoy指向第一个小孩
            }else
            {
                curBoy.setNext(boy);  //
                boy.setNext(first);
                curBoy = boy;

            }
        }
    }

    //遍历当前环形链表
    public void showBoy()
    {
        //判断环形链表是否为空
        if(first == null)
        {
            System.out.println("没有任何小孩");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while(true)
        {
            System.out.printf("小孩的编号%d\n",curBoy.getNo());
            if(curBoy.getNext() == first)  // 说明已经遍历完毕
            {
                break;
            }
            curBoy = curBoy.getNext();  //curBoy 后移
        }
    }

    //根据用户的输入，计算出小孩出圈的一个顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums  表示最初有多少个小孩
     */
    public void countBoy(int startNo, int countNum, int nums)
    {
        if(first == null || startNo < 1 || startNo > nums)
        {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建要给辅助指针，帮助小孩完成出圈
        Boy helper = first;
        //1.需要创建一个辅助指针helper，事先应该指向环形链表最后的这个节点
        while(true)
        {
            if(helper.getNext() == first)  //说明helper指向最后小孩节点
            {
                break;
            }
            helper = helper.getNext();
        }
        //2.当小孩报数前，让first和helper指针同时移动m-1次
        //这里是一个循环操作，直到圈中只有一个节点
        while(true)
        {
            if(helper == first)  //说明只有一个节点
            {
                break;
            }
            //让first和helper指针同时移动countNum-1
            for(int j = 0; j < countNum-1; j++)
            {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first节点指向的节点，就是出圈的小孩节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first); //作用相当于
        }
        System.out.printf("最后留在圈中的编号%d\n",first.getNo());
    }
}

//创建一个boy类，表示一个节点
class Boy{
    private int no;   //编号
    private Boy next;   //指向下一个节点，默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}