package com.pqq.linkedlist;

/**
 * @author 潘勤强
 * @create 2021-01-17 22:34
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //县创建几个节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);


        //按照编号进行加入（进行一个测试）

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

//        singleLinkedList.list();
//
//        //测试修改节点的代码
//        HeroNode heroNode = new HeroNode(2, "小卢", "玉麒麟~~");
//        singleLinkedList.update(heroNode);
//        System.out.println();
//        //修改之后的链表
//        singleLinkedList.list();
//        System.out.println(singleLinkedList.getLength(singleLinkedList.getHead()));
//
//        System.out.println(singleLinkedList.findLastIndexNode(singleLinkedList.getHead(),3));

        //测试一下反转链表
        singleLinkedList.list();

        System.out.println();
        //反转之后
        singleLinkedList.reverseList(singleLinkedList.getHead());
        singleLinkedList.list();
    }
}

//定义SingleLinkedList管理英雄人物
class SingleLinkedList
{
    //先初始化一个头结点
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead()
    {
        return head;
    }
    //添加节点到单向链表
    //思路，当不考虑编号顺序时，
    //1、找到链表的最后节点
    //2、将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode)
    {
        //因为head不能动，因此我们需要一个辅助便利的temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while(true)
        {
            //找到链表的最后
            if(temp.next == null)
            {
                break;
            }
            //如果没有找到就将temp向后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        temp.next = heroNode;
    }

    //获取单链表节点的个数（如果是带头结点的链表，需要不统计头结点）

    /**
     *
     * @param /head就是链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head)
    {
        if(head.next == null)  //空链表
        {
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量
        HeroNode cur = head.next;
        while(cur != null)
        {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //腾讯面试题，反转单链表
    public static void reverseList(HeroNode head)
    {
        //如果当前链表为空或者只有一个节点，则无须反转，直接返回
        if(head.next == null || head.next.next == null)
        {
            return;
        }

        //定义一个辅助指针(变量)帮助我们遍历链表
        HeroNode cur = head.next;
        HeroNode next = null;   //指向当前节点的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表
        while(cur != null)
        {
            next = cur.next;  //向后遍历

            //这是对节点的一个处理
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    //查找链表的倒数第k个节点
    //思路
    //1.缩写一个方法，接收head节点，同时接受一个index
    //2.index表示的是倒数第index个节点
    //3.先把链表从头到尾遍历，得到链表的总的长度getLength
    //4.得到size之后，我们从链表的第一个开始进行遍历（size-index）个，就可以得到
    //5.若是找到，则返回该节点，若是没有找到，则返回空

    public static HeroNode findLastIndexNode(HeroNode head, int index)
    {
        //判断链表如果为空，返回null
        if(head.next == null)
        {
            return null;  //没有找到
        }
        //第一个遍历得到的链表的长度（节点个数）
        int size = getLength(head);
        //第二次遍历 size-index位置就是我们倒数的第k个节点
        //先做一次index的校验
        if(index <= 0 || index > size)
        {
            return null;
        }
        //定义给辅助变量,for寻哈军定位到倒数的index
        HeroNode cur = head.next;
        for(int i = 0; i < size - index; i++)
        {
            cur = cur.next;
        }
        return cur;

    }


    //第二种方式，添加英雄时，根据排名插入到指定位置
    //如果有这个排名，则添加失败并且给出提示
    public void addByOrder(HeroNode heroNode)
    {
        //因为头结点不能动，因此我们依然通过一个辅助指针（变量来帮助我们找到添加的位置
        //因为是单链表，因为我们找的是temp，是位于添加位置的前一个节点，否则添加不了
        HeroNode temp = head;
        boolean flag = false;   //flag标注添加的编号是否存在，默认为false
        while(true)
        {
            if(temp.next == null)  //说明已经走到链表的最后
            {
                break;
            }
            if(temp.next.no > heroNode.no)  //位置找到，就在temp的后面插入
            {
                break;
            }else if(temp.next.no == heroNode.no)  //说明希望添加的heroNode的编号已经存在
            {
                flag = true;  //说明编号存在
                break;

            }
            temp = temp.next;
        }
        //判断flag的值
        if(flag)  //不能添加，说明编号存在
        {
            System.out.printf("准备插入的英雄编号 %d 已经存在了，不能加入\n",heroNode.no);
        }else{
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //完成修改节点的信息，根据编号来修改，即no编号不能改
    //说明
    //1.根据newHeroNode的no来进行修改即可
    public void update(HeroNode newHeroNode)
    {
        //判断为空
        if(head.next == null)
        {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;   //表示是否找到该节点
        while(true)
        {
            if(temp == null)
            {
                break;  //已经遍历玩链表
            }
            if(temp.no == newHeroNode.no)
            {
                //找到
                flag = true;
                break;

            }
            temp = temp.next;
        }
        //根据flag判断是够找到需要修改的节点
        if(flag)
        {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else{
            //没有找到
            System.out.printf("没有找到编号等于%d的节点,不需要进行任何的修改",newHeroNode.no);
        }

    }


    //显示链表【遍历】
    public void list()
    {
        //判断链表为空
        if(head.next == null)
        {
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此需要一个辅助变量来进行遍历
        HeroNode temp = head.next;
        while(true)
        {
            //判断链表是否到最后
            if(temp == null)
            {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将next后移
            temp = temp.next;
        }
    }
}


//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;  //指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    public HeroNode(int no, String name, String nickName, HeroNode next) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
        this.next = next;
    }

    //为了显示方便，重写toString方法

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +

                '}';
    }
}
