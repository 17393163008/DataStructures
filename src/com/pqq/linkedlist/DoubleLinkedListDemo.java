package com.pqq.linkedlist;

/**
 * @author 潘勤强
 * @create 2022-01-24 23:26
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {

    }
}

//创建一个双向链表的类
class DoubleLinkedList{
    //先初始化一个头结点，头结点不要动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0,"","");

    //返回头结点
    public HeroNode2 getHead()
    {
        return head;
    }

    //遍历双向链表的方法
    public void list()
    {
        //判断链表为空
        if(head.next == null)
        {
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此需要一个辅助变量来进行遍历
        HeroNode2 temp = head.next;
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

    public void add(HeroNode2 heroNode)
    {
        //因为head不能动，因此我们需要一个辅助便利的temp
        HeroNode2 temp = head;
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
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改一个节点的内容，可以看到双向链表的节点的内容修改和单链表的节点修改一致
    //只是节点的类型改成了HeroNode2
    public void update(HeroNode2 newHeroNode)
    {
        //判断为空
        if(head.next == null)
        {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
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

    //从双向链表删除一个节点
    //对于双向链表，可以直接找到要删除的节点，找到之后自我删除即可
    public void del(int no)
    {
        //判断链表是否为空
        if(head.next == null)
        {
            System.out.println("链表为空，无法删除！");
            return;
        }

        HeroNode2 temp = head.next;  //辅助变量（指针）
        boolean flag = false;   //标志是否找到待删除节点
        while(true)
        {
            if(temp == null)  //已经找到了链表的最后
            {
                break;
            }
            if(temp.no == no)
            {
                //找到链表需要删除的节点temp
                flag = true;
                break;
            }
            temp = temp.next;   //temp后移，遍历
        }

        //判断flag
        if(flag)  //找到
        {
            //可以删除
            temp.pre.next = temp.next;
            //如果是最后一个节点就不需要执行下面这句话。
            temp.next.pre = temp.pre;
        }else{

        }

    }
}


//定义HeroNode2，每个HeroNode2对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 pre;   //指向前一个节点
    public HeroNode2 next;  //指向下一个节点

    //构造器
    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    public HeroNode2(int no, String name, String nickName,HeroNode2 pre, HeroNode2 next) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
        this.pre = pre;
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