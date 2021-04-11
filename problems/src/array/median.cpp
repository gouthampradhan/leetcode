class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        map<int,int>mp;
        for(int i=0;i<nums1.size();i++){
            mp[nums1[i]]++;
        }
        for(int i=0;i<nums2.size();i++){
            mp[nums2[i]]++;
        }
        vector<int>ans;
        for(auto i:mp){
            for(int j=0;j<i.second;j++){
                ans.push_back(i.first);
            }
        }
        // for(int i=0;i<ans.size();i++){
        //     cout<<ans[i]<<' ';
        // }
        // cout<<'\n';
        double a;
        int n=ans.size();
        if(n%2==1){
            a=(double)ans[n/2];
        }
        else{
            cout<<ans[n/2]<<' '<<ans[n/2-1]<<'\n';
            a=((double)ans[n/2]+(double)ans[(n/2)-1])/2.0;
        }
        return a;
    }
};
