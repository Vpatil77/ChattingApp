
import random

def getwinner(comp,you):
    if comp==you:
        return None
    elif comp=='S':
        if you=='W':
            return False
        elif you=='G':
            return True

    elif comp=='W':
        if you=='G':
            return False
        elif you=='S':
            return True

    elif comp=='G':
        if you=='S':
            return False
        elif you=='W':
            return True



print("Computer's Turn: Snake(S) Water(W) or Gun(G): ")
ran=random.randint(1,3)
if ran==1:
    comp='S'
elif ran==2:
    comp='W'
elif ran==3:
    comp='G'

you=input("Your Choice:Snake(S) Water(W) or Gun(G): ")

a=getwinner(comp,you)

print(f"Computer's Chose:{comp}")
print(f"You Chose:{you}")

if a==None:
    print("Its a Tie!!")
elif a==True:
    print("Congrats!! You Win!!")
elif a==False:
    print("Bad Luck !! You Loose")

