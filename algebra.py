def nary_polynomial_eval(x, n, coefficients):
	sum_eval = 0
	for i in reversed(range(0, n+1)):
		sum_eval = sum_eval + coefficients[i]*(x**i)
	return sum_eval

def bin_search(hi, lo, target, n, coefficients):
	hi = hi
	lo = lo

	while hi >= lo:
		mid = (hi+lo)//2
		temp_eval = nary_polynomial_eval(mid, n, coefficients)

		if temp_eval == target:
			return mid
		elif temp_eval > target:
			hi = mid-1
		else:
			lo = mid+1
	return "N/A"

def find_x(y, n, coefficients):
	# we want to plug different values of x into the nary_polynomial
	# until we get a value of x that maps to y
	# try values of x until we get an upper bound, then binary search
	i = 0
	while True:
		temp_eval = nary_polynomial_eval(2**i, n, coefficients)
		if temp_eval == y:
			return 2**i
		# if we've passed y, we have our binary search bounds
		elif temp_eval > y:
			return bin_search(hi=2**i, lo=2**(i-1), target=y, n=n, coefficients=coefficients)
		else:
			i = i+1



# Execution starts here
print(find_x(28, 2, [1, 0, 3]))
print(find_x(5, 5, [1, 1, 1, 1, 1, 0]))
print(find_x(0, 1, [1, 3]))
print(find_x(1881676371789154860897070, 3, [1, 0, 0, 1]))

