file = open("2023\\Day2\\Input.txt", "r")
lines = readlines(file)

red::Int8 = 12
green::Int8 = 13
blue::Int8 = 14

hash = Dict()

function getVal(game)
    println(game)
    elements = split(game)
    returnVal = -1
    for i in 1:trunc(Int, (length(elements) / 2))
        quant::Int16 = parse(Int16, elements[2i - 1])
        color = elements[2i]
        if (quant > hash[color]) hash[color] = quant end

        if (cmp(color, "red") == 0 && quant > red) returnVal = 0 end
        if (cmp(color, "green") == 0 && quant > green) returnVal = 0 end
        if (cmp(color, "blue") == 0 && quant > blue) returnVal = 0 end
    end
    return returnVal
end

sum::Int32 = 0
sumPowers::Int64 = 0
for line in lines
    global hash = Dict(
        "red" => 0,
        "green" => 0,
        "blue" => 0
    )

    words = split(line)
    id = parse(Int32, replace(words[2], ":" => ""))

    colon = findfirst(":", line)[1]
    semi = findfirst(";", line)[1]

    # game 1
    valid = true
    currGame = replace(SubString(line, colon + 1, semi - 1), "," => "")

    if (getVal(currGame) != -1)
        valid = false
    end
    while (!isnothing(findnext(";", line, semi + 1)))
        temp = findnext(";", line, semi + 1)[1]
        currGame = replace(SubString(line, semi + 1, temp - 1), "," => "")
        semi = temp
        if (getVal(currGame) != -1) valid = false end
    end
    currGame = replace(SubString(line, semi + 1), "," => "")
    if (getVal(replace(currGame, ";" => "")) != -1)
        valid = false
    end

    if (valid) global sum += id end
    println("$(hash["red"]) $(hash["green"]) $(hash["blue"])")
    global sumPowers += (hash["red"] * hash["green"] * hash["blue"])
end
println("Part 1 sum: $sum")
println("Part 2 power: $sumPowers")

close(file)