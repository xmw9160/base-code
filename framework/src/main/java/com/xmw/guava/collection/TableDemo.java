package com.xmw.guava.collection;

import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * Date 2018/1/28.
 * Author xmw .
 */
public class TableDemo {

    // Table它有两个支持所有类型的键：”行”和”列”。

    /**
     * 我们知道数据库除了主键外，还提供了复合索引，而且实际中这样的多级关系查找也是比较多的，
     * 当然我们可以利用嵌套的Map来实现：Map<k1,Map<k2,v2>>。为了让我们的代码看起来不那么丑陋，
     * guava为我们提供了Table。
     * Table涉及到3个概念：rowKey,columnKey,value
     */
    public static void main(String[] args) {
        //记录学生在某门课上的成绩
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("jack", "java", 100);
        table.put("jack", "c", 90);
        table.put("mike", "java", 93);
        table.put("mike", "c", 100);
        Set<Table.Cell<String, String, Integer>> cells = table.cellSet();
        for (Table.Cell<String, String, Integer> cell : cells) {
            System.out.println(cell.getRowKey() + " " + cell.getColumnKey() + " " + cell.getValue());
        }
        System.out.println("--------------------");
        System.out.println(table.row("jack"));
        System.out.println(table);
        System.out.println(table.rowKeySet());
        System.out.println(table.columnKeySet());
        System.out.println(table.values());
    }
}
