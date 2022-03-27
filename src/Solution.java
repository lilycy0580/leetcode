class Solution {
    public int minimumEffortPath(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        List<int[]> edges = new ArrayList<>();
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                int index = i*col+j;
                if(j<col-1){
                    edges.add(new int[]{index,index+1,Math.abs(heights[i][j]-heights[i][j+1])});
                }
                if(i<row-1){
                    edges.add(new int[]{index,index+col,Math.abs(heights[i][j]-heights[i+1][j])});
                }
            }
        }
        int res = 0;
        UnionFind uf = new UnionFind(row*col);
        Collections.sort(edges,(e1,e2)->(e1[2]-e1[2]));
        for(int i=0; i<edges.size(); i++){
            int[] temp = edges.get(i);
            if(uf.isConnected(0,row*col-1)){
                return res;
            }
            uf.unite(temp[0],temp[1]);
            res = temp[2];
        }
        return res;
    }
}

class UnionFind{

    int[] parent;

    public UnionFind(int n){
        parent = new int[n];
        for(int i=0; i<n; i++){
            parent[i] = i;
        }
    }

    public int findSet(int x){
        return parent[x]==x ? x : (parent[x] = findSet(parent[x]));
    }

    public boolean unite(int x, int y){
        x = findSet(x);
        y = findSet(y);
        if(x==y){
            return false;
        }
        parent[y] = x;
        return true;
    }

    public boolean isConnected(int x, int y){
        x = findSet(x);
        y = findSet(y);
        return x==y;
    }
}
