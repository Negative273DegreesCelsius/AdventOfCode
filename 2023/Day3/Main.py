file = open("2023\Day3\Input.txt", "r")
lines = file.readlines()

grid = [line for line in lines]

sum = 0

gears = { }
gearSum = 0

def checkForSymbol(num, indices):
    returnVal = 0
    around = [
        [-1, -1], [-1, 0], [-1, 1],
        [0 , -1],          [0 , 1],
        [1 , -1], [1 , 0], [1 , 1]
    ]
    gearsAppended = []
    for index in indices:
        for a in around:
            try:
                char = grid[index[0] - a[0]][index[1] - a[1]]
                if (not char.isalnum() and (char != ".") and (char != "\n")):
                    returnVal = num
                if (char == "*" and gearsAppended.count([index[0] - a[0], index[1] - a[1]]) == 0):
                    gearsAppended.append([index[0] - a[0], index[1] - a[1]])
                    gears[f"{index[0] - a[0]} {index[1] - a[1]}"].append(num)
            except IndexError:
                pass
    return returnVal

# check for all gears:
for i in range(len(grid)):
    line = grid[i]
    for j in range(len(line)):
        if (line[j] == "*"):
            gears[f"{i} {j}"] = []

for i in range(len(grid)):
    line = grid[i]
    # search for number
    num = 0
    indices = []
    for j in range(len(line)):
        if line[j].isdigit():
            indices.append([i, j])
            num = num * 10 + int(line[j])
        else:
            if (num != 0):
                sum += checkForSymbol(num, indices)
            num = 0
            indices = []

print(sum)

for k, v in gears.items():
    if (len(v) == 2):
        gearSum += v[0] * v[1]

print(gearSum)