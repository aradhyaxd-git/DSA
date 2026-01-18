class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        n= len(nums)
        pivot=-1


        for i in range(n-1,0,-1):
            if nums[i-1]<nums[i]:
                pivot=i-1
                break
        
        if pivot!=-1:
            for j in range(n-1,pivot,-1):
                if nums[j]>nums[pivot]:
                    nums[pivot],nums[j]= nums[j],nums[pivot]
                    break
        
        nums[pivot+1:]= reversed(nums[pivot+1:])
        