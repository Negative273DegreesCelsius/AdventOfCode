file = open("2023\Day7\Input.txt", "r")
lines = file.read().split("\n")

hands = { }
five, four, full, three, two, one, high = [], [], [], [], [], [], []
sets = [high, one, two, three, full, four, five]
for l in lines:
    handInfo = l.split(" ")
    hands[handInfo[0]] = {
        "bid": -1,
        "rank": -1
    }
    hands[handInfo[0]]["bid"] = handInfo[1]

def determineType(hand):
    counts = { }
    J = 0
    for i in hand:
        if (i == 'J'):
            J += 1
            continue
        if not i in counts:
            counts[i] = 1
        else:
            counts[i] = counts[i] + 1
    counts = dict(sorted(counts.items(), key = lambda item: item[1], reverse = True))
    cards = list(counts.keys())
    if (len(counts) == 0):
        return 'five'
    if (counts[cards[0]] + int(J) == 5):
        return 'five'
    elif (counts[cards[0]] + int(J) == 4):
        return 'four'
    elif (counts[cards[0]] + int(J) == 3):
        if (counts[cards[1]] ==2):
            return 'full'
        else:
            return 'three'
    elif (counts[cards[0]] + int(J) == 2):
        if (counts[cards[1]] == 2):
            return 'two'
        else:
            return 'one'
    return 'high'

def orderRankings(s):
    strengths = ['J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A']
    result = s
    i = 0
    while (i < len(s)):
        j = len(s) - 1
        while (j > i):
            swap = False
            currHand = result[j]
            nextHand = result[j - 1]
            for k in range(5):
                currCard = currHand[k]
                nextCard = nextHand[k]
                if (strengths.index(currCard) < strengths.index(nextCard)):
                    swap = True
                    break
                elif (strengths.index(currCard) > strengths.index(nextCard)):
                    break
            if (swap):
                result[j - 1] = currHand
                result[j] = nextHand
            j -= 1
        i += 1
    return result

for hand, v in hands.items():
    handType = determineType(hand)
    match handType:
        case 'five':
            five.append(hand)
        case 'four':
            four.append(hand)
        case 'full':
            full.append(hand)
        case 'three':
            three.append(hand)
        case 'two':
            two.append(hand)
        case 'one':
            one.append(hand)
        case 'high':
            high.append(hand)

rank = 1
for i in range(len(sets)):
    sortedSet = orderRankings(sets[i])
    for hand in sortedSet:
        hands[hand]["rank"] = rank
        rank += 1
sum = 0
for hand, v in hands.items():
    sum += (int(v["bid"]) * int(v["rank"]))
print(sum)