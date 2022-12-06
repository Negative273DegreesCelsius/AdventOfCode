function getMove(move)
    if (cmp(move, "X") == 0 || cmp(move, "A") == 0)
        return "R"
    elseif (cmp(move, "Y") == 0 || cmp(move, "B") == 0)
        return "P"
    else
        return "S"
    end
end

function win(player1, player2)
    if (cmp(player1, player2) == 0) return 3 end
    if (cmp(player1, "R") == 0)
        if (cmp(player2, "P") == 0) return 6 end
    elseif (cmp(player1, "P") == 0)
        if (cmp(player2, "S") == 0) return 6 end
    else
        if (cmp(player2, "R") == 0) return 6 end
    end
    return 0
end

function getP2Move(player1, move)
    if (cmp(move, "Y") == 0) return player1 end
    if (cmp(player1, "R") == 0)
        if (cmp(move, "X") == 0) return "S"
        else return "P" end
    elseif (cmp(player1, "P") == 0)
        if (cmp(move, "X") == 0) return "R"
        else return "S" end
    else
        if (cmp(move, "X") == 0) return "P"
        else return "R" end
    end
end

file = open("2022\\Day2\\Input.txt", "r")

hashmap = Dict(
    "R" => 1,
    "P" => 2,
    "S" => 3
)

lines = readlines(file)

score::Int16 = 0
p2Score::Int16 = 0
for line in lines
    moves = split(line)
    player1 = getMove(moves[1])
    player2 = getMove(moves[2])
    part2Move = getP2Move(player1, moves[2])
    scoreEarned = win(player1, player2) + hashmap[player2]
    global score += scoreEarned
    global p2Score += hashmap[part2Move]
    global p2Score += (cmp(moves[2], "Y") == 0) ? 3 : ((cmp(moves[2], "X") == 0) ? 0 : 6)
end

println("Part 1 Score: $score")
println("Part 2 Score: $p2Score")
    