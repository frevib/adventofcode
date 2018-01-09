console.log("=== 14 part 2");

let fs = require('fs'),
    path = require('path'),
    filePath = path.join(__dirname, '../output.txt'),
    bitGrid = [],
    stateGrid = [];

let data = fs.readFileSync(filePath, {encoding: "utf-8"});
stateGrid = function buildStateGrid() {
    let row = [], grid = [];
    for (let i = 0; i < 128; i++) {
        for (let j = 0; j < 128; j++) {
            row.push('open')
        }
        grid.push(row);
        row = [];
    }

    return grid;
}();

// console.log(stateGrid[3][100])

data.split("\n").forEach(function(e, i) {
    bitGrid.push(e.split("").map(Number));
});

function countBitsInGroup(x, y) {
    if (x > 127 || x < 0 || y > 127 || y < 0) {
        return 0;
    }
    if (stateGrid[x][y] === 'done'){
        return 0;
    }
    if (bitGrid[x][y] === 0) {
        stateGrid[x][y] = 'done';
        return 0;
    }

    stateGrid[x][y] = 'done';
    return 1 +
        countBitsInGroup(x + 1, y) +
        countBitsInGroup(x - 1, y) +
        countBitsInGroup(x, y + 1) +
        countBitsInGroup(x, y - 1);
}

function countGroups(bitGrid) {
    let amountOfGroups = 0, count = 0;
    for (let x = 0; x < bitGrid.length; x++) {
        for (let y = 0; y < bitGrid[x].length; y++) {
            if (countBitsInGroup(x, y) !== 0) {
                amountOfGroups++;
            }
        }
    }

    return amountOfGroups;
}

const amountOfGroups = countGroups(bitGrid);
console.log(`amount of groups: ${amountOfGroups}`);
