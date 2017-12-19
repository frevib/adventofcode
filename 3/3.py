patternFirstStar = [1, 9, 25, 49]
patternSecondStarIndexOf = [0, 9, 25, 36, 49, ]

## first star
input = 265149
base = 1
while 1:
	base += 2
	squared = base ** 2
	if squared > input:
		print 'squared: ' + str(squared)
		print 'base: ' + str(base)
		difference = squared - input
		print 'difference: ' + str(difference)
		print 'offset: ' + str(base - difference)

		stepsLeft = (base/2) - difference
		print 'steps to the left: ' + str(stepsLeft)

		stepsUp = base/2
		print 'steps up: ' + str(stepsUp)

		print 'total steps: '  + str(stepsLeft + stepsUp)
		break

## second star

print '====second star==============================\n\n'


def spiral(n):
	vectorDict = {'0|0': 1}
	x = 0
	y = 0
	count = 1
	sign = 1
	step = 1
	for i in xrange(0, n):	
		for j in xrange(0, count):
			total = 0
			x += step

			# check all adjacent numbers
			for adjacentX in range(-1,2):
				for adjacentY in range(-1,2):
					if adjacentX == 0 and adjacentY == 0:
						continue
					adjacentKey = str(x + adjacentX) + '|' + str(y + adjacentY)
					if adjacentKey in vectorDict:
						total += vectorDict[adjacentKey]
						# print 'adjacent1 x: ' + str(x) + str(y)
			if total > 265149:
				print total
				break
			vectorDict[str(x) + '|' + str(y)] = total

		for k in xrange(0, count):
			total = 0
			y += step

			# check all adjacent numbers
			for adjacentX in range(-1,2):
				for adjacentY in range(-1,2):
					if adjacentX == 0 and adjacentY == 0:
						continue
					adjacentKey = str(x + adjacentX) + '|' + str(y + adjacentY)
					if adjacentKey in vectorDict:
						total += vectorDict[adjacentKey]
						# print 'adjacent1 x: ' + str(x) + str(y)
			if total > 265149:
				print total
				break
			vectorDict[str(x) + '|' + str(y)] = total
		count += 1
		step *= -1
	return vectorDict

spiral = spiral(40)
print spiral



print '----test------'
print '0,0: ' + str(spiral['0|0'])
print '1,0: ' + str(spiral['1|0'])
print '1,1: ' + str(spiral['1|1'])
print '0,1: ' + str(spiral['0|1'])
print '-1,1: ' + str(spiral['-1|1'])

# print '-2,-2: ' + str(spiral['-2|-2'])
# print '-4,-4: ' + str(spiral['-4|-4'])


try:
	print 'number: ' + str(spiral['1|122'])
except:
	print 'key does not exist'



