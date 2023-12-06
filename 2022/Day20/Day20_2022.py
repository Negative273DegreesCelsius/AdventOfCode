file = open("2022\Day20\Input.txt", "r")
lines = file.readlines()

arr = [int(line.replace("\n", "")) for line in lines]
nums = {
    i: arr[i] for i in range(len(arr))
}

def findIndexInDict(target):
    for k, v in nums.items():
        if v == target:
            return k

def printAsArr():
    print([val for k, val in nums.items()])

sum = 0

# printAsArr()
for i in range(len(arr)):
    target = arr[i]
    index = findIndexInDict(target)

    counter = 0
    j = index
    if (target > 0):
        if (target > len(arr)):
            counter = (target + 1) % len(arr)
        for counter in range(target):
            if (j + 1 < len(arr)):
                temp = nums[j + 1]
                nums[j + 1] = target
                nums[j] = temp
                j += 1
            else:
                temp = nums[0]
                nums[0] = target
                nums[j] = temp
                j = 0
    elif (target < 0):
        if (target * -1 > len(arr)):
            counter = (target - 1) % len(arr)
        for counter in range(target * -1):
            if (j - 1 >= 0):
                temp = nums[j - 1]
                nums[j - 1] = target
                nums[j] = temp
                j -= 1
            else:
                temp = nums[len(nums) - 1]
                nums[len(nums) - 1] = target
                nums[j] = temp
                j = len(arr) - 1
    # printAsArr()

nth = [1000, 2000, 3000]
indexZero = findIndexInDict(0)
for n in nth:
    indexOffset = n % len(arr)
    if (indexZero + indexOffset < len(arr)):
        sum += nums[indexZero + indexOffset]
    else:
        sum += nums[indexZero - (len(arr) - indexOffset)]
print(indexZero)
print(sum)