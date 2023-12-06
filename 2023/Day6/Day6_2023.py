import re # formatting strings
import math

file = open("2023\Day6\Input.txt", "r")
lines = file.read().split("\n")

times = [int(i) for i in re.sub(' +', ' ', lines[0]).split(" ") if i.isdigit()]
dists = [int(i) for i in re.sub(' +', ' ', lines[1]).split(" ") if i.isdigit()]

def rangeOfPossibilities(totalTime, dist):
    """
    s = speed
    r = remaining time
    t = total time
    d = distance

    sr > d
    s = t - r
    (t - r)r > d
    0 > r^2 - rt + d
    a = 1, b = -t, c = d
    """
    discriminant = (totalTime ** 2) - 4 * dist # b^2 - 4ac
    bound1 = (totalTime - math.sqrt(discriminant)) / 2
    bound2 = (totalTime + math.sqrt(discriminant)) / 2
    if (bound1 % 1 == 0):
        bound1 += 1
    if (bound2 % 1 == 0):
        bound2 -= 1
    return [math.ceil(bound1), math.floor(bound2)]

product = 1
for i in range(len(times)):
    time = times[i]
    dist = dists[i]
    range = rangeOfPossibilities(time, dist)
    count = range[1] - range[0] + 1
    product *= count

print(product)

time2Digits = [int(i) for i in lines[0] if i.isdigit()]
time2 = 0
for i in time2Digits:
    time2 *= 10
    time2 += i

dist2Digits = [int(i) for i in lines[1] if i.isdigit()]
dist2 = 0
for i in dist2Digits:
    dist2 *= 10
    dist2 += i

range = rangeOfPossibilities(time2, dist2)
count = range[1] - range[0] + 1

print(count)