class DisjointSet {
    List<Integer> parent = new ArrayList<>();
    List<Integer> rank = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    public DisjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            parent.add(i);
            rank.add(0);
            size.add(1);
        }
    }

    public int findUPar(int node) {
        if (node == parent.get(node))
            return node;

        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);

        return parent.get(node);
    }

    public void unionByRank(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);

        if (ulp_u == ulp_v)
            return;

        if (rank.get(ulp_u) < rank.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
        } else if (rank.get(ulp_v) < rank.get(ulp_u)) {
            parent.set(ulp_v, ulp_u);
        } else {
            parent.set(ulp_v, ulp_u);
            rank.set(ulp_u, rank.get(ulp_u) + 1);
        }
    }

    public void unionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);

        if (ulp_u == ulp_v)
            return;
        
        if (size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_u) + size.get(ulp_v));
        } else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }
}

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);

        Map<String, Integer> mailMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String mail = accounts.get(i).get(j);
                if (!mailMap.containsKey(mail)) {
                    mailMap.put(mail, i);
                } else {
                    ds.unionByRank(i, mailMap.get(mail));
                }
            }
        }

        Map<Integer, List<String>> emailGrp = new HashMap<>();
        for (Map.Entry<String, Integer> entry : mailMap.entrySet()) {
            String email = entry.getKey();
            int id = entry.getValue();

            int uPar = ds.findUPar(id);
            emailGrp.putIfAbsent(uPar, new ArrayList<>());
            emailGrp.get(uPar).add(email);
        }

        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry: emailGrp.entrySet()) {
            int accId = entry.getKey();
            List<String> emails = entry.getValue();
            Collections.sort(emails);
            List<String> merged = new ArrayList<>();
            merged.add(accounts.get(accId).get(0));
            merged.addAll(emails);
            res.add(merged);
        }

        return res;
    }
}