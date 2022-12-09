file = open("2022\\Day8\\Input.txt")

count::Int16 = 0
p2Score::Int32 = 0
treeArr = []

lines = readlines(file)
for line in lines
    tempArr = split(line, "")
    treeRow = [parse(Int8, i) for i in tempArr]
    push!(treeArr, treeRow)
end

rows = length(treeArr)
cols = length(treeArr[1])

# convert array of arrays to 2x2 matrix
mat = similar(first(treeArr), length(treeArr), length(first(treeArr)))
for i in eachindex(treeArr)
    mat[i,:] = treeArr[i]
end

for row in 1:rows
    for col in 1:cols
        visibleT::Bool = true
        visibleL::Bool = true
        visibleR::Bool = true
        visibleB::Bool = true
        treesT::Int32 = 0;
        treesL::Int32 = 0;
        treesR::Int32 = 0;
        treesB::Int32 = 0;

        height = mat[row, col]
        tempCol = col
        tempRow = row
        countTrees = true

        while (tempCol > 1)
            tempCol -= 1
            if (mat[row, tempCol] >= height) 
                visibleL = false
                if (countTrees) 
                    countTrees = false 
                    treesL += 1
                end
            else
                if (countTrees) treesL += 1 end
            end
        end
        tempCol = col
        countTrees = true
        while (tempCol < cols)
            tempCol += 1
            if (mat[row, tempCol] >= height) 
                visibleR = false
                if (countTrees)
                    countTrees = false
                    treesR += 1
                end
            else
                if (countTrees) treesR += 1 end
            end
        end
        countTrees = true
        while (tempRow > 1)
            tempRow -= 1
            if (mat[tempRow, col] >= height) 
                visibleT = false 
                if (countTrees)
                    countTrees = false
                    treesT += 1
                end
            else
                if (countTrees) treesT += 1 end
            end
        end
        tempRow = row
        countTrees = true
        while (tempRow < rows)
            tempRow += 1
            if (mat[tempRow, col] >= height) 
                visibleB = false
                if (countTrees)
                    countTrees = false
                    treesB += 1
                end
            else
                if (countTrees) treesB += 1 end
            end
        end
        if (visibleB || visibleL || visibleR || visibleT) global count += 1 end
        sceneScore::Int32 = treesB * treesL * treesR * treesT
        if (sceneScore > p2Score) global p2Score = sceneScore end
    end
end

println("Part 1 count: $count")
println("Part 2 score: $p2Score")