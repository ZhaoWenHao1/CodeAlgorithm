package alibaba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author happyzhao
 * @data 2020/5/29 8:59
 * @type alibaba
 * @question
 */
public class Main {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source == target){
            return 0;
        }
        int[][] g = new int[routes.length][routes.length];
        for(int[] gn: g){
            Arrays.fill(gn, Integer.MAX_VALUE);
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0;i < routes.length;i++){
            for(int j = 0;j < routes[i].length;j++){
                if(!map.containsKey(routes[i][j])){
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    map.put(routes[i][j], list);
                }else{
                    for (Integer n : map.get(routes[i][j])) {
                        g[n][i] = 1;
                        g[i][n] = 1;
                    }
                    final int ii = i;
                    map.compute(routes[i][j], (k, v) -> {
                        v.add(ii);
                        return v;
                    });
                }
            }
        }
        int min = Integer.MAX_VALUE;
        List<Integer> sourceList = map.get(source);
        List<Integer> targetList = map.get(target);
        if(sourceList == null || targetList == null) return -1;
        for(Integer sourceNode: sourceList){
            long[] dist = new long[routes.length];
            boolean[] visit = new boolean[routes.length];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[sourceNode] = 0;
            int n = routes.length;
            while(n-- > 0){
                long minDist = Integer.MAX_VALUE;
                int minNode = -1;
                for(int i = 0;i < dist.length;i++){
                    if(!visit[i] && dist[i] < minDist){
                        minDist = dist[i];
                        minNode = i;
                    }
                }
//                if(minNode == -1){
//                    break;
//                }
                visit[minNode] = true;
                for(int j = 0;j < g[minNode].length;j++){
                    if(g[minNode][j] == 1){
                        dist[j] = Math.min(dist[j], dist[minNode] + 1);
                    }
                }
            }
            for(int i = 0;i < dist.length;i++){
                if(targetList.contains(i)){
                    min = Math.min(min, (int) dist[i]);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min+1;
    }
    public final String regex = "[\\{\\}]";
    public static void main(String[] args) {
        leetcode.Main main = new leetcode.Main();
//        int[][] routes ;
//        List<List<Integer>> list = new ArrayList<>();
//        try {
//            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\14287\\Desktop\\user5-2.txt"));
//            String str;
//            while ((str = in.readLine()) != null) {
//                String[] split = str.split("[{}]");
//                for (String s : split) {
//                    if(!s.isEmpty() || s.length() > 1){
//                        String[] nums = s.split(",");
//                        List<Integer> collect = Arrays.stream(nums).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());
//                        if(collect.size() > 0){
//                            list.add(collect);
//                        }
//
//                    }
//                }
//            }
//        } catch (IOException e) {
//        }
//        routes = new int[list.size()][];
//        for(int i = 0;i < routes.length;i++){
//            routes[i] = list.get(i).stream().mapToInt(Integer::valueOf).toArray();
//        }
//        int[][] routes = {{7,12},{4,5,15},{6},{15,19},{9,12,13}};
        int[][] routes = {{7,12},{4,5,15},{6},{15,19},{9,12,13}};
//        15
//        12
        System.out.println(main.numBusesToDestination(routes, 15, 12));
    }
}
