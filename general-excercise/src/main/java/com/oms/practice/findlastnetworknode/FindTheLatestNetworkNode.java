package com.oms.practice.findlastnetworknode;

import java.util.HashMap;
import java.util.Map;

public class FindTheLatestNetworkNode {
    public static void main(String[] args) {
        // each node in fromIds array has only one out based on the same index in toIds array.
        // now the question is how to find last node from given node or print path of request which started from a given node.
        int[] fromIds = {1, 2, 3, 5, 4, 7, 6};
        int[] toIds =   {3, 3, 4, 4, 6, 6, 9};
        int start = 3;
        // Approach 1
        extracted(fromIds, toIds, start);
        // Approach 2
        recursiveSolution(fromIds,toIds,start);

    }
    private static int recursiveSolution(int[] fromIds, int[] toIds, int start) {
        System.out.println(start);
        for (int i = 0; i < fromIds.length; i++) {
            if(start==fromIds[i])
                return recursiveSolution(fromIds,toIds,toIds[i]);
        }
        return start;
    }
    private static void extracted(int[] fromIds, int[] toIds, int start) {
        Map<Integer , String > nodes = new HashMap<>();
        for (int i = 0; i < fromIds.length; i++) {
            nodes.put(fromIds[i] , String.valueOf(toIds[i]));
        }
        while(nodes.get(start)!= null){
            System.out.println(start);
            start = Integer.parseInt(nodes.get(start));
        }
        System.out.println("result => "+ start);
    }
}
