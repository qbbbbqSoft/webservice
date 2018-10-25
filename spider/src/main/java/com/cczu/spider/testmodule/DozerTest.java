package com.cczu.spider.testmodule;

import com.cczu.spider.pojo.TestEntity1;
import com.cczu.spider.pojo.TestEntity2;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.dozer.Mapper;

import java.util.*;

public class DozerTest {
    @Autowired
    private static Mapper mapper;

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("请输入");String s = scanner.nextLine();
//        System.out.print("请输入");int i = scanner.nextInt();
//        System.out.println("输入的内容是" + s + i);
//        Mapper mapper = new DozerBeanMapper();
//        TestEntity1 entity1 = new TestEntity1();
//        entity1.setAddr("addr");
//        entity1.setAge(26);
//        entity1.setUserName("userName");
//        TestEntity2 entity2 = mapper.map(entity1, TestEntity2.class);
//        List<TestEntity1> entity1List = new ArrayList<>();
//        entity1List.forEach(x -> mapper.map(x, TestEntity2.class));
//        System.out.println(entity2.getAddr() + entity2.getUserName()+entity2.getAge2());
        HashMap hashMap = new HashMap();
        hashMap.put("userName","bbqbb");
        hashMap.put("userName2","ssssss");
//        System.out.println(hashMap.containsKey("aaa"));
//        System.out.println(hashMap.entrySet());
//        for (Object hash:hashMap.values()) {
//            System.out.println(hash);
//        }
        Iterator iterator = hashMap.values().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
