# collections concept  ------ 集合框架概念

		为什么要使用JDK集合框架
		JDK集合框架，提供哪些功能
		常用集合接口和结合类一览表


## 为什么要使用JDK集合框架

1. 使用集合框架可以降低开发成本，无需重复造轮子
2. 使用JDK集合框架，满足业务场景，功能齐全，安全稳定，官网持续维护。

## JDK集合框架，提供哪些功能

JDK集合框架，主要提供以下数据结构的支持

* 列表/数组 - List 
* 集合 - Set
* 队列 - queue/deque
* 键值对 - map

在后面的章节，我们通过接口和实现来讲解如何使用


## 常用集合接口和结合类一览表


下面列出集合实现类

|列表类|结构|描述|
|-|-|-|
|ArrayList|列表(数组)结构|遍历速度快，CRUD操作相对慢，通过下标访问元素最快|
|LinkedList|链表结构|CRUD操作相对快，只需要改变前后两个节点指针指向，输出顺序和输入顺序一致|

|集合类|结构|描述|
|-|-|-|
|HashSet|集合结构|存储无序的，唯一的，可包含唯一一个Null对象，通过foreach和迭代器来遍历元素|


|queue队列类|结构|描述|
|-|-|-|
|ConcurrentLinkedQueue|基于链表的队列|线程安全,保证入队和出队操作的原子性和一致性，但在遍历和size()操作时只能保证数据的弱一致性|
|LinkedBlockingQueue|基于加锁链表的队列|线程安全,入队时如果队列已满或在出队时如果队列已空,线程被阻塞|
|ArrayBlockingQueue|基于数组实现|有界的阻塞队列,其同步阻塞机制的实现与LinkedBlocklingQueue基本一致|
|SynchronousQueue|同步队列|生产者线程需要及时确认到自己生产的任务已经被消费者线程取走后才能执行后续逻辑的场景下|
|PriorityQueue|优先排序队列|非阻塞队列，也不是线程安全，元素的优先级进行排序，保证最小的元素最先出队|

|deque队列类|结构|描述|
|-|-|-|
|ConcurrentLinkedDeque|基于双向链表的队列|特性和ConcurrentLinkedQueue一样|
|LinkedBlockingDeque|基于双向加锁链表的队列|特性和LinkedBlockingDeque一样|

|map类|结构|描述|
|-|-|-|
|HashMap|键值对集合结构|将key 和value对象存储到set集合中，通过key来标识对象value，线程不安全，多线程数据不能保证一致性|
|ConcurrentHashMap|键值对集合结构|它是线程安全的，通过分16段Segment进行加锁，提高读写并发效率，多线程保持数据一致性|
|LinkedHashMap|基于链表的键值对集合结构|可快速进行插入，删除|
|TreeMap|基于红黑二叉树的Map结构|TreeMap能够实现Entry的排序和快速查找|






