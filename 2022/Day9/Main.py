import copy

def tailAdj(oldHPos, newHPos, tPos):
    if (len(tPos) == 0): 
        return oldHPos
    hXPos = newHPos[1]
    hYPos = newHPos[0]
    checkPos = [
        [hYPos, hXPos - 1], [hYPos, hXPos + 1], [hYPos - 1, hXPos], [hYPos + 1, hXPos],
        [hYPos - 1, hXPos - 1], [hYPos - 1, hXPos + 1], [hYPos + 1, hXPos + 1], [hYPos + 1, hXPos - 1],
        [hYPos, hXPos]
    ]
    for pos in checkPos:
        if (pos[0] == tPos[0] and pos[1] == tPos[1]): # checks if adjacent
            return tPos 
    return oldHPos

def updateTails(tPos, tails):
    tXPos = tPos[1]
    tYPos = tPos[0]
    tails[tYPos][tXPos] = "#"
    return tails

file = open("2022\Day9\Input.txt", "r")
lines = file.readlines()
maxX = minX = maxY = minY = xDim = yDim = 0
for line in lines:
    directions = line.split()
    dir = directions[0]
    space = int(directions[1])
    if (dir == "U"):
        yDim += space
        if (yDim > maxY):
            maxY = yDim
    elif (dir == "D"):
        yDim -= space
        if (yDim < minY):
            minY = yDim
    elif (dir == "L"):
        xDim -= space
        if (xDim < minX):
            minX = xDim
    else:
        xDim += space
        if (xDim > maxX):
            maxX = xDim

xDim = maxX + 1 if minX == 0 else maxX + abs(minX) + 1
yDim = maxY + 1 if minY == 0 else maxY + abs(minY) + 1
startX = 0 if minX == 0 else abs(minX)
startY = maxY
start = hPos = [startY, startX]
tPos = tails = []
for i in range(yDim):
    tails.append(["." for j in range(xDim)])

for line in lines:
    directions = line.split()
    dir = directions[0]
    space = int(directions[1])
    if (dir == 'U'):
        for i in range(space):
            tempHPos = copy.deepcopy(hPos)
            hPos[0] -= 1
            tPos = tailAdj(oldHPos = tempHPos, newHPos = hPos, tPos = tPos)
            tails = updateTails(tPos = tPos, tails = tails)
    elif (dir == 'D'):
        for i in range(space):
            tempHPos = copy.deepcopy(hPos)
            hPos[0] += 1
            tPos = tailAdj(oldHPos = tempHPos, newHPos = hPos, tPos = tPos)
            tails = updateTails(tPos = tPos, tails = tails)
    elif (dir == 'L'):
        for i in range(space):
            tempHPos = copy.deepcopy(hPos)
            hPos[1] -= 1
            tPos = tailAdj(oldHPos = tempHPos, newHPos = hPos, tPos = tPos)
            tails = updateTails(tPos = tPos, tails = tails)
    else:
        for i in range(space):
            tempHPos = copy.deepcopy(hPos)
            hPos[1] += 1
            tPos = tailAdj(oldHPos = tempHPos, newHPos = hPos, tPos = tPos)
            tails = updateTails(tPos = tPos, tails = tails)

count = 0
for row in range(len(tails)):
    for space in range(len(tails[row])):
        if (tails[row][space] == '#'):
            count += 1
print(f"Tail Positions: {count}")