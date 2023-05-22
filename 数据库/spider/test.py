def PPP(*args):
    a, b, c = args
    print(a, b, c, end='\n')

a = 1
b = 2
c = 3

args = a, b, c

PPP(*args)
