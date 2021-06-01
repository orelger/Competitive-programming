'''
Author: Orel Gershonovich
Release: 21/3/21
'''

if __name__ == "__main__":
    T = 0
    N = 0
    P = 0

    T = int(input())

    for t in range(T):
        N = int(input())
        X = N
        while X > 0:
            X //= 2
            P += 1

        P -= 1  # decrease in 1 because we need the lower val of the log

        # if pow(2, p) == N print 1
        if 2 ** P == N:
            print(1)
        else:  # get the large pow(2, p) = K
            # sub K from N and mul in 2 because is jumping in constant 2
            N = N - (2 ** P)
            N = N * 2
            print(N + 1)  # print + 1 because mul in 2 is even and we need the odd num

        P = 0
