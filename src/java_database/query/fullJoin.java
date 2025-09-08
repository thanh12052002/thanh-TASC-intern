package java_database.query;

import java.util.ArrayList;
import java.util.List;

public class fullJoin {
    // create class Pair
    static class Pair {
        Integer first;
        Integer second;

        //
        Pair(Integer f, Integer s) {
            first = f;
            second = s;
        }

        //
        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    public static List<Pair> fullJoin(int a[], int b[]) {
        List<Pair> result = new ArrayList<>();
        boolean[] matchedB = new boolean[b.length];
        // 1. Duyet tung phan tu A
        for (int a1 : a) {
            boolean matched = false;
            for (int j = 0; j < b.length; j++) {
                Integer b1 = b[j];
                if (!matchedB[j] && a1 == b1) {
                    result.add(new Pair(a1, b1));
                    matchedB[j] = true;
                    matched = true;
                }
            }
            if (!matched) {
                result.add(new Pair(a1, null));
            }
        }
        for (int j = 0; j < b.length; j++) {
            if (!matchedB[j]) {
                result.add(new Pair(null, b[j]));
            }
        }
        return result;
    }

    public static void main(String args[]) {
        int a[] = { 1, 2, 3 };
        int b[] = { 1, 1, 4 };

        List<Pair> res = fullJoin.fullJoin(a, b);

        for (Pair p : res) {
            System.out.println(p);
        }
    }
}

/*
 * WITH RECURSIVE EmployeeHierarchy AS (
 * -- Phần Neo: Tìm nhân viên ở cấp độ 1
 * SELECT id, name, manager_id, 1 AS level
 * FROM Employees
 * WHERE manager_id = 1
 * 
 * UNION ALL
 * 
 * -- Phần Đệ quy: Tìm nhân viên ở các cấp độ tiếp theo
 * SELECT
 * e.id,
 * e.name,
 * e.manager_id,
 * eh.level + 1 AS level
 * FROM
 * Employees AS e
 * JOIN
 * EmployeeHierarchy AS eh ON e.manager_id = eh.id
 * )
 * 
 * SELECT id, name, manager_id, level
 * FROM EmployeeHierarchy;
 */