
file = open("2022\\Day1\\Input.txt", "r")

lineNum::Int16 = 1
sum::Int32 = 0
top3::Int32 = 0
calories = zeros(0)

lines = readlines(file)
for line in lines
    if isempty(filter(x -> !isspace(x), line))
        append!(calories, sum)
        global sum = 0
    else
        sum += parse(Int32, line)
    end
    global lineNum += 1
end

max::Int32 = maximum(calories)
println("Maximum: $max")

for i in 1:3
    nextMax = trunc(findmax(calories)[1])
    index = findmax(calories)[2]
    global top3 += nextMax
    deleteat!(calories, index)
end

println("Top 3 Sum: $top3")

close(file)