'''
Author: Orel Gershonovich
Release: 31/3/21
'''


def calB(a, b, base, key, val):
    carry = 0
    sumB = ""

    for i in range(a.__len__() - 1, -1, -1):

        # Current Place value for
        # the resultant sum
        if a[i] == ' ':
            x = 0
        if a[i] != ' ':
            numx = a[i]
            x = val[numx]
        if b[i] == ' ':
            y = 0
        if b[i] == '+':
            continue
        if b[i] != ' ':
            numx = b[i]
            y = val[numx]

        curr = carry + x + y

        # Update carry
        carry = curr // int(base)

        # Find current digit
        curr = curr % int(base)

        # Update sum result
        z = key[curr]
        sumB += z

    if carry > 0:
        z = key[carry]
        sumB += z

    return sumB[::-1]


if __name__ == "__main__":
    b, letters = input().split()
    my_dict = {}
    my_dict2 = {}

    for index, let in enumerate(letters):
        my_dict[index] = let
        my_dict2[let] = index

    num1 = str(input())
    realNum1 = num1
    num2 = str(input())
    realNum2 = num2
    dash = input("")

    s = ""
    diff = abs(num1.__len__() - num2.__len__())

    # Padding 0 in front of the
    # number to make both numbers equal
    for i in range(1, diff + 1):
        s += " "

    if num1.__len__() < num2.__len__():
        num1 = s + num1
    else:
        num2 = s + num2

    sum = calB(num1, num2, b, my_dict, my_dict2)

    space = dash.__len__() - sum.__len__()

    sum = (' ' * space) + sum

    print(b, letters)
    print(realNum1)
    print(realNum2)
    print(dash)
    print(sum)
