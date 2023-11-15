import random


def citeste_linii_fisier():
    nume_fisier = "C:\\Users\\Daniel\\Desktop\\AI\\Tema4\\seeds\\seeds_dataset.txt"
    linii = []
    with open(nume_fisier, 'r') as fisier:
        linie = fisier.readline()
        while linie:
            linii.append(linie.strip())
            linie = fisier.readline()

    return linii


def randomizare_date(lista_date):
    randomized_list = random.sample(lista_date, len(lista_date))
    return randomized_list


def ret_liste(date_randomizate):
    date_antrenament = date_randomizate[:168]
    date_test = date_randomizate[169:]
    return date_antrenament,date_test





