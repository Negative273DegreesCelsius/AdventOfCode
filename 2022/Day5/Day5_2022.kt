import java.io.File
import java.io.BufferedReader

fun setStacks(stacks: ArrayList<ArrayList<Char>>, lines: List<String>, emptyLine: Int) {
    for (i in 0..8) {
        var stack: ArrayList<Char> = ArrayList<Char>();
        for (j in 0..(emptyLine - 2)) {
            try {
                var character: Char = lines.get(j).get(i * 4 + 1);
                if (character != ' ') stack.add(0, lines.get(j).get(i * 4 + 1));
            } catch (e: StringIndexOutOfBoundsException) { }
        }
        stacks.add(stack);
    }
}

fun main() {
    fun getInputLines(fileName: String): List<String> = File(fileName).bufferedReader().readLines();

    val lines: List<String> = getInputLines("Input.txt");

    var emptyLine = 0;
    while (!lines.get(emptyLine).isEmpty()) emptyLine++;

    val stacks: ArrayList<ArrayList<Char>> = ArrayList<ArrayList<Char>>();
    setStacks(stacks, lines, emptyLine);
    
    for (i in (emptyLine + 1)..(lines.size - 1)) {
        var line: String = lines.get(i);
        var tokens: List<String> = line.split(" ");
        var quantity = tokens.get(1).toInt();
        var startStack = tokens.get(3).toInt();
        var destination = tokens.get(5).toInt();
        for (j in 1..(quantity)) {
            var crate = stacks.get(startStack - 1).removeLast();
            stacks.get(destination - 1).add(crate);
        }
    }
    print("Part 1 Solution: ");
    for (stack in stacks) {
        print(stack.last());
    }

    stacks.clear();
    setStacks(stacks, lines, emptyLine);
    for (i in (emptyLine + 1)..(lines.size - 1)) {
        var line: String = lines.get(i);
        var tokens: List<String> = line.split(" ");
        var quantity = tokens.get(1).toInt();
        var startStack = tokens.get(3).toInt();
        var destination = tokens.get(5).toInt();
        for (j in quantity downTo 1) {
            var stackSize = stacks.get(startStack - 1).size;
            var crate = stacks.get(startStack - 1).removeAt(stackSize - j);
            stacks.get(destination - 1).add(crate);
        }
    }
    print("\nPart 2 Solution: ")
    for (stack in stacks) {
        print(stack.last());
    }
}