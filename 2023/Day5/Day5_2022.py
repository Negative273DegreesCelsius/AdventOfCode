file = open("2023\Day5\Input.txt", "r")
lines = file.read().split("\n")

def setDict(lineNum):
    dict = { }
    count = 0
    try:
        while (lines[lineNum] != ''):
            # print(lines[lineNum])
            data = [int(d) for d in lines[lineNum].split(" ")]

            dict[count] = {
                "mapped": data[0],
                "target": data[1],
                "r": data[2]
            }
            count += 1
            lineNum += 1
    except IndexError:
        pass
    return [dict, lineNum]

def mapVal(dict, target):
    for k, v in dict.items():
        if (target >= v["target"] and target < v["target"] + v["r"]):
            return v["mapped"] + (target - v["target"])
    return target

seeds = [int(seed.replace("\n", "")) for seed in lines[0].split(" ") if seed.replace("\n", "").isdigit()]

lineNum = 3 # seed to soil starts line 3

maps = dict.fromkeys([
    'seedToSoil', 'soilToFert', 'fertToWater', 'waterToLight', 'lightToTemp', 'tempToHum', 'humToLoc'
])

for k, v in maps.items():
    arr = setDict(lineNum)
    maps[k] = arr[0]
    lineNum = arr[1] + 2

min = "uninitialized"
for seed in seeds:
    soil = mapVal(maps['seedToSoil'], seed)
    fert = mapVal(maps['soilToFert'], soil)
    water = mapVal(maps['fertToWater'], fert)
    light = mapVal(maps['waterToLight'], water)
    temp = mapVal(maps['lightToTemp'], light)
    hum = mapVal(maps['tempToHum'], temp)
    loc = mapVal(maps['humToLoc'], hum)

    if (min == "uninitialized" or loc < min):
        min = loc

print(min)

# min = "uninitialized"

# def reverseMap(dict, mapped):
#     for k, v in dict.items():
#         if (mapped >= v["mapped"] and mapped < v["mapped"] + v["r"]):
#             return v["target"] + (mapped - v["mapped"])
#     return mapped

# compartments = []
# mapsToCompartmentalize = ['humToLoc', 'tempToHum', 'lightToTemp', 'waterToLight', 'fertToWater', 'soilToFert', 'seedsToSoil']
# mappedValues = []
# for i in range(len(mapsToCompartmentalize) - 1):
#     currMap = maps[mapsToCompartmentalize[i]]
#     ranges = [v["r"] for k, v in currMap.items()]
#     newMappedVals = []
#     count = 0
#     for k, v in currMap.items():
#         newMappedVals.append([reverseMap(currMap, v["mapped"]), v["r"]])
#     for j in newMappedVals:
#         mappedValues.append(j)
#     for j in range(len(mappedValues)):
#         mappedValues[j] = [reverseMap(maps[mapsToCompartmentalize[i + 1]])]

# for i in range(len(seeds) / 2):
#     seedStart = seeds[2 * i]
#     seedRange = seeds[2 * i + 1]

