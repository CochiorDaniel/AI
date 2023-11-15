import retea
import citire_date
import numpy as np

def main():
    lista_rand = citire_date.randomizare_date(citire_date.citeste_linii_fisier())
    lista_date_antrenament, lista_date_test = citire_date.ret_liste(lista_rand)
    rn = retea.RN()

    for instanta in lista_date_antrenament:
        print("Instanta:", instanta)
        instanta = instanta[:-1]
        instanta = instanta.split()
        instanta = np.array(instanta)
        instanta = instanta.astype(float)
        instanta = instanta.reshape(7,1)
        print("Clasificare: ",  rn.forward(instanta).tolist())


if __name__ == "__main__":
    main()

