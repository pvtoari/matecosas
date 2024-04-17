from decimal import Decimal, getcontext
import datetime;

def gauss(decimales):
    anim= "|/-\\"
    
    getcontext().prec = decimales + 2
    a = Decimal(1)
    b = Decimal(1) / Decimal(2).sqrt()
    t = Decimal(1) / Decimal(4)
    p = Decimal(1)
    pi_prev = Decimal(0)
    cont = 0;
    while True:
        cont += 1
        a_next = (a + b) / Decimal(2)
        b = (a * b).sqrt()
        t -= p * (a - a_next) ** 2
        a = a_next
        p *= Decimal(2)
        
        pi = (a + b) ** 2 / (Decimal(4) * t)
        if pi == pi_prev:
            break
        pi_prev = pi
        
        progress = anim[cont % len(anim)]
        print(f"\r{progress} {cont}", end="")
    
    return str(pi)[:-1]
    
def main():
    decimales = int(input("Numero de decimales: "))
    pito = gauss(decimales)
    f = open("/workspaces/matecosas/decimalesPy/calcs/pi_"+ str(datetime.datetime.now()) + ".txt", "w")
    f.write(pito)
    print("\n"+"Resultado guardado en -> calcs/..")

if __name__ == "__main__":
    main()