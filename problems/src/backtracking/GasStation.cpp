class Solution {
public:
    int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
        int n = gas.size();
        for(int i=0;i<=n;i++){
            vector<int> summ(n+1,0);
            int cnt = 0;
            bool flag = true;
            for(int j=i+1;cnt<=n;j++,cnt++){
                summ[j%n] = (summ[(j-1)%n] + gas[(j-1)%n] - cost[(j-1)%n]);  
                if(summ[j%n] < 0){
                    flag=false; break;
                }
            }
            if(flag) return i;
        }
        return -1;
    }
};