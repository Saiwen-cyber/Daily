package coding;

/**
 * @author Annie Fang
 * @create 2023/3/26 11:57
 */
public class UnionFind {
    public final int n;
    public final int[] parent;
    //连通分量
    //也可以看作根节点的数量
    //初始值是节点数量，这个时候 连接关系还没有union。
    public int count;

    public UnionFind(int n, int[][] connections) {
        this.n = n;
        parent = new int[n];
        initParent();
        for (int[] conn : connections) {
            this.union(conn[0], conn[1]);
        }
    }

    /**
     * 初始化节点parent为自己。
     */
    void initParent() {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        count = n;
    }

    //连接节点a，b
    void union(int nodeA, int nodeB) {
        int rootA = find(nodeA);
        int rootB = find(nodeB);
        if (rootA == rootB) {
            return;
        }
        count--;
        parent[rootA] = rootB;
    }

    //查找节点所在树的根节点
    //并查集路径压缩，防止树过高
    int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }
}
